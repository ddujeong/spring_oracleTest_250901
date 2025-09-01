package com.ddu.oracle.dto;

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
	

}
