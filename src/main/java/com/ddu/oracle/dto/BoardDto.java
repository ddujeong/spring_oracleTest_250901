package com.ddu.oracle.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	private int rnum;
	private int bnum;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private int bhit;
	private Date bdate;
	
	private MemberDto memberDto; // BoardDto : MemberDto => 1 : 1 관계
}
