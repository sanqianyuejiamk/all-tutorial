package com.farabbit.springboot.controller;

import com.farabbit.springboot.service.BackendAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.retry.annotation.Retry;

import java.util.concurrent.ExecutionException;

/**
 * @author mengka
 * @Date 2021-12-01 15:29
 */
@Slf4j
@RestController
public class RetryController {

    @Autowired
    private BackendAService businessAService;

    RestTemplate restTemplate= new RestTemplate();

    /**
     *  http://127.0.0.1:8072/getInvoice
     *
     * @return
     */
    @GetMapping("/getInvoice")
    @Retry(name = "getInvoiceRetry", fallbackMethod = "getInvoiceFallback")
    public String getInvoice() {
        log.info("getInvoice() call starts here");
        ResponseEntity<String> entity= restTemplate.getForEntity("http://127.0.0.1:8071/world", String.class);
        log.info("Response :" + entity.getStatusCode());
        return entity.getBody();
    }

    public String getInvoiceFallback(Exception e) {
        log.info("---RESPONSE FROM FALLBACK METHOD---");
        return "SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!";
    }

    @GetMapping("failure")
    public String failure(){
        return businessAService.failure();
    }

    @GetMapping("futureTimeout")
    public String futureTimeout() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        String flux = businessAService.fluxTimeout2().get();
        long endTime = System.currentTimeMillis();
        log.info("-------futureTimeout, "+(endTime - start)+"ms");
        return flux+" "+(endTime - start)+"ms";
    }
}
