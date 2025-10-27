package jira.jira.services.implementations;

import jira.jira.data.UserData;
import jira.jira.dtos.UserDto;
import jira.jira.entities.User;
import jira.jira.mappers.UserMapper;
import jira.jira.repositories.UserRepository;
import jira.jira.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {

	private final UserData userData;
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public UserDto addUser(UserDto userDto) {
		User user = new User();
		User saved = userRepository.save(user);
		return userMapper.entityToDto(saved);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userData.findAllUsers().stream().map(userMapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public User updateUser(UserDto userDto) {
		User user = userRepository.findById(userDto.getId())
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userDto.getId()));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new RuntimeException("User not found with id: " + id);
		}
		userRepository.deleteById(id);
	}

}
