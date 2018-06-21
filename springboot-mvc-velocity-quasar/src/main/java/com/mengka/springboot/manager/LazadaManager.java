package com.mengka.springboot.manager;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;
import com.mengka.springboot.httpAsync_01.HttpAsyncClientPool;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/**
 * @author huangyy
 * @version cabbage-forward2.0,2018/6/21
 * @since cabbage-forward2.0
 */
@Service
public class LazadaManager {

    private static final Logger logger = LogManager.getLogger(LazadaManager.class);

    private static final Semaphore concurrency = new Semaphore(300);

    public final static String INPUT_CHARSET = "utf-8";//字符格式

    /**
     *  发送HTTP异步请求
     *
     * @param url
     * @throws Exception
     */
    public void hehe(String url) throws Exception {
        logger.info("------------, Lazada hehe。。。");
        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClientPool.getInstance().getHttpClient();

        //转换成请求参数
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("aa","test11");

        HttpPost httpPost = new HttpPost(url);
        setPostParams(httpPost, param);

        //step1 获取信号量控制并发数（防止内存溢出）
        concurrency.acquireUninterruptibly();

        logger.debug("发送泊位状态fiber start..");
        new Fiber<Void>(new SuspendableRunnable() {
            @Override
            public void run() throws SuspendExecution, InterruptedException {
                try {
                    // snippet future calls
                    ArrayList<Future<HttpResponse>> futures = new ArrayList<>();

                    futures.add(httpAsyncClient.execute(httpPost, HttpClientContext.create(), new FutureCallback<HttpResponse>() {

                        @Override
                        public void completed(HttpResponse response) {
                            try {
                                int httpCode = response.getStatusLine().getStatusCode();

                                if (httpCode < 200 || httpCode >= 300) {
                                    logger.error("Send Failed: " + httpCode);
                                    logger.info("调用发送泊位状态接口-第三方接口1调用失败，保存到缓存队列!");
                                    return;
                                }
                                String responseBody = EntityUtils.toString(response.getEntity(), "utf-8");
                                logger.info("Response(" + httpCode + "): " + responseBody);
                            } catch (Exception e) {
                                logger.error("发送泊位状态异常", e);
                            } finally {
                                concurrency.release();
                            }
                        }

                        @Override
                        public void failed(Exception ex) {
                            logger.error("调用发送泊位状态接口失败！", ex);
                            try {
                                logger.info("调用发送泊位状态接口-第三方接口1调用failed，保存到缓存队列!");
                            } catch (Exception e) {
                                logger.error("failed error!", e);
                            } finally {
                                concurrency.release();
                            }
                        }

                        @Override
                        public void cancelled() {
                            logger.error("调用发送泊位状态接口cancelled.");
                            try {
                                logger.info("调用发送泊位状态接口-第三方接口1调用cancelled，保存到缓存队列!");
                            } catch (Exception e) {
                                logger.error("cancelled error!", e);
                            } finally {
                                concurrency.release();
                            }
                        }
                    }));

//                    for (Future<HttpResponse> future : futures) {
//                        int httpCode = future.get().getStatusLine().getStatusCode();
//
//                        String responseBody = EntityUtils.toString(future.get().getEntity(), "utf-8");
//                        logger.info("--------, future = " + responseBody);
//
//                        InnotekResponseBase innoResp = InnotekResponseBase.convertToInnotekResponse(responseBody);
//                        logger.info("发送泊位状态响应结果, innoResp = " + JSON.toJSONString(innoResp));
//                    }
                    // end of snippet
                } catch (Exception ex) {
                    logger.error("error!", ex);
                }
            }
        }).start();
        logger.debug("发送泊位状态fiber end..");
    }

    private static void setPostParams(HttpPost httpost, Map<String, Object> params) throws UnsupportedEncodingException {
        if (params == null) {
            return;
        }
        /**
         String content = JSON.toJSONString(params);
         httpost.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
         */

        //装载参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(null != params) {
            for(Map.Entry<String, Object> entry: params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        //设置参数到请求对象中
        httpost.setEntity(new UrlEncodedFormEntity(nvps, INPUT_CHARSET));
    }
}
