package com.github.naoty.circuit;

public class SerialTasks<T1, T2, T3> implements Task<T1, T3> {
    private Task<T1, T2> mTask1;
    private Task<T2, T3> mTask2;

    private SerialTasks(Task<T1, T2> task1, Task<T2, T3> task2) {
        mTask1 = task1;
        mTask2 = task2;
    }

    @Override
    public T3 run(T1 obj) {
        return mTask2.run(mTask1.run(obj));
    }

    public static class Builder<U1, U2, U3> {
        private Task<U1, U3> mTask;

        public Builder(Task<U1, U2> task1, Task<U2, U3> task2) {
            mTask = new SerialTasks<U1, U2, U3>(task1, task2);
        }

        public <U4> Builder<U1, U3, U4> add(Task<U3, U4> task) {
            return new Builder<U1, U3, U4>(mTask, task);
        }

        public Task<U1, U3> build() {
            return mTask;
        }
    }
}
