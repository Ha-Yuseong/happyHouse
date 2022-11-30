package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserDto;

public interface UserService {
	int userRegister(UserDto userDto);
	int userUpdate(UserDto userDto);
	int userDelete(int userSeq);
	String getUserType(String code);
}
