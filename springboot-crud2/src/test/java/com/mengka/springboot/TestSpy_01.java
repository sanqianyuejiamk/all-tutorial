package com.mengka.springboot;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author mengka
 * @Date 2022-01-28 16:09
 */
public class TestSpy_01 extends BaseTest {

    @Spy
    private List<String> spiedList = new ArrayList<>();

    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
        spiedList.add("one");
        spiedList.add("two");

        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");

        assertEquals(2, spiedList.size());
        assertEquals(spiedList.get(0), "one");

        Mockito.doReturn(100).when(spiedList).size();
        assertEquals(100, spiedList.size());
    }
}
