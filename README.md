# Circuit

A runner for serial or parallel tasks.

## Todo
- [x] Add Task interface
- [x] Add SerialTasks
- [ ] Add ParalellTasks
- [ ] Enable Compound tasks
- [ ] Publish

## Usage

### Single task

```java
Task<String, Integer> task = new Task<String, Integer>() {
    @Override
    public Integer run(String text) {
      return Integer.valueOf(text.length());
    }
};

task.run("Hello, world!"); //=> 13
```

### Serial tasks

```java
// task1<String, Integer> -> task2<Integer, String> -> task3<String, Integer>
Task<String, Integer> tasks = new SerialTasks.Builder<String, Integer, String>(task1, task2)
  .add(task3)
  .build();
tasks.run("Hello, world!");
```

### Paralell tasks

```java
// [task1, task2]
ParallelTasks tasks = ParalellTasks.Builder()
    .add(task1)
    .add(task2)
    .build();
tasks.run();
```

### Compound tasks

```java
// task1 -> [task2, task3] -> task4
ParallelTasks subtasks = ParallelTasks.Builder()
    .add(task2)
    .add(task3)
    .build()
SerialTasks tasks = SerialTasks.Builder()
    .add(task1)
    .add(subtasks)
    .add(task4)
    .build();
tasks.run();
```

## Installation

```groovy
dependencies {
  compile "com.github.naoty:circuit:+"
}
```

## Author

[naoty](https://github.com/naoty)
