package com.example.cruddemo.config;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * HikariCP 连接池监控配置
 * 用于监控和记录连接池状态
 */
@Component
public class HikariConfigMonitor {

    private static final Logger logger = LoggerFactory.getLogger(HikariConfigMonitor.class);

    private final DataSource dataSource;

    @Autowired
    public HikariConfigMonitor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        logPoolStatus();
    }

    /**
     * 定时记录连接池状态（每5分钟）
     */
    @Scheduled(fixedRate = 300000) // 5分钟
    public void scheduledLogPoolStatus() {
        logPoolStatus();
    }

    /**
     * 记录连接池状态到日志
     */
    public void logPoolStatus() {
        if (dataSource instanceof HikariDataSource) {
            HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
            HikariPoolMXBean poolBean = hikariDataSource.getHikariPoolMXBean();

            logger.info("=== HikariCP Connection Pool Status ===");
            logger.info("Pool Name: {}", hikariDataSource.getPoolName());
            logger.info("Active Connections: {}", poolBean.getActiveConnections());
            logger.info("Idle Connections: {}", poolBean.getIdleConnections());
            logger.info("Total Connections: {}", poolBean.getTotalConnections());
            logger.info("Threads Awaiting Connection: {}", poolBean.getThreadsAwaitingConnection());
            logger.info("Maximum Pool Size: {}", hikariDataSource.getMaximumPoolSize());
            logger.info("Minimum Idle: {}", hikariDataSource.getMinimumIdle());
            logger.info("========================================");
        }
    }

    /**
     * 获取连接池状态信息（可用于API端点）
     */
    public String getPoolStatus() {
        if (dataSource instanceof HikariDataSource) {
            HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
            HikariPoolMXBean poolBean = hikariDataSource.getHikariPoolMXBean();

            return String.format(
                "Pool: %s | Active: %d | Idle: %d | Total: %d | Max: %d | Min: %d | Waiting: %d",
                hikariDataSource.getPoolName(),
                poolBean.getActiveConnections(),
                poolBean.getIdleConnections(),
                poolBean.getTotalConnections(),
                hikariDataSource.getMaximumPoolSize(),
                hikariDataSource.getMinimumIdle(),
                poolBean.getThreadsAwaitingConnection()
            );
        }
        return "HikariCP not available";
    }
}

