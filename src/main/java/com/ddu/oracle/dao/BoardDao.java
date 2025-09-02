package com.ddu.oracle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ddu.oracle.dto.BoardDto;

public interface BoardDao {
	
	public void boardWriteDao(String btitle, String bcontent, String bwriter);
	
	public List<BoardDto> boardListDao();
	
	public int boardDeleteDao(int bnum);
	
	public int allBoardCountDao();
	
	public BoardDto contentViewDao(int bnum);
	
	public void upBhitDao(@Param("bnum") int bnum);
	
	public int boardModifyDao(String btitle, String bcontent, int bnum);
}
