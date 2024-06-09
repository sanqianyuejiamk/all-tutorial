package com.farabbit.springboot.controller;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.domain.BookDTO;
import com.farabbit.springboot.domain.BookReq;
import com.farabbit.springboot.repository.BookRepository;
import com.farabbit.springboot.transfor.BookTransfer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

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

    /**
     *  http://127.0.0.1:8071/book/t1
     *
     * @param bookReq
     * @return
     */
    @RequestMapping(path = "/t1", method = RequestMethod.POST)
    public BookDTO t1(@Valid @RequestBody BookReq bookReq) {
        log.info("---user--- {}",new Gson().toJson(bookReq));

        Book book = bookRepository.findById(bookReq.getId()).get();

        return bookTransfer.toDTO(book);
    }
}
