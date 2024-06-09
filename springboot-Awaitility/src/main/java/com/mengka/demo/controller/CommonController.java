package com.mengka.demo.controller;

import com.google.gson.Gson;
import com.mengka.demo.request.PetRequest;
import com.mengka.demo.response.GetPetByIdResponse;
import com.mengka.demo.retry.GenericRetryClient;
import com.mengka.demo.service.MengkaService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

/**
 * @author mengka
 * @Date 2024-06-01 19:23
 */
@Slf4j
@RestController
public class CommonController {

    @Autowired
    private MengkaService mengkaService;

    /**
     *  http://127.0.0.1:8072/t1?id=1
     *  http://127.0.0.1:8072/t1?id=8
     * @return
     */
    @GetMapping("/t1")
    @SneakyThrows
    public ResponseEntity<String> t1(@RequestParam String id) {
        log.info("common controller t1..");
        List<String> list = Arrays.asList("1","2","3","4","5","7");

        GetPetByIdResponse getPetByIdResponse = new GenericRetryClient<GetPetByIdResponse>()
                .runCallable(() -> mengkaService.getPetOrderData2(1));

        return ResponseEntity.ok("Hello World"+ new Gson().toJson(getPetByIdResponse));
    }
}
