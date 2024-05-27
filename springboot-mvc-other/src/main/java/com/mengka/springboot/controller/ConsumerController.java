package com.mengka.springboot.controller;

import com.mengka.springboot.component.SimpleZooComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *   ./zkCli.sh -server 127.0.0.1:2181
 *
 *   ls2 /mengka/hyy044101331
 *   get /mengka/hyy044101331
 *
 * @author mengka
 * @Date 2022-01-14 21:22
 */
@Slf4j
@RestController
@RequestMapping(path = "/v1")
public class ConsumerController {

    @Autowired
    private SimpleZooComponent simpleZooComponent;

    @GetMapping("/hello")
    public ResponseEntity<String> listDepartments()throws Exception{

        String path = "/mengka/hyy044101331";
        simpleZooComponent.createNode(path);

        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }

    @GetMapping("/del")
    public ResponseEntity<String> delNode()throws Exception{

        String path = "/mengka/hyy044101331";
        String data = simpleZooComponent.getNodeDate(path);
        if(StringUtils.isNotBlank(data)) {
            simpleZooComponent.deleteNode(path);
        }
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }
}
