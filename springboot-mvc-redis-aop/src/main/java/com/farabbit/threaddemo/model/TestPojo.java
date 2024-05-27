package com.farabbit.threaddemo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class TestPojo {
    private static final long serialVersionUID = 6414799007524074403L;
    private int id;
    private String name;

    public TestPojo(TestPojo testPojo) {
        id = testPojo.id;
        name = testPojo.name;
    }
}