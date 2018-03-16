package com.mengka.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:/properties/datasource.properties")
@ImportResource("classpath:/spring/applicationContext.xml")
public class SpringBootMonitorStart {

    public static void main(String[] args) {
//        String userHome = System.getProperty("user.home");
//        System.out.println("-------, user.home: " + userHome);
//
//        String logPath = System.getProperty("logging.path");
//        System.out.println("-------, logging.path: " + logPath);
//
//        SpringApplication.run(SpringBootMonitorStart.class, args);

        SpringApplication springApplication = new SpringApplication(SpringBootMonitorStart.class);
        springApplication.addListeners(new ApplicationPidFileWriter("app.pid"));
        springApplication.run(args);
    }
}
