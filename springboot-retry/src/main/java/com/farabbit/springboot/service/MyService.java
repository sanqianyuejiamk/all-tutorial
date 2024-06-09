package com.farabbit.springboot.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import java.sql.SQLException;

/**
 * @author mengka
 * @Date 2021-12-17 13:15
 */
public interface MyService {

    @Retryable(value = { SQLException.class,RuntimeException.class }, maxAttempts = 5, backoff = @Backoff(delay = 100))
    void retryServiceWithCustomization(String sql) throws SQLException,RuntimeException;

    @Retryable( value = SQLException.class, maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    void retryServiceWithExternalConfiguration(String sql) throws SQLException;

    @Recover
    void recover(SQLException e, String sql);

    @Recover
    void recoverRuntime(RuntimeException e, String sql);

    void templateRetryService();
}
