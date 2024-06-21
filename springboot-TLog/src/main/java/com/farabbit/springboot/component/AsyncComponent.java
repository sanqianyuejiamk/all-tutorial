package com.farabbit.springboot.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import java.util.concurrent.Future;

/**
 * @author mengka
 * @Date 2021-12-16 13:38
 */
@Slf4j
@Component
public class AsyncComponent {

    @Async
    public Future<String> asyncMethodWithReturnType() {
        log.info("Execute method asynchronously " + Thread.currentThread().getName());
        try {
            //int aa = 2/0;
            Thread.sleep(5000);
            return new AsyncResult<>("hello world !!!!");
        } catch (final InterruptedException e) {

        }

        return null;
    }

    /**
     *  Execute method asynchronously with configured executor mengka-thread-1
     */
    @Async("threadPoolTaskExecutor")
    public void asyncMethodWithConfiguredExecutor() {
        System.out.println("Execute method asynchronously with configured executor " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (final InterruptedException e) {

        }
    }
}
