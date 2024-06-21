package com.farabbit.springboot.controller;

import com.farabbit.springboot.component.AsyncComponent;
import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.repository.BookRepository;
import com.farabbit.springboot.service.MengkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author mengka
 * @Date 2021-12-15 17:31
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AsyncComponent asyncComponent;

    @Autowired
    private MengkaService mengkaService;

    /**
     *  http://127.0.0.1:8071/t1
     *
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @GetMapping("t1")
    public ResponseEntity<String> t1() throws InterruptedException, ExecutionException {
        log.info("-------, 调用t1接口");

        mengkaService.demo1("044101331","x1");
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("testAsync")
    public ResponseEntity<String> testAsync() throws InterruptedException, ExecutionException {
        log.info("-------, 调用testAsync接口");


        log.info("Start - invoking an asynchronous method. " + Thread.currentThread().getName());
        final Future<String> future = asyncComponent.asyncMethodWithReturnType();

        while (true) {
            if (future.isDone()) {
                log.info("Result from asynchronous process - " + future.get());
                break;
            }
            log.info("Continue doing something else. ");
            Thread.sleep(1000);
        }

        return ResponseEntity.ok("Hello World");
    }

    /**
     *  http://127.0.0.1:8071/world
     *
     * @return
     */
    @GetMapping("world")
    public ResponseEntity<String> world() {
        log.info("-------, 调用hello接口");


        bookRepository.updateNameById("英语",1L);


        List<Book> books = bookRepository.findByNameAndPriceAndTenantId("数学",100L,"2002");

        return ResponseEntity.ok("Hello World");
    }
}
