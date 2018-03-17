//package com.mengka.springboot.fiber_01;
//
//import co.paralleluniverse.fibers.FiberAsync;
//import co.paralleluniverse.fibers.SuspendExecution;
//import co.paralleluniverse.fibers.Suspendable;
//import com.mengka.springboot.fiber_01.common.FooException;
//
///**
// * @author huangyy
// * @version cabbage-forward2.0,2018-03-17
// * @since cabbage-forward2.0
// */
//public class FiberFooClient implements FooClient {
//
//    private final AsyncFooClient asyncClient;
//
//    public FiberFooClient(AsyncFooClient asyncClient) {
//        this.asyncClient = asyncClient;
//    }
//
//    /**
//     *  We need to tell Quasar that our method is fiber-blocking (or “suspendable”), so we annotate it with @Suspendable.
//     *
//     * @param arg
//     * @return
//     * @throws FooException
//     * @throws InterruptedException
//     */
//    @Override
//    @Suspendable
//    public String op(final String arg) throws FooException, InterruptedException {
//        try {
//            return new FiberAsync<String, FooException>() {
//                @Override
//                protected void requestAsync() {
//                    asyncClient.asyncOp(arg, new FooCompletion<String>() {
//                        public void success(String result) {
//                            FiberAsync.this.asyncCompleted(result);
//                        }
//
//                        public void failure(FooException exception) {
//                            FiberAsync.this.asyncFailed(exception);
//                        }
//                    });
//                }
//            }.run();
//        } catch (SuspendExecution e) {
//            throw new AssertionError(e);
//        }
//    }
//}
