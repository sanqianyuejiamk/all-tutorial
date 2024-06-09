package com.mengka.demo.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

/**
 * @author mengka
 * @Date 2024-06-01 19:23
 */
@Slf4j
@RestController
public class CommonController {

    /**
     *  http://127.0.0.1:8072/t1
     * @return
     */
    @GetMapping("/t1")
    public ResponseEntity<String> t1() {
        log.info("common controller t1..");
        List<String> list = Arrays.asList("1","2","3","4","5","7");

        return ResponseEntity.ok("Hello World"+ new Gson().toJson(list));
    }
}
