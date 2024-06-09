//package com.mengka.springboot.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
///**
// * @author huangyy
// * @version cabbage-forward2.0,2018-03-18
// * @since cabbage-forward2.0
// */
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public RestTemplate customRestTemplate(){
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpRequestFactory.setConnectionRequestTimeout(-1);
//        httpRequestFactory.setConnectTimeout(300000);
//        httpRequestFactory.setReadTimeout(300000);
//        return new RestTemplate(httpRequestFactory);
//    }
//}
