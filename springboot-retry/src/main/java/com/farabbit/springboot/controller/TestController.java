package com.farabbit.springboot.controller;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.repository.BookRepository;
import com.farabbit.springboot.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
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

    @Autowired
    private MyService myService;

    @Autowired
    private RetryTemplate retryTemplate;

    /**
     *  http://127.0.0.1:8071/test1
     *
     * @return
     */
    @GetMapping("test1")
    public ResponseEntity<String> test1() {
        log.info("-------, 调用hello接口");

        try {
            myService.retryServiceWithCustomization(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("test2")
    public ResponseEntity<String> test2() {
        log.info("-------, 调用test2接口");


        retryTemplate.execute(arg0 -> {
            myService.templateRetryService();
            return null;
        });

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
