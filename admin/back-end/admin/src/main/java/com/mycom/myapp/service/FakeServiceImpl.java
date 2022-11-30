package com.mycom.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.myapp.dao.FakeDao;
import com.mycom.myapp.dto.FakeDto;
import com.mycom.myapp.dto.FakeParamDto;
import com.mycom.myapp.dto.FakeResultDto;
import com.mycom.myapp.dto.NoticeDto;
import com.mycom.myapp.dto.NoticeResultDto;


@Service
public class FakeServiceImpl implements FakeService{
	@Autowired
	FakeDao dao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public FakeResultDto fakeList(FakeParamDto fakeParamDto) {	
		FakeResultDto fakeResultDto = new FakeResultDto();
		try {
			List<FakeDto> list = dao.fakeList(fakeParamDto);	
			fakeResultDto.setList(list);
			fakeResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			fakeResultDto.setResult(FAIL);
		}
		return fakeResultDto;
	}
	

	@Override
	public FakeResultDto fakeUpdate(int fakeId) {
		FakeResultDto fakeResultDto = new FakeResultDto();
		try {
			dao.fakeUpdate(fakeId);
			fakeResultDto.setResult(SUCCESS);	
			System.out.println("dd");
			
		}catch(Exception e) {
			e.printStackTrace();
			fakeResultDto.setResult(FAIL);
		}
		
		return fakeResultDto;
	}
	
}
