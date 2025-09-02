package com.ddu.oracle.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ddu.oracle.dao.BoardDao;
import com.ddu.oracle.dto.BoardDto;

@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/bWrite")
	public String bWrite(HttpSession session, Model model) {
		String sid = (String) session.getAttribute("sessionId");
		
		if (sid == null) {
			model.addAttribute("msg" , "로그인 한 회원만 글쓰기가 가능합니다");
			model.addAttribute("url", "login");
			
			return "alert/alert";
		}
		return"writeForm";
	}
	@RequestMapping(value = "/bWriteOk")
	public String bWriteOk(HttpServletRequest request, Model model) {
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bwriter = request.getParameter("bwriter");
		
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		bDao.boardWriteDao(btitle, bcontent, bwriter);
		
		return"redirect:boardList";
	}
	@RequestMapping(value = "/boardList")
	public String bList(HttpServletRequest request, Model model) {
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		List<BoardDto> bDtos = bDao.boardListDao();
		
		model.addAttribute("bDtos",bDtos);
		model.addAttribute("count",bDao.allBoardCountDao());
		return"boardList";
	}
	@RequestMapping(value = "/boardDelete")
	public String bDelete(HttpServletRequest request, Model model) {
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		int deleteResult = bDao.boardDeleteDao(Integer.parseInt(request.getParameter("bnum")));
		
		if(deleteResult == 1) {
			model.addAttribute("msg" , "글 삭제 완료 되었습니다");
			model.addAttribute("url", "boardList");
		} else {
			model.addAttribute("msg" , "글 삭제가 실패 하였습니다. 다시 확인 해 주세요");
			model.addAttribute("url", "boardList");
		}
		return"alert/alert";
	}
	@RequestMapping(value = "/contentView")
	public String contentView(HttpServletRequest request, Model model) {
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		bDao.upBhitDao(Integer.parseInt(request.getParameter("bnum")));
		BoardDto bDto = bDao.contentViewDao(Integer.parseInt(request.getParameter("bnum")));
		
		model.addAttribute("bDto",bDto);
		return"contentView";
	}
	
}
