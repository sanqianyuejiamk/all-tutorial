//package com.mkyong.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
//public class JpaConfig extends HikariConfig {
//
//    @Bean
//    public DataSource dataSource() throws SQLException {
//        HikariConfig config = new HikariConfig("/hikari.properties");
//        HikariDataSource dataSource = new HikariDataSource(config);
//
//        return dataSource;
//    }
//
//}