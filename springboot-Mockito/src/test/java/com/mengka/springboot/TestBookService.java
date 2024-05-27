package com.mengka.springboot;

import com.google.gson.Gson;
import com.mengka.springboot.domain.Note;
import com.mengka.springboot.manager.NoteManager;
import com.mengka.springboot.repository.NoteRepository;
import com.mengka.springboot.service.BookService;
import com.mengka.springboot.service.impl.BookServiceImpl;
import com.mengka.springboot.utils.TimeUtil;
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

import java.util.Date;
import java.util.Optional;

/**
 * @author mengka
 * @version 2021/4/17
 * @since
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class TestBookService {

    @Autowired
    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    NoteRepository noteRepository;

    @Mock
    NoteManager noteManager;

    @Before
    public void before(){
        Note note = Note.builder().title("mk note").content(TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS)).build();

        Mockito.when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        Mockito.when(noteManager.getNoteDescribe()).thenReturn("Mock note Describe");
    }

    @Test
    public void test_find_note(){
        Note oneNote = bookService.findNote(1L);
        log.info("oneNote = {}",new Gson().toJson(oneNote));

        Assert.assertEquals("mk note", oneNote.getTitle());
    }
}
