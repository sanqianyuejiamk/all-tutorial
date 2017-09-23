package com.mengka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 *  http://127.0.0.1:8080/m/taa
 *
 * @author mengka
 * @description
 * @data 2016/04/11.
 */
@Controller
@RequestMapping("/m")
public class FreeMarkerController {

    private static final Logger logger = LoggerFactory.getLogger(FreeMarkerController.class);

    @RequestMapping(value = "/taa")
    public ModelAndView taa(HttpServletRequest request, Model model){
        logger.info("freeMarkerController index......");
        return new ModelAndView("index2", "mengka", "test AAA..");
    }

    @RequestMapping(value = "/tbb")
    public String tbb(HttpServletRequest request, Model model){
        logger.info("freeMarkerController index......");
        return "views/index";
    }

}
