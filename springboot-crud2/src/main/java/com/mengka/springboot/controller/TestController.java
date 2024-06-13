package com.mengka.springboot.controller;

import com.mengka.springboot.domain.Book;
import com.mengka.springboot.domain.Note;
import com.mengka.springboot.repository.BookRepository;
import com.mengka.springboot.service.NoteService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
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
    private NoteService noteService;

    private static final boolean bucket4j = false;
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

        if(bucket4j){
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        return ResponseEntity.ok("Hello World");
    }

    /**
     *  分页查询
     *
     *  http://127.0.0.1:8071/t1/2
     *
     *  【0,10】第一页
     *  【1,10】第二页
     *  【2,10】第三页
     *
     * @param pageNum
     * @return
     */
    @GetMapping("t1/{pageNum}")
    public ResponseEntity<String> t1Page(@PathVariable int pageNum) {
        log.info("-------, 调用t1 page接口");

        Page<Note> list = noteService.getNoteList(pageNum,10);
        Iterator<Note> u = list.iterator();
        while (u.hasNext()){
            log.info(new Gson().toJson(u.next()));
        }

        return ResponseEntity.ok("Hello World");
    }
}
