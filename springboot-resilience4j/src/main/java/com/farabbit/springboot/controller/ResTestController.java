package com.farabbit.springboot.controller;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.repository.BookRepository;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * @author mengka
 * @Date 2021-11-28 15:45
 */
@Slf4j
@RestController
public class ResTestController {

    @Autowired
    private BookRepository bookRepository;

//    private TimeLimiter ourTimeLimiter = TimeLimiter.of(TimeLimiterConfig.custom()
//            .timeoutDuration(Duration.ofMillis(5000)).build());


//    @GetMapping("/author/resilience4j")
//    public Callable<String> getWithResilience4jTimeLimiter(@RequestParam Long id) {
//        log.info("-------, 调用resilience4j接口");
//        return TimeLimiter.decorateFutureSupplier(ourTimeLimiter, () ->
//                CompletableFuture.supplyAsync(() -> {
//                    bookRepository.wasteTime();
//                    return bookRepository.findById(id)
//                            .map(Book::getName)
//                            .orElse("No book found for this title.");
//                }));
//    }

    @GetMapping("/test2")
    private Flux<Book> getAllEmployees() {
        return Flux.fromIterable(bookRepository.findAll());
    }
}
