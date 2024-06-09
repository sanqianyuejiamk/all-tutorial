package com.farabbit.springboot.controller;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class TestController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        log.info("-------, 调用hello接口");
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
