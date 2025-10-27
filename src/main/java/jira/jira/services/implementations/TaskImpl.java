package jira.jira.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jira.jira.dtos.TaskDto;
import jira.jira.entities.Task;
import jira.jira.entities.User;
import jira.jira.enums.PriorityEnum;
import jira.jira.enums.StatusEnum;
import jira.jira.mappers.TaskMapper;
import jira.jira.repositories.TaskRepository;
import jira.jira.repositories.UserRepository;
import jira.jira.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;
	private final UserRepository userRepository;

	@Transactional
	@Override
	public Task createTask(TaskDto dto) {
		Task task = new Task();
		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		task.setPriority(dto.getPriority());
		task.setStatus(StatusEnum.NEW);
		task.setCreatedAt(LocalDateTime.now());

		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
		task.setUser(user);

		return taskRepository.save(task);
	}



	@Override
	public List<TaskDto> getAllTasks() {
		return taskRepository.findAll()
				.stream()
				.map(taskMapper::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public TaskDto updateTaskStatus(TaskDto taskDto) {
		Task task = taskRepository.findById(taskDto.getId())
				.orElseThrow(() -> new RuntimeException("Task not found with id: " + taskDto.getId()));

		task.setStatus(taskDto.getStatus());
		Task saved = taskRepository.save(task);

		return taskMapper.entityToDto(saved);
	}

	@Override
	public List<TaskDto> getTasksByStatus(StatusEnum status) {
		return taskRepository.findByStatus(status)
				.stream()
				.map(taskMapper::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<TaskDto> getTasksByPriority(PriorityEnum priority) {
		return taskRepository.findByPriority(priority)
				.stream()
				.map(taskMapper::entityToDto)
				.collect(Collectors.toList());
	}


	@Override
	public List<TaskDto> getTasksByUserId(Long userId) {
		return taskRepository.findById(userId)
				.stream()
				.map(taskMapper::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteTaskByID(TaskDto taskDto) {
		Task task = taskRepository.findById(taskDto.getId())
				.orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskDto.getId()));
		taskRepository.delete(task);
	}


	@Override
	public Map<StatusEnum, Long> getTasksStats() {
		List<Task> allTasks = taskRepository.findAll();
		return allTasks.stream()
				.collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
	}

}
