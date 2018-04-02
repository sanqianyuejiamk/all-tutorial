package com.mengka.springboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

/**
 * @author huangyy
 * @version cabbage-forward2.0,2018-04-02
 * @since cabbage-forward2.0
 */
@Controller
@RequestMapping("/v1/ace")
public class AceEditorController {

    static final Logger logger = LogManager.getLogger(AceEditorController.class);

    /**
     *  http://127.0.0.1:8073/v1/ace/rate3
     *
     *  https://ace.c9.io/
     *  https://github.com/ajaxorg/ace
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/rate3")
    public String rate3(Map<String, Object> model, Long id) {
        logger.info("CommonController rate id = {}", id);


        return "ace";
    }
}
