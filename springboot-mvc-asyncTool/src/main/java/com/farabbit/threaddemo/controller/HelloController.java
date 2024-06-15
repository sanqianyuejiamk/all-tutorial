package com.farabbit.threaddemo.controller;

import com.farabbit.threaddemo.manager.MengkaManager;
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
@RequestMapping("/t2")
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    private List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);

    @Autowired
    private MengkaManager mengkaManager;

    /**
     *   http://127.0.0.1:8073/t2/t1
     *
     *   https://gitee.com/jd-platform-opensource/asyncTool/blob/master/QuickStart.md
     *
     * @return
     */
    @GetMapping("/t1")
    public ResponseEntity<String> t1() {

        Integer count = 13;//userMapper.myCount();

        mengkaManager.test();

        return ResponseEntity.ok("Hello World count = "+count);
    }

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

    private int getNum() {
        return 100;
    }

}