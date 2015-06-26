package com.gl.todolist.aop;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;

import com.gl.todolist.domain.Task;

@Aspect
public class TaskServiceAfterAdvice implements TaskServiceAfterAdviceInterface {
	/* (non-Javadoc)
	 * @see com.gl.todolist.aop.TaskServiceAfterAdviceInterface#countTasks(java.util.List)
	 */
	@AfterReturning(
			   pointcut="execution(* listAll())",
			   returning="tasks")
	  public void countTasks(List<Task> tasks) {
		System.out.println("Count = " + tasks.size());
	  }
	

}
