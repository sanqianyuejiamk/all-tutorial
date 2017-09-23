package com.mengka.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author huangyy
 * @description
 * @data 2017/01/04.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
        configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
        configurer.defaultContentTypeStrategy(new PathExtensionContentNegotiationStrategy());
        configurer.ignoreAcceptHeader(true);
    }

    /**freemarker*/
    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freemarkerConfig = new FreeMarkerConfigurer();
        Properties settings = new Properties();
        settings.setProperty("tag_syntax", "auto_detect");
        freemarkerConfig.setFreemarkerSettings(settings);
        freemarkerConfig.setTemplateLoaderPath("templates");
        freemarkerConfig.setDefaultEncoding("UTF-8");

        Map<String, Object> freemarkerVariables = new HashMap<String, Object>();
//        freemarkerVariables.put("freemarkerDirectives", freemarkerDirectives());
        freemarkerConfig.setFreemarkerVariables(freemarkerVariables);
        return freemarkerConfig;
    }

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(false);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setRequestContextAttribute("rc");
        viewResolver.setContentType("text/html; charset=utf-8");
        return viewResolver;
    }
}
