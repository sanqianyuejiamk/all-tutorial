package com.mengka.springboot.controller;

import com.mengka.springboot.domain.Book;
import com.mengka.springboot.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mengka
 * @Date 2021-12-15 17:31
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private BookRepository bookRepository;

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
