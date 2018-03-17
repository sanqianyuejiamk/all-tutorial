package com.mengka.springboot.httpAsync_01;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.util.EntityUtils;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/**
 * High-Concurrency Async HTTP Clients
 * 1) 异步http连接池；
 * 2) 第三方系统接口延迟很长，也很稳定；
 * 3) 信号量控制生成速度；
 * 4) 开源协程库quasar，比线程资源消耗更低效率更高，在用户空间切换状态；
 *
 * @author huangyy
 * @version cabbage-forward2.0,2018-03-18
 * @since cabbage-forward2.0
 */
public class httpAsync_01 {

    private static final Log logger = LogFactory.getLog(httpAsync_01.class);

    private static final Semaphore concurrency = new Semaphore(1024);

    public static void main(String[] args) throws Exception {

        //转换成请求参数
        String url = "http://google.com";
        Map<String, Object> param = new HashMap();

        HttpPost httpPost = new HttpPost(url);
        setPostParams(httpPost, param);


        for (int i = 0; i < 10000; i++) {
            logger.info("High-Concurrency Async HTTP Clients test..");

            //step1 获取信号量控制并发数（防止内存溢出）
            concurrency.acquireUninterruptibly();

            CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClientPool.getInstance().getHttpClient();

            try {
                logger.info("发送泊位状态fiber start..");
                new Fiber<Void>(new SuspendableRunnable() {
                    @Override
                    public void run() throws SuspendExecution, InterruptedException {
                        try {
                            // snippet future calls
                            ArrayList<Future<HttpResponse>> futures = new ArrayList<>();

                            futures.add(httpAsyncClient.execute(httpPost, HttpClientContext.create(), new FutureCallback<HttpResponse>() {

                                @Override
                                public void completed(HttpResponse response) {
                                    int httpCode = response.getStatusLine().getStatusCode();
                                    logger.info("发送泊位状态响应Response: " + httpCode);
                                    if (httpCode < 200 || httpCode >= 300) {
                                        logger.error("Send Failed: " + httpCode);
                                        //保存到缓存队列
                                        logger.info("调用发送泊位状态接口-第三方接口1调用失败，保存到缓存队列!");
                                        return;
                                    }
                                }

                                @Override
                                public void failed(Exception ex) {
                                    logger.error("调用发送泊位状态接口失败！", ex);
                                }

                                @Override
                                public void cancelled() {
                                    logger.error("调用发送泊位状态接口cancelled.");
                                }
                            }));

                            for (Future<HttpResponse> future : futures) {
                                int httpCode = future.get().getStatusLine().getStatusCode();

                                String responseBody = EntityUtils.toString(future.get().getEntity(), "utf-8");
                                logger.info("--------, future = " + responseBody);
                            }
                            // end of snippet
                        } catch (Exception ex) {
                            logger.error("error!", ex);
                        }
                    }
                }).start();
                logger.info("发送泊位状态fiber end..");
            } finally {
                concurrency.release();
            }
        }
    }

    private static void setPostParams(HttpPost httpost, Map<String, Object> params) {
        if (params == null) {
            return;
        }
        String content = JSON.toJSONString(params);
        httpost.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
    }
}
