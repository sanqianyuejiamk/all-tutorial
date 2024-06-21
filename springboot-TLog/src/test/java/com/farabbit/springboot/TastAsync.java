package com.farabbit.springboot;

import com.farabbit.springboot.component.AsyncComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author mengka
 * @Date 2021-12-16 13:57
 */
@Slf4j
public class TastAsync extends BaseTest {

    @Autowired
    private AsyncComponent asyncComponent;

    @Test
    public void testAsyncAnnotationForMethodsWithReturnType() throws InterruptedException, ExecutionException {
        System.out.println("Start - invoking an asynchronous method. " + Thread.currentThread().getName());
        final Future<String> future = asyncComponent.asyncMethodWithReturnType();

        while (true) {
            if (future.isDone()) {
                System.out.println("Result from asynchronous process - " + future.get());
                break;
            }
            System.out.println("Continue doing something else. ");
            Thread.sleep(1000);
        }
    }

    @Test
    public void testAsyncAnnotationForMethodsWithConfiguredExecutor() {
        System.out.println("Start - invoking an asynchronous method. ");
        asyncComponent.asyncMethodWithConfiguredExecutor();
        System.out.println("End - invoking an asynchronous method. ");
    }
}
