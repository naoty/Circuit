package com.github.naoty.circuit.sample;

import com.github.naoty.circuit.ParallelTasks;
import com.github.naoty.circuit.SerialTasks;
import com.github.naoty.circuit.Task;

import java.util.List;

public class Sample {
    public static void main(String args[]) {
        runSerialTasks();
        runParallelTasks();
        runCompoundTasks();
    }

    public static void runSerialTasks() {
        System.out.println("=== runSerialTasks");

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

    public static void runParallelTasks() {
        System.out.println("=== runParallelTasks");

        Task<Integer, String> task1 = new Task<Integer, String>() {
            @Override
            public String run(Integer obj) {
                StringBuffer buf = new StringBuffer();
                for (Integer i = 0; i < obj; i++) {
                    System.out.println("task1");
                    try {
                        Thread.sleep(3000);
                        buf.append("task1");
                    } catch(InterruptedException e) {
                        buf.append("interruped");
                    }
                }
                return buf.toString();
            }
        };

        Task<Integer, String> task2 = new Task<Integer, String>() {
            @Override
            public String run(Integer obj) {
                StringBuffer buf = new StringBuffer();
                for (Integer i = 0; i < obj * 2; i++) {
                    System.out.println("task2");
                    try {
                        Thread.sleep(1000);
                        buf.append("task1");
                    } catch(InterruptedException e) {
                        buf.append("interruped");
                    }
                }
                return buf.toString();
            }
        };

        Task<Integer, String> tasks = new ParallelTasks.Builder<Integer, String>()
                .add(task1)
                .add(task2)
                .build();
        String result = tasks.run(3);
        System.out.println(result);
    }

    public static void runCompoundTasks() {
        System.out.println("=== runCompoundTasks");

        Task<String, Integer> task1 = new Task<String, Integer>() {
            @Override
            public Integer run(String result) {
                System.out.println("task1");
                return result.length();
            }
        };

        Task<Integer, String> task2 = new Task<Integer, String>() {
            @Override
            public String run(Integer obj) {
                StringBuffer buf = new StringBuffer();
                for (Integer i = 0; i < obj; i++) {
                    System.out.println("task2");
                    try {
                        Thread.sleep(3000);
                        buf.append("task1");
                    } catch(InterruptedException e) {
                        buf.append("interruped");
                    }
                }
                return buf.toString();
            }
        };

        Task<Integer, String> task3 = new Task<Integer, String>() {
            @Override
            public String run(Integer obj) {
                StringBuffer buf = new StringBuffer();
                for (Integer i = 0; i < obj * 2; i++) {
                    System.out.println("task3");
                    try {
                        Thread.sleep(1000);
                        buf.append("task1");
                    } catch(InterruptedException e) {
                        buf.append("interruped");
                    }
                }
                return buf.toString();
            }
        };

        Task<String, Integer> task4 = new Task<String, Integer>() {
            @Override
            public Integer run(String obj) {
                System.out.println("task4");
                return obj.length();
            }
        };

        Task<Integer, String> subtasks = new ParallelTasks.Builder<Integer, String>()
                .add(task2)
                .add(task3)
                .build();
        Task<String, Integer> tasks = new SerialTasks.Builder<String, Integer, String>(task1, subtasks)
                .add(task4)
                .build();
        Integer result = tasks.run("Hi");
        System.out.println(result);
    }
}
