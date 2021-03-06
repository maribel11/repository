package com.gl.todolist.web.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gl.todolist.domain.Task;
import com.gl.todolist.domain.User;
import com.gl.todolist.services.TaskServices;
import com.gl.todolist.services.exceptions.UserException;
import com.gl.todolist.web.dto.TaskDTO;

@Controller
@RequestMapping("/tasks")
public class TaskController extends BaseController {

	public TaskController() {
		super();
	}

	@Autowired
	TaskServices taskServices;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public Task saveTask(@RequestBody Task task, HttpSession session) throws UserException{
		/*User user = getLoggedUser();
		if(user!=null){
			task.setUser(user);	
		}*/
		
		User user = new User();
		user.setId(1l);
		task.setUser(user);
		return taskServices.saveUpdateTask(task);
	}

	private User getLoggedUser() {
		//User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
		User user = new User();
		user.setId(1l);
		user.setName("test@test.com");
		return user;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Task updateTask(@RequestBody TaskDTO taskDTO,HttpSession session) throws UserException{
		//el cliente js tiene mal la ruta del put http://localhost:8080/todolist-web/rest/tasks/1 <-- {1}
		Task task = taskServices.findTask(taskDTO.getId());
		if(getLoggedUser().getName().equalsIgnoreCase(task.getUser().getName())){			
			task.setTaskStatus(taskDTO.getTaskStatus());
			task.setTaskType(taskDTO.getTaskType());
			task.setTitle(taskDTO.getTitle());
			return taskServices.saveUpdateTask(task);
		}else{
			//TODO: throw security exception
		}
		return null;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void deleteTask(@PathVariable Long id) throws EntityNotFoundException{
		taskServices.deleteTask(id);
//		Task task = iTaskServices.findTask(id);
//		if(getLoggedUser().getName().equalsIgnoreCase(task.getUser().getName())){			
//			iTaskServices.deleteTask(id);
//		}else{
//		//TODO: throw security exception
//		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public List<Task> listTasks(HttpSession session){
		/*User user = getLoggedUser();
		if( user == null )
			throw new SecurityException("No user in session");*/
		return taskServices.listAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Task findTask(@PathVariable Long id) throws EntityNotFoundException{
		return taskServices.findTask(id);
	}		
}
