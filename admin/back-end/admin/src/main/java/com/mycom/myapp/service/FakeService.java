package com.mycom.myapp.service;

import com.mycom.myapp.dto.FakeDto;
import com.mycom.myapp.dto.FakeParamDto;
import com.mycom.myapp.dto.FakeResultDto;

public interface FakeService {

	FakeResultDto fakeList(FakeParamDto fakeParamDto);
	FakeResultDto fakeUpdate(int fakeId);

}
