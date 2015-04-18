# Circuit

A runner for serial or parallel tasks.

## TODO
- [x] Add Task interface
- [x] Add SerialTasks
- [x] Add ParallelTasks
- [x] Enable Compound tasks
- [ ] Brush up
- [ ] Unit tests
- [ ] Publish

## Usage

### Single task

```java
Task<String, Integer> task = new Task<String, Integer>() {
    @Override
    public Integer run(String text) {
        return text.length();
    }
};

task.run("Hello, world!"); //=> 13
```

### Serial tasks

```java
// task1 -> task2 -> task3
Task<String, Integer> tasks = new SerialTasks.Builder<String, Integer>(task1)
    .add(task2)
    .add(task3)
    .build();
tasks.run("Hello, world!");
```

### Parallel tasks

```java
// [task1, task2]
Task<Integer, String> tasks = new ParallelTasks.Builder<Integer, String>()
    .add(task1)
    .add(task2)
    .build();
tasks.run(3);
```

### Compound tasks

```java
// task1 -> [task2, task3] -> task4
Task<Integer, String> subtasks = ParallelTasks.Builder<Integer, String>()
    .add(task2)
    .add(task3)
    .build()
Task<String, String> tasks = SerialTasks.Builder<String, Integer>(task1)
    .add(subtasks)
    .add(task4)
    .build();
tasks.run("Hello, world!");
```

## Installation

```groovy
dependencies {
  compile "com.github.naoty:circuit:+"
}
```

## Author

[naoty](https://github.com/naoty)
