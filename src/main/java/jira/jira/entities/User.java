package jira.jira.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jira_user")
public class User {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

}
