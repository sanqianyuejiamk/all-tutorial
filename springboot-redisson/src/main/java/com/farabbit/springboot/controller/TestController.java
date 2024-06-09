package com.farabbit.springboot.controller;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.repository.BookRepository;
import com.farabbit.springboot.service.BookService;
import com.farabbit.springboot.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.*;
import java.util.stream.IntStream;


@Slf4j
@RestController
public class TestController {

    @Autowired
    private BookRepository bookRepository;

    private static final BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>(10);//有界队列

    @Autowired
    private BookService bookService;

    /**
     *  http://127.0.0.1:8071/world
     *
     * @return
     */
    @GetMapping("world")
    public ResponseEntity<String> world() {
        log.info("-------, 调用hello接口");

        Object obj = bookService.getMap("test1","k1");

        bookService.lockAcquire("name11");
        bookService.setMap("test1","k1","value2");

        Object obj2 = bookService.getMap("test1","k1");

        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        log.info("-------, 调用hello接口");


        final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 30,
                TimeUnit.SECONDS, queue, Executors.defaultThreadFactory());

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());


        IntStream.rangeClosed(1, 100)
                //.boxed()
                //.sorted(Collections.reverseOrder())
                .forEach(counter -> {
                    Future<String> future = executor.submit(()->{
                        bookRepository.wasteTime();
                        String result = "[Just for test.."+ TimeUtil.toDate(new Date(),TimeUtil.format_1);
                        log.info("-------, result = "+result);
                        return result;
                    });
//                    try {
//                        String result = future.get();
//                        log.info("-------, result = "+result);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
                });

//        Future<String> future = executor.submit(()->{
//           return "[Just for test.."+ TimeUtil.toDate(new Date(),TimeUtil.format_1);
//        });

        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/author/transactional")
    @Transactional(timeout = 10)
    public String getWithTransactionTimeout(@RequestParam Long title) {
        //bookRepository.wasteTime();
        return bookRepository.findById(title)
                .map(Book::getName)
                .orElse("No book found for this title.");
    }
}
