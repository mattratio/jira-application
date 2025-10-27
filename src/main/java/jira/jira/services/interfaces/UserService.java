package jira.jira.services.interfaces;

import jira.jira.dtos.UserDto;
import jira.jira.entities.User;

import java.util.List;

public interface UserService {

	UserDto addUser (UserDto userDto);

	List<UserDto> getAllUsers();

	User updateUser(UserDto userDto);

	void deleteUser(Long id);

}
