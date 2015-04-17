package com.github.naoty.circuit.sample;

import com.github.naoty.circuit.Task;

import java.lang.Override;

public class Sample {
    public static void main(String args[]) {
        Task<String, Integer> task = new Task<String, Integer>() {
            @Override
            public Integer run(String text) {
                return Integer.valueOf(text.length());
            }
        };
        Integer count = task.run("Hello, world!");
        System.out.println(count);
    }
}
