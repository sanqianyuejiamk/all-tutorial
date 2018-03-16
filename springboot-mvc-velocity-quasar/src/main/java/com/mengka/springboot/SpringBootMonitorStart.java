package com.mengka.springboot;

import co.paralleluniverse.springframework.boot.autoconfigure.web.FiberSpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 *  Statsd数据采集工具
 *  measuring everything and anaything.
 *
 *  mvn clean install
 *  java -javaagent:../quasar-core-0.7.6.jar -jar spring-boot-1.0-SNAPSHOT-cap.jar
 *
 * Created with IntelliJ IDEA
 * User: huangyy
 * Date: 2016/11/26
 * Time: 13:36
 */
@FiberSpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:/properties/datasource.properties")
@ImportResource("classpath:/spring/applicationContext.xml")
public class SpringBootMonitorStart {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMonitorStart.class, args);

    }
}
