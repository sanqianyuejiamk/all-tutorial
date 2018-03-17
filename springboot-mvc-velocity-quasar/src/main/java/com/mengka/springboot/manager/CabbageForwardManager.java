package com.mengka.springboot.manager;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;
import com.innotek.cabbageforward.api.protocol.model.BaseDock;
import com.innotek.cabbageforward.api.protocol.model.BerthHeartItem;
import com.innotek.cabbageforward.api.protocol.model.DeviceHeartItem;
import com.mengka.springboot.component.ForwardExecuter;
import com.mengka.springboot.util.HttpAsyncClientPool;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author huangyy
 * @version cabbage-forward2.0,2018-03-15
 * @since cabbage-forward2.0
 */
@Log4j2
@Service
public class CabbageForwardManager {

    public void forward()throws Exception{

//        String filePath = "/Users/hyy044101331/work_innotek/work_cabbage_forward_plugin_trunk2/cabbage-forward-plugins/forward-protocol-http/target/forward-protocol-http-1.0.4.jar";
//        String className = "com.innotek.cabbageforward.api.protocol.adapter.impl.ProtocolAdapterImpl";
//
//        BerthHeartItem berthHeartItem = new BerthHeartItem();
//        BaseDock baseDock = new BaseDock();
//        baseDock.setUrl("http://127.0.0.1:8087/v1/kv/a1");
//        baseDock.setProtocol(1);
//        berthHeartItem.setDock(baseDock);
//
//        Class<?>[] parameterTypes = new Class[]{BerthHeartItem.class};
//        Object[] args = new Object[]{berthHeartItem};
//
//        //构建转发程序执行器
//        ForwardExecuter forwardExecuter = ImmutableForwardExecuter.builder().packagePath(filePath)
//                .className(className)
//                .version("1.0.4")
//                .method("sendBerthHeart")
//                .maxResendCount(3)
//                .parameterTypes(parameterTypes)
//                .lastSentTime(new Date())
//                .args(args)
//                .build();
//
//        forwardExecuter.execute();
    }

    public void forward2()throws Exception{
        String url = "http://127.0.0.1:8053/v1/mq/send";
        //转换成请求参数
        Map<String, Object> param = new HashMap<String, Object>();

        final HttpGet httpPost = new HttpGet(url);
//        setPostParams(httpPost, param);

        final CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClientPool.getInstance().getHttpClient();

        log.info("------, fiber start..");

        new Fiber<Void>(new SuspendableRunnable() {
            @Override
            public void run() throws SuspendExecution, InterruptedException {
                try {
                    // snippet future calls
                    ArrayList<Future<HttpResponse>> futures = new ArrayList<>();

                    futures.add(httpAsyncClient.execute(httpPost, HttpClientContext.create(), new FutureCallback<HttpResponse>() {

                        @Override
                        public void completed(HttpResponse response) {
                            log.info("--------, " + response.getStatusLine().getStatusCode());
//                            log.info("--------2222, " + getHttpContent(response));
                        }

                        @Override
                        public void failed(Exception ex) {
                            log.info("------, failed");
                        }

                        @Override
                        public void cancelled() {
                            log.info("------, failed");
                        }
                    }));

                    for (Future<HttpResponse> future : futures) {
                        log.info("----, content = " + EntityUtils.toString(future.get().getEntity()));
                    }
                    // end of snippet
                } catch (Exception ex) {
                    log.error("error!", ex);
                }
            }
        }).start();

        log.info("------, fiber end..");
    }

    public static String getHttpContent(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String body = null;
        if (entity == null) {
            return null;
        }
        try {

            body = EntityUtils.toString(entity, "utf-8");

        } catch (ParseException e) {
            log.warn("the response's content inputstream is corrupt", e);
        } catch (IOException e) {
            log.warn("the response's content inputstream is corrupt", e);
        }
        return body;
    }
}
