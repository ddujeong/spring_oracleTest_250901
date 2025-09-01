package com.ddu.oracle.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	private int membernum;
	private String memberid;
	private String memberpw;
	private String membername;
	private String memberdate;
	
	private List<BoardDto> boardDtos; // MemberDto : BoardDto -> 1 : N 관계
}
