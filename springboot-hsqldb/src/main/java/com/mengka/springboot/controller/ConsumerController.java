package com.mengka.springboot.controller;

import com.mengka.springboot.model.UserReq;
import com.mengka.springboot.model.UserRsp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mengka
 * @Date 2022-01-14 21:22
 */
@Slf4j
@RestController
@RequestMapping(path = "/v1")
public class ConsumerController {

    @GetMapping("/hello")
    public ResponseEntity<String> listDepartments(){
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }

    @PostMapping("/taskId")
    public ResponseEntity<UserRsp> save(@RequestBody UserReq userReq) {
        UserRsp userRsp = new UserRsp().setTaskId("3001");
        return new ResponseEntity<>(userRsp, HttpStatus.OK);
    }
}
