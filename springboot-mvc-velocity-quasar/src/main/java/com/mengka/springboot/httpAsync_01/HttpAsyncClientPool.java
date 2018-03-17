package com.mengka.springboot.httpAsync_01;

import co.paralleluniverse.fibers.httpasyncclient.FiberCloseableHttpAsyncClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author huangyy
 * @version cabbage-forward2.0,2018-03-12
 * @since cabbage-forward2.0
 */
public class HttpAsyncClientPool {

    private static final Log logger = LogFactory.getLog(HttpAsyncClientPool.class);

    private static HttpAsyncClientPool mozuHttpClientPool = null;
    private final CloseableHttpAsyncClient threadSafeClient;
    private final IdleConnectionMonitorThread monitor;

    private PoolingNHttpClientConnectionManager cm;

    private HttpAsyncClientPool() throws IOReactorException {
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(
                IOReactorConfig.
                        custom().
                        setIoThreadCount(Runtime.getRuntime().availableProcessors()).
                        setSoKeepAlive(true).
                        setSoTimeout(-1).
                        setConnectTimeout(-1).
                        setSoLinger(0).
                        setSoReuseAddress(false).
                        setSelectInterval(10).
                        build()
        );
        //connect manager
        cm = new PoolingNHttpClientConnectionManager(ioReactor);
        // Increase max total connection to 200
        cm.setMaxTotal(InnotekConfig.getMaxHttpClientConnections());
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(InnotekConfig.getDefaultHttpClientMaxPerRoute());

        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator
                        (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase
                            ("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return -1;
            }
        };

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(-1)
                .setSocketTimeout(-1)
                .setConnectTimeout(-1)
                .build();



        // Build the client.
//        threadSafeClient = HttpAsyncClientBuilder.create()
//                .setConnectionManager(cm)
//                .useSystemProperties()
//                .disableConnectionState()
//                .setDefaultRequestConfig(requestConfig)
//                .setKeepAliveStrategy(myStrategy)
//                .build();

        threadSafeClient = FiberCloseableHttpAsyncClient.wrap(HttpAsyncClients.
                custom().
                setMaxConnPerRoute(InnotekConfig.getDefaultHttpClientMaxPerRoute()).
                setDefaultRequestConfig(requestConfig).
                setMaxConnTotal(InnotekConfig.getMaxHttpClientConnections()).
                setConnectionManager(cm).
                build());


        threadSafeClient.start();

        monitor = new IdleConnectionMonitorThread(cm);

        monitor.setDaemon(true);
        monitor.start();
    }

    static public HttpAsyncClientPool getInstance() throws IOReactorException {
        if (mozuHttpClientPool == null) {
            synchronized (HttpAsyncClientPool.class) {
                if (mozuHttpClientPool == null) {
                    mozuHttpClientPool = new HttpAsyncClientPool();
                }
            }
        }
        return mozuHttpClientPool;
    }

    /**
     * Get the http client to use for connecting to an HTTP server.
     *
     * @return HttpClient
     */
    public CloseableHttpAsyncClient getHttpClient() {
        //Get the http client
        return this.threadSafeClient;
    }

    /**
     * Call once when destroying process to clean up http connections.
     */
//    public void shutdown() {
//        try {
//            monitor.shutdown();
//        } catch (InterruptedException ie) {
//            logger.info("Intertupted shutting down HttpConnection Pooling.");
//        }
//    }

    // Watches for stale connections and evicts them.
    private class IdleConnectionMonitorThread extends Thread {
        // The manager to watch.
        private final PoolingNHttpClientConnectionManager cm;
        // Use a BlockingQueue to stop everything.
        private final BlockingQueue<Stop> stopSignal = new ArrayBlockingQueue<Stop>(1);

        // Pushed up the queue.
        private class Stop {
            // The return queue.
            private final BlockingQueue<Stop> stop = new ArrayBlockingQueue<Stop>(1);

            // Called by the process that is being told to stop.
            public void stopped() {
                // Push me back up the queue to indicate we are now stopped.
                stop.add(this);
            }

            // Called by the process requesting the stop.
            public void waitForStopped() throws InterruptedException {
                // Wait until the callee acknowledges that it has stopped.
                stop.poll(30, TimeUnit.SECONDS);
            }

        }

        IdleConnectionMonitorThread(PoolingNHttpClientConnectionManager cm) {
            super();
            this.cm = cm;
        }

        @Override
        public void run() {
            try {
                // Holds the stop request that stopped the process.
                Stop stopRequest;
                // Every 5 seconds.
                while ((stopRequest = stopSignal.poll(5, TimeUnit.SECONDS)) == null) {
                    // Close expired connections
                    cm.closeExpiredConnections();
                    // Optionally, close connections that have been idle too long.
                    cm.closeIdleConnections(60, TimeUnit.SECONDS);
                    // Look at pool stats.
//                    logger.debug("Stats: {}", cm.getTotalStats());
                }
                // Acknowledge the stop request.
                stopRequest.stopped();
            } catch (InterruptedException ex) {
                // terminate
            }
        }

        public void shutdown() throws InterruptedException {
            logger.trace("Shutting down client pool");
            // Signal the stop to the thread.
            Stop stop = new Stop();
            stopSignal.add(stop);
            // Wait for the stop to complete.
            stop.waitForStopped();
            // Close the pool - Added
            try {
                threadSafeClient.close();
            } catch (IOException ioe) {
                logger.info("IO Exception while closing HttpClient connecntions.");
            }
            // Close the connection manager.
            synchronized (this) {
                notifyAll();
            }
            logger.trace("Client pool shut down");
        }

    }

}