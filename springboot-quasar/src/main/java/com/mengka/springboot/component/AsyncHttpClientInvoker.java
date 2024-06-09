//package com.mengka.springboot.component;
//
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.UnknownHostException;
//import java.nio.charset.Charset;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.Future;
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
//import kilim.Pausable;
//import kilim.Task;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.Header;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpVersion;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.config.CookieSpecs;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.HttpDelete;
//import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpHead;
//import org.apache.http.client.methods.HttpOptions;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpPut;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.client.methods.HttpTrace;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.concurrent.FutureCallback;
//import org.apache.http.conn.ConnectTimeoutException;
//import org.apache.http.entity.ByteArrayEntity;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.InputStreamEntity;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.HttpMultipartMode;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.apache.http.impl.nio.client.HttpAsyncClients;
//import org.apache.http.impl.nio.reactor.IOReactorConfig;
//import org.apache.http.message.BasicNameValuePair;
//import org.springframework.beans.factory.DisposableBean;
//
///**
// * @author huangyy
// * @version cabbage-forward2.0,2018-03-17
// * @since cabbage-forward2.0
// */
//public class AsyncHttpClientInvoker extends AbstractAsyncHttpInvoker<AsyncHttpClientExecutionMeta> implements DisposableBean {
//
//    private static final int         PROC_COUNT     = Runtime.getRuntime().availableProcessors();
//    private static final int         RETRY_TIMES    = 3;
//    // 表示入参所属于的位置，是body中如req.body=LOCATION[a,b,c]
//    private static final String      BODY_LOCATION  = "LOCATION";
//
//    private HttpAsyncClientBuilder   builder;
//
//    private CloseableHttpAsyncClient httpClient;
//    private ReadWriteLock            httpClientLock = new ReentrantReadWriteLock();
//    private Lock                     r              = httpClientLock.readLock();
//    private Lock                     w              = httpClientLock.writeLock();
//
//    public AsyncHttpClientInvoker(){
//        builder = HttpAsyncClients.custom();
//        builder.setDefaultIOReactorConfig(IOReactorConfig.custom().setConnectTimeout(5000).setSoTimeout(10000).setIoThreadCount(PROC_COUNT + 1).setShutdownGracePeriod(5000).setRcvBufSize(1024 * 8).setSndBufSize(1024 * 8).setSoKeepAlive(true).setSoLinger(1000).setTcpNoDelay(true).build());
//        builder.setDefaultRequestConfig(RequestConfig.custom().setStaleConnectionCheckEnabled(false).setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).setRedirectsEnabled(false).build());
//        // 最大允许链接数，原则上无限制
//        builder.setMaxConnPerRoute(1024 * 1024);
//        builder.setMaxConnTotal(Integer.MAX_VALUE);
//        builder.setThreadFactory(new ThreadFactory() {
//
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r, "AsyncHttpClientInvoker-reactor-thread");
//            }
//        });
//        builder.disableCookieManagement();
//
//        // init http client
//        w.lock();
//        try {
//            if (this.httpClient == null) {
//                this.httpClient = builder.build();
//                this.httpClient.start();
//            }
//        } finally {
//            w.unlock();
//        }
//    }
//
//    @Override
//    public Object asyncInvokeMethod(ServiceMeta<AsyncHttpClientExecutionMeta> serviceMeta, Object[] args, long timeout,
//                                    InvokerContext invokerContext, BizStackTrace stackTrace)
//            throws InvokeMethodException,
//            InvokeMethodTargetException,
//            InvokeMethodTimeoutException,
//            Pausable {
//        final AsyncHttpClientExecutionMeta executionMeta = serviceMeta.getExecution();
//        final Map<String, Object> paramMap = buildParamMap(serviceMeta, args);
//        final ExpressionExecutor executor = new MapContextExpressionExecutor(paramMap, false);
//        final URI url = parseUrl(executionMeta, executor);
//        final String requestEncoding = getRequestEncoding(executionMeta, executor);
//        final String methodName = getMethod(executionMeta, executor);
//
//        final HttpRequestBase method;
//        RequestConfig.Builder reqConf = RequestConfig.custom().setSocketTimeout((int) timeout);
//        reqConf.setRedirectsEnabled(executionMeta.isRedirect());// 是否需要自动重定向
//        reqConf.setMaxRedirects(3);
//
//        final boolean isProxy = executionMeta.isProxy();// 是否是透传调用
//
//        // 给上层的异步listener指定一个超时时间, 这个超时时间比api超时时间长1s，作为系统等待的底线, 一旦超过则主动abort请求
//        invokerContext.listenerTimeout(timeout + 1000);
//
//        HttpRequestMethod methodType = null;
//        if (isProxy) {
//            method = HttpProxyHelper.buildProxyMethod4(executionMeta, args, url, invokerContext);
//        } else {
//            try {
//                methodType = HttpRequestMethod.valueOf(methodName.toUpperCase());
//            } catch (RuntimeException e) {
//                throw new InvokeMethodException("http invoke error: unknow http method " + methodName);
//            }
//            switch (methodType) {
//                case GET:
//                    method = new HttpGet(url.toString());
//                    final Class<?> returnType = getReturnType(serviceMeta);
//                    if (Map.class.equals(returnType)) {
//                        reqConf.setRedirectsEnabled(false);
//                    }
//                    break;
//                case POST:
//                    method = new HttpPost(url.toString());
//                    // method.setHeader("Content-Type", "application/x-www-form-urlencoded; charset="
//                    // + requestEncoding);
//                    break;
//                case MULITIPART:
//                    method = new HttpPost(url.toString());
//                    method.setHeader("Content-Type", "multipart/form-data");
//                    break;
//                case PUT:
//                    method = new HttpPut(url.toString());
//                    break;
//                case HEAD:
//                    method = new HttpHead(url.toString());
//                    break;
//                case DELETE:
//                    method = new HttpDelete(url.toString());
//                    break;
//                case TRACE:
//                    method = new HttpTrace(url.toString());
//                    break;
//                case OPTIONS:
//                    method = new HttpOptions(url.toString());
//                    break;
//                default:
//                    throw new InvokeMethodException("http invoke error: unknow http method " + methodName);
//            }
//            method.setProtocolVersion(HttpVersion.HTTP_1_1);
//
//            method.setHeader("Connection", "Keep-Alive");
//            method.setHeader("User-Agent", "AliOpenAPI/1.0");
//        }
//        final BizStackTraceElement stElement = stackTrace.createElement("invoker.httpClient");
//        stElement.addBizValue("req.url", url);
//        stElement.addBizValue("req.method", methodName);
//
//        if (!isProxy) {
//            setRequestHeader(method, executionMeta, executor, stElement);
//            setRequestBody(method, methodType, executionMeta, executor, paramMap, requestEncoding, stElement);
//        }
//
//        method.setConfig(reqConf.build());
//        return asyncRetryable(serviceMeta, args, executionMeta, executor, url, method, invokerContext, isProxy,
//                stElement);
//    }
//
//    // 可异步重试的代码块
//    private Object asyncRetryable(final ServiceMeta<AsyncHttpClientExecutionMeta> serviceMeta, final Object[] args,
//                                  final AsyncHttpClientExecutionMeta executionMeta, final ExpressionExecutor executor,
//                                  final URI url, final HttpRequestBase method, final InvokerContext invokerContext,
//                                  final boolean isProxy, final BizStackTraceElement stElement)
//            throws InvokeMethodException,
//            InvokeMethodTargetException,
//            InvokeMethodTimeoutException,
//            Pausable {
//        // 异步调用得到的result
//        final ObjectReference<Object> resultHolder = new ObjectReference<Object>();
//        // 异步调用发生的exception
//        final ObjectReference<InvokeMethodException> exHolder = new ObjectReference<InvokeMethodException>();
//
//        // 重试次数记录
//        final ObjectReference<Integer> retryTimes = new ObjectReference<Integer>();
//        retryTimes.setObj(0);
//        // 是否需要重试
//        final ObjectReference<Boolean> needRetry = new ObjectReference<Boolean>();
//
//        // 在asyncHttpClient4使用主线程执行callback的情形下，指示后续流程不要yield
//        final ObjectReference<CountDownLatch> callbackReturned = new ObjectReference<CountDownLatch>();
//        final Lock callbackReturnedLock = new ReentrantLock();
//
//        // 防止callback过快返回从而在主线程suspend listener之前调用resume
//        final AtomicBoolean listenerSuspended = new AtomicBoolean();
//
//        while (true) {
//            // 清空callback returned状态
//            setCallbackReturned(callbackReturnedLock, callbackReturned, null);
//            Future<HttpResponse> f = null;
//            listenerSuspended.set(false);
//
//            r.lock();
//            try {
//                f = httpClient.execute(method, new FutureCallback<HttpResponse>() {
//
//                    @Override
//                    public void completed(HttpResponse response) {
//                        setCallbackReturned(callbackReturnedLock, callbackReturned, new CountDownLatch(1));
//                        try {
//                            int statusCode = response.getStatusLine().getStatusCode();
//                            stElement.addBizValue("rsp.statusCode", statusCode);
//                            stElement.addBizValue("rsp.headers", response.getAllHeaders());
//
//                            if (isProxy && executionMeta.isDirectResponse()) {
//                                // response透传, 由invoker直接向客户端输出response内容，不用再唤醒continuation走servlet流程
//                                try {
//                                    resultHolder.setObj(HttpProxyHelper.writeProxyResponse4(method, invokerContext,
//                                            response));
//                                } catch (InvokeMethodException e) {
//                                    exHolder.setObj(e);
//                                }
//                            } else {
//                                try {
//                                    resultHolder.setObj(buildResult(httpClient, serviceMeta, response, executor));
//                                } catch (Exception e) {
//                                    OceanLog.system.error("Build result error, service: " + serviceMeta.toString());
//                                    exHolder.setObj(ensureInvokeMethodEx(e));
//                                }
//                            }
//                        } finally {
//                            if (listenerSuspended.get()) invokerContext.listenerResume();
//                            callbackReturned.getObj().countDown();
//                        }
//                    }
//
//                    @Override
//                    public void failed(Exception ex) {
//                        setCallbackReturned(callbackReturnedLock, callbackReturned, new CountDownLatch(1));
//                        try {
//                            int curRetryTimes = retryTimes.getObj();
//                            // 将底层异常分情况包装成methodInvoke层面的异常返回
//                            if (ex instanceof ConnectTimeoutException) {
//                                needRetry.setObj(needRetry(ex, curRetryTimes));
//                                ex = new InvokeMethodConnectException("http invoke connect error:(URL:" + url + "):"
//                                        + ex.getMessage(), ex);
//                            } else if (ex instanceof IOException) {
//                                needRetry.setObj(needRetry(ex, curRetryTimes));
//                                if (ex instanceof java.net.SocketTimeoutException
//                                        || (ex.getMessage() != null && ex.getMessage().contains("java.net.SocketTimeoutException"))) {
//                                    ex = new InvokeMethodTimeoutException("http invoke read timed out error:(URL:"
//                                            + url + "):" + ex.getMessage(), ex);
//                                } else if (ex instanceof java.net.UnknownHostException
//                                        || (ex.getMessage() != null && ex.getMessage().contains("java.net.UnknownHostException"))) {
//                                    ex = new InvokeMethodConnectException("http invoke UnknownHost error:(URL:" + url
//                                            + "):" + ex.getMessage(), ex);
//                                } else {
//                                    ex = new InvokeMethodException("http invoke io error: URL=" + url + " exception:"
//                                            + ex.getMessage(), ex);
//                                }
//                            } else {
//                                // other exception
//                                needRetry.setObj(needRetry(ex, curRetryTimes));
//                                ex = new InvokeMethodException(
//                                        "http invoke error:(URL:" + url + "):" + ex.getMessage(),
//                                        ex);
//                            }
//                            if (needRetry.getObj() == null || !needRetry.getObj()) {
//                                // 若不需重试
//                                exHolder.setObj(ensureInvokeMethodEx(ex));
//                            }
//                        } finally {
//                            if (listenerSuspended.get()) invokerContext.listenerResume();
//                            callbackReturned.getObj().countDown();
//                        }
//                    }
//
//                    @Override
//                    public void cancelled() {
//                        setCallbackReturned(callbackReturnedLock, callbackReturned, new CountDownLatch(1));
//                        try {
//                            InvokeMethodException error = new InvokeMethodException("Async httpClient invoke to '"
//                                    + url + "' cancelled.");
//                            exHolder.setObj(error);
//                        } finally {
//                            if (listenerSuspended.get()) invokerContext.listenerResume();
//                            callbackReturned.getObj().countDown();
//                        }
//                    }
//                });
//            } catch (IllegalStateException e) {
//                if (e.getMessage().contains("I/O reactor status: STOPPED")) {
//                    // 目前http async
//                    // client小概率出现"java.lang.IllegalStateException: Request cannot be executed; I/O reactor status: STOPPED",
//                    // 必须重启httpClient解决, 并记录日志
//                    OceanLog.system.error("HttpAsyncClient reactor stopped, restarting...", e);
//                    // restart http client
//                    r.unlock();
//                    w.lock();
//                    try {
//                        if (this.httpClient != null) try {
//                            this.httpClient.close();
//                        } catch (IOException e1) {
//                        }
//                        this.httpClient = this.builder.build();
//                        this.httpClient.start();
//                    } finally {
//                        r.lock();// 锁降级
//                        w.unlock();
//                    }
//                    continue;// retry
//                } else {
//                    throw e;// 非“status: STOPPED”错误，直接抛出
//                }
//            } finally {
//                r.unlock();
//            }
//
//            if (callbackReturned.getObj() == null) {
//                // 只有当callback还没回来的情况下才调用这里的逻辑, 这段逻辑需要callbackReturned设置锁保护
//                callbackReturnedLock.lock();
//                try {
//                    if (callbackReturned.getObj() == null) {// double check
//                        // 将listener层面的执行挂起
//                        invokerContext.listenerSuspend();
//                        listenerSuspended.set(true);
//                    }
//                } finally {
//                    callbackReturnedLock.unlock();
//                    // 协程挂起, 等待回调唤醒, 若程序以非常小的概率在unlock之后、yield之前完成了callback调用，则会超时, 目前只能这样
//                    if (callbackReturned.getObj() == null) Task.yield();
//                }
//            } else {
//                // 如果到了这里说明callback已经赶在主线程yield之前就迅速回来了，那么就不能再yield了，等待callback逻辑执行结束后就直接继续后续逻辑
//                try {
//                    callbackReturned.getObj().await(1, TimeUnit.SECONDS);// callback内部逻辑应该很快，等1s已经不得了了
//                } catch (InterruptedException e) {
//                    throw new InvokeMethodException("Callback logic timeout!", e);
//                }
//            }
//
//            // 协程被listener线程唤醒，先处理listener超时场景, 再处理最终调用结果
//            if (invokerContext.isListenerTimeout()) {
//                resultHolder.setObj(null);// listener超时，就算service返回了正常结果，也放弃
//                // listener超时作为系统等待的底线，主动abort请求
//                f.cancel(true);
//                InvokeMethodException ex = new InvokeMethodTimeoutException(
//                        "Async service: '"
//                                + url
//                                + "' does not respond within the api timeout.");
//                exHolder.setObj(ex);
//                break;
//            } else if (needRetry.getObj() != null && needRetry.getObj()) {
//                // 重试准备
//                int retryTime = retryTimes.getObj() + 1;
//                retryTimes.setObj(retryTime);
//                if (OceanLog.provider.isWarnEnabled()) {
//                    OceanLog.provider.warn("Retry" + retryTime + "times for " + serviceMeta.toString());
//                }
//                method.reset();
//            } else {
//                // 不需重试
//                break;
//            }
//        }
//
//        // 处理最终调用结果
//        if (resultHolder.getObj() != null) return resultHolder.getObj();
//        if (exHolder.getObj() != null) throw exHolder.getObj();
//        return null;
//    }
//
//    private void setCallbackReturned(Lock lock, ObjectReference<CountDownLatch> objRef, CountDownLatch obj) {
//        lock.lock();
//        try {
//            objRef.setObj(obj);
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    private InvokeMethodException ensureInvokeMethodEx(Exception ex) {
//        if (ex instanceof InvokeMethodException) return (InvokeMethodException) ex;
//        return new InvokeMethodException(ex.getMessage(), ex);
//    }
//
//    private boolean needRetry(Exception e, int times) {
//        if (((e instanceof ConnectTimeoutException) || (e instanceof UnknownHostException) || (e.getMessage() != null && e.getMessage().contains("java.net.UnknownHostException")))
//                && times < RETRY_TIMES) {
//            return true;
//        }
//        return false;
//    }
//
//    private Object buildResult(CloseableHttpAsyncClient httpClient,
//                               ServiceMeta<AsyncHttpClientExecutionMeta> serviceMeta, HttpResponse response,
//                               ExpressionExecutor executor) throws IllegalStateException, IOException,
//            InvokeMethodException {
//        final Class<?> returnType = getReturnType(serviceMeta);
//        if (returnType == null) {
//            return null;
//        }
//        int statusCode = response.getStatusLine().getStatusCode();
//        String statusText = response.getStatusLine().getReasonPhrase();
//        // 非Map result不处理 [200, 299]的response
//        if (!Map.class.equals(returnType)) {
//            if (statusCode >= 299 || statusCode < 200) {
//                throw new InvokeMethodException("http invoke error, status: " + statusCode + " " + statusText);
//            }
//        }
//        if (InputStream.class.equals(returnType)) {
//            return response.getEntity().getContent();
//        }
//        final String encoding = getResponseDefaultEncoding(serviceMeta.getExecution(), executor);
//        if (String.class.equals(returnType)) {
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            response.getEntity().writeTo(os);
//            return new String(os.toByteArray(), encoding);
//        } else if (returnType.isArray() && Byte.TYPE.equals(returnType.getComponentType())) {
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            response.getEntity().writeTo(os);
//            return os.toByteArray();
//        } else if (Map.class.equals(returnType)) {
//            Map<String, Object> result = new LinkedHashMap<String, Object>();
//            result.put(APIInvokeConstant.KEY_StatusCode, statusCode);
//            result.put(APIInvokeConstant.KEY_StatusText, statusText);
//            Header[] headers = response.getAllHeaders();
//            if (headers != null) {
//                for (Header header : headers) {
//                    // 支持同一name多个value
//                    // 多个value的值由一个List结构存储
//                    if (result.containsKey(header.getName())) {
//                        if (result.get(header.getName()) instanceof List) {
//                            ((List<Object>) result.get(header.getName())).add(header.getValue());
//                        } else {
//                            List<Object> headerValues = new ArrayList<Object>();
//                            headerValues.add(result.get(header.getName()));
//                            headerValues.add(header.getValue());
//                            result.put(header.getName(), headerValues);
//                        }
//                    } else {
//                        result.put(header.getName(), header.getValue());
//                    }
//                }
//            }
//            String responseBody = resolveResponseBody(response, encoding);
//            if (serviceMeta.getExecution().getResponse().isBodyToMap()) {
//                result.put(APIInvokeConstant.KEY_Body, JsonMapper.json2map(responseBody));
//            } else {
//                result.put(APIInvokeConstant.KEY_Body, responseBody);
//            }
//            return result;
//        } else {
//            String val = resolveResponseBody(response, encoding);
//            try {
//                return ValueParseUtil.parseStringValue(val, returnType);
//            } catch (Exception e) {
//                OceanLog.provider.error("Http response is not a valid json while return type is set to 'Object'. Message: "
//                        + e.getMessage() + ", result: " + StringUtils.abbreviate(val, 20));
//                return val;
//            }
//        }
//    }
//
//    private String resolveResponseBody(HttpResponse response, String encoding) throws IOException {
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        response.getEntity().writeTo(os);
//        byte[] bs = os.toByteArray();
//        if (encoding == null) return new String(bs);
//        return new String(bs, encoding);
//    }
//
//    private void setRequestBody(HttpRequestBase method, HttpRequestMethod methodType,
//                                final AsyncHttpClientExecutionMeta executionMeta, final ExpressionExecutor executor,
//                                Map<String, Object> paramMap, final String encoding, BizStackTraceElement stElement)
//            throws InvokeMethodException {
//        final String body = executionMeta.getRequest().getBody();
//        Object bodyObject = null;
//        boolean isObjectBody = false;
//        if (StringUtils.isNotBlank(body)) {
//            if (ParamExpression.isExpression(body, startFlag, endFlag)) {
//                isObjectBody = true;
//                final ParamExpression pe = new ParamExpression(body, Object.class, startFlag, endFlag);
//                try {
//                    bodyObject = pe.parse(executor);
//                } catch (final ParseException e) {
//                    throw new InvokeMethodException("get request body error:" + e.getMessage(), e);
//                }
//                // 表示入参所属于的位置，是body中如req.body=LOCATION[a,b]
//            } else if (body.startsWith(BODY_LOCATION)) {
//                String sub = body.substring(BODY_LOCATION.length());
//                if (StringUtils.isNotBlank(sub)) {
//                    isObjectBody = true;
//                    if (sub.startsWith("[")) {
//                        String[] array = JSONArray.parseArray(sub).toArray(new String[0]);
//                        if (array != null && array.length > 0) {
//                            Map<String, Object> inParam = new HashMap<String, Object>();
//                            for (String key : array) {
//                                inParam.put(key, paramMap.get(key));
//                            }
//                            bodyObject = inParam;
//                        }
//                    } else {
//                        Map<String, String> paramLocation = JSON.parseObject(sub, Map.class);
//                        Map<String, Object> userHeader = new HashMap<String, Object>();
//                        Map<String, Object> userQuery = new HashMap<String, Object>();
//                        Map<String, Object> userBody = new HashMap<String, Object>();
//                        if (paramLocation != null && paramLocation.size() > 0) {
//                            for (Entry<String, String> entry : paramLocation.entrySet()) {
//                                switch (ParamLocation.valueOf(entry.getValue())) {
//                                    case head: {
//                                        userHeader.put(entry.getKey(), paramMap.get(entry.getKey()));
//                                        break;
//                                    }
//                                    case query: {
//                                        userQuery.put(entry.getKey(), paramMap.get(entry.getKey()));
//                                        break;
//                                    }
//                                    case body: {
//                                        userBody.put(entry.getKey(), paramMap.get(entry.getKey()));
//                                        break;
//                                    }
//                                }
//                            }
//
//                            // init query url
//                            if (userQuery != null && userQuery.size() > 0) {
//                                try {
//                                    URIBuilder builder = new URIBuilder(method.getURI().toString());
//                                    for (final Map.Entry<?, ?> entry : userQuery.entrySet()) {
//                                        final Object key = entry.getKey();
//                                        final Object value = entry.getValue();
//                                        if (key == null || value == null) {
//                                            continue;
//                                        }
//                                        builder.addParameter(key.toString(), value.toString());
//                                    }
//                                    method.setURI(builder.build());
//                                } catch (URISyntaxException e) {
//                                    throw new InvokeMethodException("parse http url error:" + e.getMessage(), e);
//                                }
//                            }
//
//                            // init header
//                            if (userHeader != null && userHeader.size() > 0) {
//                                for (Map.Entry<String, Object> entry : userHeader.entrySet()) {
//                                    method.removeHeaders(entry.getKey());
//                                    method.addHeader(entry.getKey(), (String) entry.getValue());
//                                }
//                                stElement.addBizValue("req.paramHeaders", userHeader);
//                            }
//
//                            // init body param
//                            if (userBody != null && userBody.size() > 0) {
//                                bodyObject = userBody;
//                            }
//
//                        }
//                    }
//
//                }
//            }
//        }
//        stElement.addBizValue("req.body", bodyObject);
//        if (method instanceof HttpEntityEnclosingRequestBase) {
//            HttpEntityEnclosingRequestBase m = (HttpEntityEnclosingRequestBase) method;
//            // 为能够带entity的req设置entity
//            if (!HttpRequestMethod.MULITIPART.equals(methodType)) {
//                // 非multipart
//                if (isObjectBody) {
//                    if (bodyObject == null) {
//                        return;
//                    } else if (bodyObject instanceof InputStream) {
//                        m.setEntity(new InputStreamEntity((InputStream) bodyObject));
//                    } else if (bodyObject instanceof byte[]) {
//                        m.setEntity(new ByteArrayEntity((byte[]) bodyObject));
//                    } else if (bodyObject instanceof Map) {
//                        List<NameValuePair> params = new ArrayList<NameValuePair>();
//                        for (final Map.Entry<?, ?> entry : ((Map<?, ?>) bodyObject).entrySet()) {
//                            final Object key = entry.getKey();
//                            final Object value = entry.getValue();
//                            if (key == null || value == null) {
//                                continue;
//                            }
//                            params.add(new BasicNameValuePair(key.toString(), value.toString()));
//                        }
//                        try {
//                            m.setEntity(new org.apache.http.client.entity.UrlEncodedFormEntity(params, encoding));
//                        } catch (UnsupportedEncodingException e) {
//                            throw new InvokeMethodException("Unsupported encoding.", e);
//                        }
//                    } else {
//                        m.setEntity(new StringEntity(bodyObject.toString(), encoding));
//                    }
//                } else {
//                    m.setEntity(new StringEntity(body, encoding));
//                }
//            } else {
//                // multipart情形，可能是文件上传，也可能是普通传参
//                if (isObjectBody) {
//                    if (bodyObject == null) {
//                        return;
//                    } else if (bodyObject instanceof Map) {
//                        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//                        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//                        builder.setCharset(Charset.forName(encoding));
//                        for (final Map.Entry<?, ?> entry : ((Map<?, ?>) bodyObject).entrySet()) {
//                            final Object key = entry.getKey();
//                            final Object value = entry.getValue();
//                            if (key == null || value == null) {
//                                continue;
//                            }
//                            // if (value instanceof PartSource) {
//                            // multiPost.addPart(new FilePart(key.toString(), (PartSource) value));
//                            // } else
//                            if (value instanceof UploadFileItem[]) {
//                                UploadFileItem[] fs = (UploadFileItem[]) value;
//                                if (fs.length > 0) {
//                                    try {
//                                        for (UploadFileItem item : fs)
//                                            if (item != null) builder.addBinaryBody(key.toString(),
//                                                    item.getInputStream(),
//                                                    ContentType.DEFAULT_BINARY,
//                                                    item.getFileName());
//                                    } catch (IOException e) {
//                                        throw new InvokeMethodException(
//                                                "IO exception when obtaining uploaded file item's stream.",
//                                                e);
//                                    }
//                                }
//                            } else if (value instanceof InputStream) {
//                                InputStream istream = (InputStream) value;
//                                builder.addBinaryBody(key.toString(), istream, ContentType.DEFAULT_BINARY,
//                                        parseFileName(key.toString(), executionMeta, executor));
//                            } else if (value instanceof byte[]) {
//                                builder.addBinaryBody(key.toString(), (byte[]) value, ContentType.DEFAULT_BINARY,
//                                        parseFileName(key.toString(), executionMeta, executor));
//                            } else {
//                                builder.addTextBody(key.toString(), value.toString());
//                            }
//                        }
//                        m.setEntity(builder.build());
//                    } else {
//                        throw new InvokeMethodException("unsport http multipart post request body:" + body);
//                    }
//                }
//            }
//        }
//    }
//
//    private void setRequestHeader(HttpRequestBase method, AsyncHttpClientExecutionMeta executionMeta,
//                                  ExpressionExecutor executor, BizStackTraceElement stElement) {
//        Map<String, String> headers = getRequestHeaders(executionMeta, executor);
//        if (headers != null) {
//            for (Map.Entry<String, String> entry : headers.entrySet()) {
//                method.removeHeaders(entry.getKey());
//                method.addHeader(entry.getKey(), entry.getValue());
//            }
//            stElement.addBizValue("req.headers", headers);
//        }
//    }
//
//    @Override
//    public Class<AsyncHttpClientExecutionMeta> getExeMetaClass() {
//        return AsyncHttpClientExecutionMeta.class;
//    }
//
//    @Override
//    public String getInvokerName() {
//        return "asyncHttpClient";
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        if (this.httpClient != null) httpClient.close();
//    }
//
//}