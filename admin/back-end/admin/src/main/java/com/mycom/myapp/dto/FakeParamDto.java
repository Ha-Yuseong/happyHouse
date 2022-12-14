package com.mycom.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// for boardList, boardDetail
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FakeParamDto {
	private int limit;
	private int offset;
	
	private int boardId;
	private int userSeq;
}
