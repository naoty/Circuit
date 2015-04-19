package com.github.naoty.circuit;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class SerialTasksTest {
    @Test
    public void runTest() {
        Task<String, Integer> task1 = new Task<String, Integer>() {
            @Override
            public Integer run(String obj) {
                return obj.length();
            }
        };

        Task<Integer, String> task2 = new Task<Integer, String>() {
            @Override
            public String run(Integer obj) {
                StringBuffer buf = new StringBuffer();
                for (int i = 0; i < obj; i++) {
                    buf.append("sample");
                }
                return buf.toString();
            }
        };

        Task<String, String> tasks = new SerialTasks.Builder<String, Integer>(task1)
                .add(task2)
                .build();
        String actual = tasks.run("hi");
        assertThat(actual, is("samplesample"));
    }
}
