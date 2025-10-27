package jira.jira.repositories;

import jira.jira.entities.Task;
import jira.jira.enums.PriorityEnum;
import jira.jira.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	Optional<Task> findById(Long id);

	void deleteTaskById(Long id);

	List<Task> findByStatus(StatusEnum status);

	List<Task> findByPriority(PriorityEnum priority);

}
