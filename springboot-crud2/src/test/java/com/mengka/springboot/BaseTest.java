package com.mengka.springboot;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huangyy
 * @date 2017/11/03.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Demo2Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {
}
