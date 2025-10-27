package jira.jira.controllers;

import jira.jira.dtos.UserDto;
import jira.jira.entities.User;
import jira.jira.mappers.UserMapper;
import jira.jira.repositories.UserRepository;
import jira.jira.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@PostMapping("/")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User save = userRepository.save(user);
		return ResponseEntity.ok(save);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));

		UserDto userDto = userMapper.entityToDto(user);
		return ResponseEntity.ok(userDto);
	}


	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
		userDto.setId(id);
		User user = userService.updateUser(userDto);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}


}
