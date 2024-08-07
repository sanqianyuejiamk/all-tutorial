package com.mengka.threaddemo.controller;

import com.mengka.threaddemo.model.User;
import com.mengka.threaddemo.service.DemoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/t2")
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    private List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);

    @Autowired
    private DemoService demoService;

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

    @GetMapping("/lock")
    public ResponseEntity<String> t1(@RequestParam String id) throws InterruptedException {

        User user = new User().setId(id);
        demoService.customMethod(user);

        return ResponseEntity.ok("Hello World");
    }

    private int getNum() {
        return 100;
    }

}