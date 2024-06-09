package com.farabbit.springboot;

import com.farabbit.springboot.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

/**
 * @author mengka
 * @Date 2021-12-17 13:13
 */
@Slf4j
public class TestRetry extends BaseTest {

    @Autowired
    private MyService myService;

    @Test
    public void test_retry() throws SQLException {
        myService.retryServiceWithExternalConfiguration(null);
    }
}
