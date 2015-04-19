package com.github.naoty.circuit;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class TaskTest {
    @Test
    public void testRun() {
        Task<String, String> task = new Task<String, String>() {
            @Override
            public String run(String obj) {
                return obj.toUpperCase();
            }
        };
        String actual = task.run("sample");
        assertThat(actual, is("SAMPLE"));
    }
}
