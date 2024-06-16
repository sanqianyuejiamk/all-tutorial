package com.farabbit.springboot.service;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Cacheable(value = "itemCache", key = "#id")
    public Book getBookById(long id){
        log.info("BookService getBookById id = "+id);
        Book book63 = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        return book63;
    }
}