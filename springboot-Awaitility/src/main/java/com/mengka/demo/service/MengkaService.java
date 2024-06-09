package com.mengka.demo.service;

import com.alibaba.fastjson.JSON;
import com.mengka.demo.response.GetPetByIdResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mengka
 * @Date 2024-06-09 19:45
 */
@Slf4j
@Service
public class MengkaService {

    @SneakyThrows
    public GetPetByIdResponse getPetOrderData2(Integer id){
        HttpGet request = new HttpGet("https://petstore.swagger.io/v2/store/order/"+id);
        request.addHeader(org.apache.http.HttpHeaders.CONTENT_TYPE, "application/json");

        GetPetByIdResponse petByIdResponse = new GetPetByIdResponse();

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
             CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getProtocolVersion());              // HTTP/1.1
            System.out.println(response.getStatusLine().getStatusCode());   // 301
            System.out.println(response.getStatusLine().getReasonPhrase()); // Moved Permanently
            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 301 Moved Permanently

            org.apache.http.HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                petByIdResponse = JSON.parseObject(result,GetPetByIdResponse.class);
                System.out.println(result);
            }
            petByIdResponse.setStatusCode(response.getStatusLine().getStatusCode());

        }
        return petByIdResponse;
    }

    /**
     *  https://stackoverflow.com/questions/8297215/spring-resttemplate-get-with-parameters
     *  https://howtodoinjava.com/spring-boot2/resttemplate/resttemplate-get-example/
     *
     * @param id
     * @return
     */
    public GetPetByIdResponse getPetOrderData(Integer id){
        String url = "https://petstore.swagger.io/v2/store/order/"+id;// /Users/hyy044101331/work_sanqianyuejia/all-tutorial/springboot-hsqldb

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);// set `content-type` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));// set `accept` header

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);

        // send POST request
        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);

        // check response
        if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) {
            log.info("Request Successful. response body = "+response.getBody());
        } else {
            log.info("Request Failed. StatusCode = "+response.getStatusCode());
        }
        GetPetByIdResponse petByIdResponse = JSON.parseObject(response.getBody(),GetPetByIdResponse.class);
        petByIdResponse.setStatusCode(response.getStatusCode().value());
        return petByIdResponse;
    }
}
