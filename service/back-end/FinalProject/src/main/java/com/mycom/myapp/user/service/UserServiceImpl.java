package com.mycom.myapp.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.user.dao.UserDao;
import com.mycom.myapp.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;
	
	@Override
	public int userRegister(UserDto userDto) {
		// TODO Auto-generated method stub
		return dao.userRegister(userDto);
	}

	@Override
	public int userUpdate(UserDto userDto) {
		// TODO Auto-generated method stub
		return dao.userUpdate(userDto);
	}

	@Override
	public int userDelete(int userSeq) {
		// TODO Auto-generated method stub
		return dao.userDelete(userSeq);
	}

	@Override
	public String getUserType(String code) {
		// TODO Auto-generated method stub
		return dao.getUserType(code);
	}

}
