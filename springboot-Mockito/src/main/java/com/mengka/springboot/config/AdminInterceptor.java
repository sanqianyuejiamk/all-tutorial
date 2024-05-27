package com.mengka.springboot.config;

import com.mengka.springboot.utils.MD5Util;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;


public class AdminInterceptor implements HandlerInterceptor {
    /**
     * 不拦截的前缀
     */
    private final static Set<String> URL_SET = new HashSet<String>();

    static {

        URL_SET.add("sdk");
        URL_SET.add("tFile");
        URL_SET.add("network");
        URL_SET.add("api");
        URL_SET.add("wb");

    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String requestURI = request.getRequestURI();
        int index = requestURI.indexOf('/', 1);
        if (index == -1) {
            index = requestURI.length();
        }
        String substring = requestURI.substring(1, index);
        if (!URL_SET.contains(substring)) {
            String header = request.getHeader("Auth-Token");
            String instanceId = request.getHeader("Sdk-Instance-Id");
            if (!MD5Util.MD532Low(requestURI + "@" + instanceId).equals(header)) {
                return false;
            }
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        return true;
    }


}