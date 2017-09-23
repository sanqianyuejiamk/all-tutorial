package com.mengka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mengka
 * @date 2017/09/21.
 */
@Controller
@RequestMapping("/inno")
public class InnoController {

    @RequestMapping(value = "/aa.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String topic(ModelMap map, HttpServletRequest request,
                        @RequestParam(required = false) String groupName) {
        return "inno/aa";
    }
}
