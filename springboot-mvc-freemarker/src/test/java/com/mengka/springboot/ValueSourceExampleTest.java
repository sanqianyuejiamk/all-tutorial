package com.mengka.springboot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * 》》JUnit 5使用指南：
 *  http://junit.org/junit5/docs/current/user-guide/#overview
 *
 *  https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-parameterized-tests/
 */
@DisplayName("Pass the method parameters provided by the @ValueSource annotation")
class ValueSourceExampleTest {

    @DisplayName("Should pass a non-null message to our test method")
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    void shouldPassNonNullMessageAsMethodParameter(String message) {
        assertNotNull(message);
    }
}