package jira.jira.mappers;

import jira.jira.dtos.TaskDto;
import jira.jira.entities.Task;
import jira.jira.entities.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

	public Task dtoToEntity(TaskDto taskDto) {
		Task task = new Task();
		task.setId(taskDto.getId());
		task.setDescription(taskDto.getDescription());
		task.setTitle(taskDto.getTitle());
		task.setDueDate(taskDto.getDueDate());
		task.setStatus(taskDto.getStatus());
		task.setCreatedAt(taskDto.getCreatedAt());
		task.setUpdatedAt(taskDto.getUpdatedAt());
		task.setPriority(taskDto.getPriority());

		User user = new User();
		user.setId(taskDto.getUserId());
		task.setUser(user);

		return task;
	}

	public TaskDto entityToDto(Task task) {
		TaskDto taskDto = new TaskDto();
		taskDto.setId(task.getId());
		taskDto.setDescription(task.getDescription());
		taskDto.setTitle(task.getTitle());
		taskDto.setDueDate(task.getDueDate());
		taskDto.setStatus(task.getStatus());
		taskDto.setCreatedAt(task.getCreatedAt());
		taskDto.setUpdatedAt(task.getUpdatedAt());
		taskDto.setPriority(task.getPriority());
		taskDto.setUserId(task.getUser().getId()); // без if

		return taskDto;
	}


	public Task dtoToEntity(Task task, TaskDto taskDto) {
		task.setId(task.getId());
		task.setDescription(task.getDescription());
		task.setTitle(task.getTitle());
		task.setDueDate(task.getDueDate());
		task.setUser(task.getUser());
		task.setStatus(task.getStatus());
		task.setCreatedAt(task.getCreatedAt());
		task.setUpdatedAt(task.getUpdatedAt());
		task.setPriority(task.getPriority());
		return task;
	}
}
