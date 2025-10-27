package jira.jira.data;


import jira.jira.entities.Task;
import jira.jira.entities.User;
import jira.jira.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserData {
	private final UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User findUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public void updateUser(User user) {
		userRepository.save(user);
	}

	public void deleteUserById(Long id) {
		userRepository.deleteUserById(id);
	}
}
