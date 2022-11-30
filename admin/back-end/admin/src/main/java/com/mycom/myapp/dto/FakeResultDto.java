package com.mycom.myapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FakeResultDto {
	private int result;
	private FakeDto dto;
	private List<FakeDto> list;
	private int count;

}
