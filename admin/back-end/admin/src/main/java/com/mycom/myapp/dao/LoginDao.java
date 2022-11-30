package com.mycom.myapp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.dto.UserDto;

@Mapper
public interface LoginDao {
	UserDto login(String userEmail);
}
