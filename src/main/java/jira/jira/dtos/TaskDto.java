package jira.jira.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jira.jira.enums.PriorityEnum;
import jira.jira.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto {

	private Long id;

	private String title;

	private String description;

	private PriorityEnum priority;

	private StatusEnum status;

	private LocalDateTime dueDate;

	private Long userId;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}
