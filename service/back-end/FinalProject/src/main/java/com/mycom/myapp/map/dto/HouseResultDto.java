package com.mycom.myapp.map.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseResultDto {
	private int result;
	private HouseTotalDto dto; // 게시글 하나
	private List<HouseTotalDto> list; // 게시글 목록
	private int count; // 게시글 총 건수
	
}
