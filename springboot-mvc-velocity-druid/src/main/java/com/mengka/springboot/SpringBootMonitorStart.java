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
 *
 * 【作用】
 * 》》可以监控数据库访问性能，Druid内置提供了一个功能强大的StatFilter插件，能够详细统计SQL的执行性能，这对于线上分析数据库访问性能有帮助；
 *
 * 》》替换DBCP和C3P0。Druid提供了一个高效、功能强大、可扩展性好的数据库连接池。
 *
 * 》》SQL执行日志，Druid提供了不同的LogFilter，能够支持Common-Logging、Log4j和JdkLog，你可以按需要选择相应的LogFilter，监控你应用的数据库访问情况。
 *
 * 》》数据库密码加密。直接把数据库密码写在配置文件中，这是不好的行为，容易导致安全问题。DruidDruiver和DruidDataSource都支持PasswordCallback。
 *
 * 》》扩展JDBC，如果你要对JDBC层有编程的需求，可以通过Druid提供的Filter-Chain机制，很方便编写JDBC层的扩展插件。
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
        String userHome = System.getProperty("user.home");
        System.out.println("-------, user.home: " + userHome);

        String logPath = System.getProperty("logging.path");
        System.out.println("-------, logging.path: " + logPath);

        SpringApplication.run(SpringBootMonitorStart.class, args);
    }
}
