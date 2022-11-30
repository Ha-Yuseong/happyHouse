package com.mycom.myapp.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.user.dto.UserDto;

@Mapper
public interface UserDao {
	int userRegister(UserDto userDto);
	int userUpdate(UserDto userDto);
	int userDelete(int userSeq);
	String getUserType(String code);
}
