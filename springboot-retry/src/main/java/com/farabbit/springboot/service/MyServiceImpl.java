package com.farabbit.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.sql.SQLException;

/**
 * @author mengka
 * @Date 2021-12-17 13:16
 */
@Slf4j
@Service
public class MyServiceImpl implements MyService {
    @Override
    public void retryServiceWithCustomization(String sql) throws SQLException,RuntimeException {
        log.info("throw SQLException in method retryServiceWithCustomization()");
        int aa = 2/0;
        if (StringUtils.isEmpty(sql)) {

            throw new SQLException();
        }
    }

    @Override
    public void retryServiceWithExternalConfiguration(String sql) throws SQLException {
        if (StringUtils.isEmpty(sql)) {
            log.info("throw SQLException in method retryServiceWithExternalConfiguration()");
            throw new SQLException();
        }
    }

    /**
     *  尝试3次失败后，调用方法 SQLException
     *
     * @param e
     * @param sql
     */
    @Override
    public void recover(SQLException e, String sql) {
        log.info("-------, In recover method");
    }

    /**
     *  尝试3次失败后，调用方法 RuntimeException
     *
     * @param e
     * @param sql
     */
    @Override
    public void recoverRuntime(RuntimeException e, String sql) {
        log.info("-------, In recover RuntimeException method");
    }

    @Override
    public void templateRetryService() {
        log.info("throw RuntimeException in method templateRetryService()");
        throw new RuntimeException();
    }
}
