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
public class HouseinfoDto {
	private int no;
	private String AptName;
	private String si;
	private String gu;
	private String dong;
	private String jibun;
	private String buildYear;
	private String lat;
	private String lng;
	private String img;
}
