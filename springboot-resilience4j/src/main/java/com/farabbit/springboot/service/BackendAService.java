package com.farabbit.springboot.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

/**
 * @author mengka
 * @Date 2021-12-01 21:31
 */
@Slf4j
@Component(value = "backendAService")
public class BackendAService {

    private static final String BACKEND_A = "backendA";

    @CircuitBreaker(name = BACKEND_A)
    @Bulkhead(name = BACKEND_A)
    @Retry(name = BACKEND_A)
    public String failure() {
        log.info("BackendAService failure。。");
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    }


    @Bulkhead(name = BACKEND_A, type = Bulkhead.Type.THREADPOOL)
    @TimeLimiter(name = BACKEND_A)
    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "futureFallback")
    public CompletableFuture<String> fluxTimeout2() {

        Random random = new Random();
        int sleepTime = random.nextInt(5000);

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        //Try.run(() -> Thread.sleep(5000));


        return CompletableFuture.completedFuture("Hello World from backend A");
    }

    private CompletableFuture<String> futureFallback(TimeoutException ex) {
        return CompletableFuture.completedFuture("Recovered specific TimeoutException: " + ex.toString());
    }
}
