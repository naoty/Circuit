package com.github.naoty.circuit;

import java.util.ArrayList;
import java.util.List;

public class ParallelTasks<T1, T2> implements Task<T1, T2> {
    private List<Task<T1, T2>> mTasks;

    private ParallelTasks(List<Task<T1, T2>> tasks) {
        mTasks = tasks;
    }

    @Override
    public T2 run(final T1 obj) {
        final List<T2> results = new ArrayList<T2>();
        List<Thread> threads = new ArrayList<Thread>();

        for (final Task<T1, T2> task: mTasks) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    T2 result = task.run(obj);
                    results.add(result);
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread: threads) {
            try {
                thread.join();
            } catch(InterruptedException e) {
                return null;
            }
        }

        return results.isEmpty() ? null : results.get(0);
    }

    public static class Builder<U1, U2> {
        private List<Task<U1, U2>> mTasks;

        public Builder() {
            mTasks = new ArrayList<Task<U1, U2>>();
        }

        public Builder<U1, U2> add(Task<U1, U2> task) {
            mTasks.add(task);
            return this;
        }

        public Task<U1, U2> build() {
            return new ParallelTasks<U1, U2>(mTasks);
        }
    }
}
