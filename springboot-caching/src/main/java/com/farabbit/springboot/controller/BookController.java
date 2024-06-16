package com.farabbit.springboot.controller;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.domain.BookDTO;
import com.farabbit.springboot.domain.BookReq;
import com.farabbit.springboot.repository.BookRepository;
import com.farabbit.springboot.service.BookService;
import com.farabbit.springboot.transfor.BookTransfer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.Date;

/**
 * @author mengka
 * @Date 2022-01-22 21:11
 */
@Slf4j
@RestController
@Validated
@RequestMapping(path = "/book")
public class BookController {

    private final BookTransfer bookTransfer = BookTransfer.INSTANCE;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    /**
     *  http://127.0.0.1:8071/book/t1
     *
     * @param bookReq
     * @return
     */
    @RequestMapping(path = "/t1", method = RequestMethod.POST)
    public BookDTO t1(@RequestBody BookReq bookReq) {
        log.info("---user--- {}",new Gson().toJson(bookReq));

        Book book = bookRepository.findById(bookReq.getId()).get();

        return bookTransfer.toDTO(book);
    }

    @GetMapping("/t2")
    public String t2() {
        log.info("---user--- ");

        Book book = new Book().setName("数学x1").setPrice(101).setTenantId("2001").setCreatedAt(new Date());
        book.addFeature("x1","test111");
        bookRepository.save(book);

        Book book63 = bookRepository.findById(67L).get();
        log.info("content = {}",book63.getFeature("x1"));

        book63.setCreatedAt(new Date());
        book63.addFeature("x2","test222");
        book63.addFeature("x3","test333");
        bookRepository.save(book63);

        return "Hello world";
    }

    /**
     * http://127.0.0.1:8072/book/t3
     *
     *  ./redis-cli
     *
     *  127.0.0.1:6379> keys *itemCache*
     * 1) "itemCache::67"
     * 2) "itemCache::68"
     *
     *
     * @return
     */
    @GetMapping("/t3")
    public String t3() {
        log.info("---user--- ");

        Book book = new Book().setName("数学o1").setPrice(100).setTenantId("2001");
        Book book62 = bookService.insertBook(book);

        long bookId = book62.getId();

        Book book63 = bookService.getBookById(bookId);
        log.info("content = {}",book63.getFeature("x1"));

        Book book64 = bookService.getBookById(bookId);

        bookService.editBook(book64);
        Book book65 = bookService.getBookById(bookId);

        bookService.removeBookById(bookId);
        Book book66 = bookService.getBookById(bookId);
        return "Hello world";
    }
}
