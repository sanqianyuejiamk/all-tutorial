package com.farabbit.threaddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.farabbit.threaddemo.model.ResponseMessage;
import com.farabbit.threaddemo.service.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.io.File;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author mengka
 * @Date 2024-06-30 20:37
 */
@Log4j2
@RequestMapping("/upload")
@Controller
public class UploadController {

    @Value("${d2cmanager.resourcespath}")
    private String resourcespath;

    @Autowired
    private FileStorageService storageService;

    @GetMapping("/t2")
    public ModelAndView hello() {
        final ModelAndView modelAndView = new ModelAndView("fileupload");
        modelAndView.addObject("name","Velocity");
        return modelAndView;
    }

    /**
     *  Java and Ajax file uploads
     *
     *  http://127.0.0.1:8073/upload/t1
     *
     * @return
     */
    @GetMapping("/t1")
    public ModelAndView hellot1() {
        final ModelAndView modelAndView = new ModelAndView("fileupload2");
        modelAndView.addObject("name","Velocity");
        return modelAndView;
    }

    @GetMapping("/t3")
    public ModelAndView hellot3() {
        final ModelAndView modelAndView = new ModelAndView("fileupload3");
        modelAndView.addObject("name","Velocity");
        return modelAndView;
    }

    /**
     *
     *  TODO: Spring WebFlux File upload example
     *
     *  》》上传地址：
     *  http://127.0.0.1:8073/upload/t3
     *
     *  /Users/hyy044101331/work_github/work_demo/spring-webflux-file-upload-example
     *
     *  https://www.bezkoder.com/spring-webflux-file-upload-example/
     *
     * @param filePartMono
     * @return
     */
    @PostMapping("/t4")
//    @PostMapping(value ="/t4", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Mono<ResponseEntity<ResponseMessage>> uploadFile(@RequestPart("inputFile") Mono<FilePart> filePartMono) {
        return storageService.save(filePartMono).map(
                (filename) -> ResponseEntity.ok().body(new ResponseMessage("Uploaded the file successfully: " + filename)));
    }

}
