package jira.jira.data;

import jira.jira.entities.Task;
import jira.jira.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskData {
	private final TaskRepository taskRepository;

	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> findAllTasks() {
		return taskRepository.findAll();
	}

	public Task findTaskById(Long id) {
		return taskRepository.findById(id).orElse(null);
	}

	public void updateTask(Task task) {
		taskRepository.save(task);
	}

	public void deleteTaskById(Long id) {
		taskRepository.deleteTaskById(id);
	}
}
