package com.mycom.myapp.dto;

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
public class FakeDto {
	int fakeId;
	String userId;
	String sellerId;
	String title;
	String content;
	String regDt;
	String dealId;
	String isDone;
}
