package com.mengka.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * Druid开源数据库连接池
 * 1）提供连接池功能；
 * 2）提供非常优秀的监控功能；
 * <br>
 * Druid组件：
 * 1）DruidDriver代理Driver，能够提供基于Filter－Chain模式的插件体系；
 * 2）DruidDataSource高效可管理的数据库连接池；
 * 3）SQLParser；
 * <p>
 * 分支：
 * https://github.com/sanqianyuejiamk/druid
 * <p>
 * master:
 * https://github.com/alibaba/druid
 * <p>
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
        String userHome = System.getProperty("user.home");
        System.out.println("-------, user.home: " + userHome);

        String logPath = System.getProperty("logging.path");
        System.out.println("-------, logging.path: " + logPath);

        SpringApplication.run(SpringBootMonitorStart.class, args);
    }
}
