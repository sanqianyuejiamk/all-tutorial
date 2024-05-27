package com.mengka.springboot.controller;

import com.mengka.springboot.domain.Note;
import com.mengka.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mengka
 * @version 2021/4/17
 * @since
 */
@RestController
@RequestMapping("/v1")
public class HelloController {

    @Autowired
    BookService bookService;

    @GetMapping("/taa")
    public String taa(){
        Note note = bookService.findNote(20L);
        return note!=null?note.getTitle():null;
    }

    @GetMapping("/tbb")
    public String tbb(){
        Long noteNextId = bookService.getNextNoteIdSafe();
        return String.valueOf(noteNextId);
    }
}
