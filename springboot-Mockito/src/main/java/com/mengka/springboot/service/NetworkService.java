package com.mengka.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wujie
 * @Class NetworkService
 * @Description 网络测试Service
 * @Date 2021/3/1 9:35
 */
@Service
public class NetworkService {
    private final static Logger logger = LoggerFactory.getLogger(NetworkService.class);

    private static String getCause(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    public void testNetWork(HttpServletRequest request, HttpServletResponse response) {
        String body = null;
        int statusCode = 200;
        try {
            Map<String, String> headerMap = new HashMap<>();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                headerMap.put(key, request.getHeader(key));
            }
//            String url = headerMap.remove("test-network-url");
            String url = "http://www.baidu.com";
            if (url == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String str;
                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }
            } catch (Exception e) {
                logger.error("读取请求出错 [{}]", e);
            }
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(600000);
            factory.setReadTimeout(20000);
            RestTemplate restTemplate = new RestTemplate(factory);

            RequestEntity.BodyBuilder method = RequestEntity.method(HttpMethod.valueOf(request.getMethod()), URI.create(url));
            RequestEntity requestEntity;
            if (sb.length() > 0) {
                requestEntity = method.body(sb.toString());
            } else {
                requestEntity = method.build();
            }

            ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
            body = exchange.getBody();
            statusCode = exchange.getStatusCode().value();
            HttpHeaders headers = exchange.getHeaders();
            headers.forEach((k, v) -> {
                response.setHeader(k, v.get(0));
            });
        } catch (Exception e) {
            statusCode = 500;
            body = "[e:" + e.toString() + "]:\n" + getCause(e);

        }

        try {
            response.setStatus(statusCode);
            PrintWriter writer = response.getWriter();
            writer.write(body);
            writer.flush();
        } catch (IOException e) {
            logger.error("返回出错[{}]", e);
        }

    }


}