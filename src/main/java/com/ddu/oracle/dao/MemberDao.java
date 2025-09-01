package com.ddu.oracle.dao;

public interface MemberDao {
	
	public int memberJoinDao(String memberid, String memberpw, String membername);
	
	public int memberIdCheckDao(String memberid);
	
	public int loginOkDao(String memberid, String memberpw);
}
