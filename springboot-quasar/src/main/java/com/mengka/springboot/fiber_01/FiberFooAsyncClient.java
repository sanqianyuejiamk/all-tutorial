//package com.mengka.springboot.fiber_01;
//
//import co.paralleluniverse.strands.SettableFuture;
//
//import java.util.concurrent.Future;
//
///**
// * @author huangyy
// * @version cabbage-forward2.0,2018-03-17
// * @since cabbage-forward2.0
// */
//public class FiberFooAsyncClient implements FooClient {
//    private final AsyncFooClient asyncClient;
//
//    public FiberFooClient(AsyncFooClient asyncClient) {
//        this.asyncClient = asyncClient;
//    }
//
//    @Override
//    public Future<String> asyncOp(String arg, FooCompletion<String> callback) {
//        final SettableFuture<T> future = new SettableFuture<>();
//        asyncClient.asyncOp(arg, callbackFuture(future, callback))
//        return future;
//    }
//
//    private static <T> FooCompletion<T> callbackFuture(final SettableFuture<T> future, final FooCompletion<T> callback) {
//        return new FooCompletion<T>() {
//            @Override
//            public void success(T result) {
//                future.set(result);
//                callback.completed(result);
//            }
//
//            @Override
//            public void failure(Exception ex) {
//                future.setException(ex);
//                callback.failed(ex);
//            }
//
//            @Override
//            public void cancelled() {
//                future.cancel(true);
//                callback.cancelled();
//            }
//        };
//    }
//}
