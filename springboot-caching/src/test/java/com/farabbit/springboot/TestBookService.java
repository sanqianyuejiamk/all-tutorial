package com.farabbit.springboot;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.repository.BookRepository;
import com.farabbit.springboot.service.BookService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

/**
 * @author mengka
 * @Date 2024-06-16 15:34
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class TestBookService {

    @Autowired
    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Before
    public void before(){
        Book note = new Book().setName("数学o1").setPrice(100).setTenantId("2001");

        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(note));
    }

    @Test
    public void test_find_note(){
        Book oneNote = bookService.getBookById(1L);
        log.info("oneNote = {}",new Gson().toJson(oneNote));

        Assert.assertEquals("数学o1", oneNote.getName());
    }
}
