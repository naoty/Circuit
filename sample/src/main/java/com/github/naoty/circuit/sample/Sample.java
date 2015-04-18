package com.github.naoty.circuit.sample;

import com.github.naoty.circuit.SerialTasks;
import com.github.naoty.circuit.Task;

public class Sample {
    public static void main(String args[]) {
        Task<String, Integer> task1 = new Task<String, Integer>() {
            @Override
            public Integer run(String result) {
                System.out.println("task1");
                return result.length();
            }
        };

        Task<Integer, String> task2 = new Task<Integer, String>() {
            @Override
            public String run(Integer result) {
                StringBuffer buf = new StringBuffer();
                for (Integer i = 0; i < result; i++) {
                    buf.append("A");
                }
                System.out.println("task2");
                return buf.toString();
            }
        };

        Task<String, Integer> task3 = new Task<String, Integer>() {
            @Override
            public Integer run(String obj) {
                System.out.println("task3");
                return obj.length() * 2;
            }
        };

        Task<Integer, String> task4 = new Task<Integer, String>() {
            @Override
            public String run(Integer obj) {
                System.out.println("task4");
                return "foobar";
            }
        };

        Task<String, Integer> task5 = new Task<String, Integer>() {
            @Override
            public Integer run(String obj) {
                System.out.println("task5");
                return obj.length();
            }
        };

        Task<String, Integer> tasks = new SerialTasks.Builder<String, Integer, String>(task1, task2)
                .add(task3)
                .add(task4)
                .add(task5)
                .build();
        Integer result = tasks.run("Hello");
        System.out.println(result);
    }
}
