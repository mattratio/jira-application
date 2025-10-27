package jira.jira.services.interfaces;

import jira.jira.dtos.TaskDto;
import jira.jira.entities.Task;
import jira.jira.enums.PriorityEnum;
import jira.jira.enums.StatusEnum;

import java.util.List;
import java.util.Map;

public interface TaskService {

	Task createTask(TaskDto taskDto);

	List<TaskDto> getAllTasks();

	TaskDto updateTaskStatus(TaskDto taskDto);

	List<TaskDto> getTasksByStatus(StatusEnum status);

	List<TaskDto> getTasksByPriority(PriorityEnum priority);

	List<TaskDto> getTasksByUserId(Long userId);

	void deleteTaskByID(TaskDto taskDto);

	Map<StatusEnum, Long> getTasksStats();
}
