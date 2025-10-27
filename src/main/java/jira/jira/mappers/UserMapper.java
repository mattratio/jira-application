package jira.jira.mappers;

import jira.jira.dtos.UserDto;
import jira.jira.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public User dtoToEntity(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		return user;
	}

	public UserDto entityToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		return userDto;
	}

	public User dtoToEntity(User user, UserDto userDto) {
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		return user;
	}

}
