package com.farabbit.threaddemo.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;

@Log4j2
@RequestMapping("/")
@Controller
public class HelloController {

    @Value("${d2cmanager.resourcespath}")
    private String resourcespath;

    @GetMapping("/hello-world")
    public ModelAndView hello() {
        final ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("name","Velocity");
        return modelAndView;
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView mysqlsqlUpload(@RequestParam(value = "inputFile", required = false) MultipartFile file) {
        final ModelAndView modelAndView = new ModelAndView("success");
        log.error("调用数据库初始化SQL上传接口 {}",file.getOriginalFilename());
        String destination = resourcespath +"/"+file.getOriginalFilename();
        try {
            //保存文件
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(destination));
            log.info("上传数据库初始化SQL destination = {}",destination);
        }catch (Exception e){
            log.error("上传文件异常",e);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", -1);
            jsonObject.put("message", "系统异常");
            modelAndView.addObject("result",jsonObject.toString());
            return modelAndView;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        jsonObject.put("destination", destination);
        modelAndView.addObject("result", jsonObject.toString());
        return modelAndView;
    }
}
