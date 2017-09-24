package com.mengka.interceptor;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 *   拦截器实现①
 *
 * User: mengka
 * Date: 15-3-22-下午5:46
 */
public class AllInterceptor implements WebRequestInterceptor {

    static final Logger logger = LogManager.getLogger(AllInterceptor.class);

    public void preHandle(WebRequest request) throws Exception {
        String url = request.getContextPath();
        logger.info("---------------, AllInterceptor preHandle path = "+url);

        request.setAttribute("request", "request", WebRequest.SCOPE_REQUEST);//这个是放到request范围内的，所以只能在当前请求中的request中获取到
        request.setAttribute("session", "session", WebRequest.SCOPE_SESSION);

    }

    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

    }
}
