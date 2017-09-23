package com.mengka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * User: mengka
 * Date: 15-5-31-下午2:34
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @RequestMapping(value = "/login.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String userLogin(ModelMap map, HttpServletRequest request,
                            @RequestParam(required = false) String groupName) {
        return "mengka/login";
    }

    @RequestMapping(value = "/loginSystem.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(ModelMap map, HttpServletRequest request,
                        @RequestParam(required = true) String name,
                        @RequestParam(required = true) String password) {


        return "mengka/login";
    }


    @RequestMapping(value = "/logout.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(ModelMap map, HttpServletRequest request,
                         @RequestParam(required = false) String groupName) {

        return "mengka/login";
    }
}
