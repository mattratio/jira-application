package jira.jira.entities;

import jakarta.persistence.*;
import jira.jira.enums.PriorityEnum;
import jira.jira.enums.StatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jira_task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "priority")
	private PriorityEnum priority;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status = StatusEnum.NEW;

	@Column(name = "due_date")
	private LocalDateTime dueDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;


	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
