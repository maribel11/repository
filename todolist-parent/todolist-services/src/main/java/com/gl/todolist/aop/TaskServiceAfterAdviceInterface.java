package com.gl.todolist.aop;

import java.util.List;

import com.gl.todolist.domain.Task;

public interface TaskServiceAfterAdviceInterface {

	public abstract void countTasks(List<Task> tasks);

}