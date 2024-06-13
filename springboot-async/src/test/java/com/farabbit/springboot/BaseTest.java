package com.farabbit.springboot;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author mengka
 * @Date 2021-12-16 13:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Demo2Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {
}
