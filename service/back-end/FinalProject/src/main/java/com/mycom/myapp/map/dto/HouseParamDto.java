package com.mycom.myapp.map.dto;

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
public class HouseParamDto {
	private String searchWord;
	private int limit;
	private int offset;
	
	private String dong;
	private int code;
	
	private String floor;
	private String area;
	private String houseContent;
	private int dealAmount;
	
	private int houseId;
	private int userSeq;
}
