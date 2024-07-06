package com.mengka.threaddemo.controller;

import com.mengka.threaddemo.domain.Note;
import com.mengka.threaddemo.service.NoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/v1/api")
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    private List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);

    @Autowired
    private NoteService noteService;

    @GetMapping("/")
    public String main(Model model) {

        // pre-java 8
        if (logger.isDebugEnabled()) {
            logger.debug("Hello from Log4j 2 - num : {}", num);
        }

        // java 8 lambda, no need to check log level
        logger.info("Hello from Log4j 2 - num : {}", () -> num);

        model.addAttribute("tasks", num);
        return "welcome"; //view
    }

    /**
     *  
     *  https://www.baeldung.com/spring-security-method-security
     *
     * @param model
     * @return
     */
    @GetMapping("/t1")
    public ResponseEntity<List<Note>> t1(Model model) {
        List<Note> notes = noteService.findAll();
        return ResponseEntity.ok(notes);
    }


    private int getNum() {
        return 100;
    }

}