package com.mycom.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.dto.FakeDto;
import com.mycom.myapp.dto.FakeParamDto;

@Mapper
public interface FakeDao {
	 int fakeUpdate(int fakeId);
	 List<FakeDto> fakeList(FakeParamDto fakeParamDto);
}
