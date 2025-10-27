package jira.jira.controllers;

import jira.jira.dtos.TaskDto;
import jira.jira.entities.Task;
import jira.jira.enums.PriorityEnum;
import jira.jira.enums.StatusEnum;
import jira.jira.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;


	@PostMapping("/")
	public ResponseEntity<Task> create(@RequestBody TaskDto dto) {
		Task created = taskService.createTask(dto);
		return ResponseEntity.ok(created);
	}


	@GetMapping("/")
	public ResponseEntity<List<TaskDto>> getAllTasks() {
		return ResponseEntity.ok(taskService.getAllTasks());
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<TaskDto> updateTaskStatus(
			@PathVariable Long id,
			@RequestBody Map<String, String> body) {

		String statusValue = body.get("status");
		StatusEnum newStatus = StatusEnum.valueOf(statusValue.toUpperCase());

		TaskDto dto = new TaskDto();
		dto.setId(id);
		dto.setStatus(newStatus);

		TaskDto updated = taskService.updateTaskStatus(dto);
		return ok(updated);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<TaskDto>> getTasksByStatus(@PathVariable StatusEnum status) {
		return ResponseEntity.ok(taskService.getTasksByStatus(status));
	}


	@GetMapping("/priority/{priority}")
	public ResponseEntity<List<TaskDto>> getTasksByPriority(@PathVariable PriorityEnum priority) {
		return ResponseEntity.ok(taskService.getTasksByPriority(priority));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<TaskDto>> getTasksByUser(@PathVariable Long userId) {
		return ResponseEntity.ok(taskService.getTasksByUserId(userId));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable Long id) {
		TaskDto dto = new TaskDto();
		dto.setId(id);
		taskService.deleteTaskByID(dto);
		return ResponseEntity.ok("Task deleted successfully!");
	}

	@GetMapping("/stats")
	public ResponseEntity<Map<StatusEnum, Long>> getTasksStats() {
		Map<StatusEnum, Long> stats = taskService.getTasksStats();
		return ok(stats);
	}
}
