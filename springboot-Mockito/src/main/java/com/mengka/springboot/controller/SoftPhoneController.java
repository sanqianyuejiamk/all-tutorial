package com.mengka.springboot.controller;

import com.mengka.springboot.domain.Note;
import com.mengka.springboot.domain.SoftPhoneLog;
import com.mengka.springboot.service.CommandService;
import com.mengka.springboot.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mengka
 * @version 2021/4/18
 * @since
 */
@RestController
@RequestMapping("/sf")
public class SoftPhoneController {

    @Autowired
    private CommandService commandService;

    @Autowired
    private NetworkService networkService;

    @GetMapping("/log")
    public String log(){
        SoftPhoneLog softPhoneLog = new SoftPhoneLog();
        commandService.log(softPhoneLog);
        return null;
    }

    /**
     *  检测网络联通性的
     *  http://127.0.0.1:8071/sf/test
     *
     *  【问题】: 输入一个URL了后到底发生了什么？
     *
     * @param response
     * @param request
     */
    @GetMapping("/test")
    public void test(HttpServletResponse response, HttpServletRequest request) {
        networkService.testNetWork(request, response);
    }
}
