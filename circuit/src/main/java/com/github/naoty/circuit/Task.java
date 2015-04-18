package com.github.naoty.circuit;

public interface Task<T, U> {
    U run(T obj);
}
