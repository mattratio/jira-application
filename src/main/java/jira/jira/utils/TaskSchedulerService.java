package jira.jira.utils;

import jira.jira.entities.Task;
import jira.jira.enums.StatusEnum;
import jira.jira.repositories.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskSchedulerService {

	private final TaskRepository taskRepository;

	public TaskSchedulerService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Scheduled
	public void closeOverdueTasks() {
		LocalDateTime now = LocalDateTime.now();

		List<Task> overdueTasks = taskRepository.findAll().stream()
				.filter(task -> task.getDueDate() != null)
				.filter(task -> task.getDueDate().isBefore(now))
				.filter(task -> !task.getStatus().equals(StatusEnum.DONE))
				.toList();

		for (Task task : overdueTasks) {
			task.setStatus(StatusEnum.DONE);
		}

		if (!overdueTasks.isEmpty()) {
			taskRepository.saveAll(overdueTasks);
			System.out.println("Автоматически закрыто задач: " + overdueTasks.size());
		}
	}
}

