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
		int pageSize = 10; // 게시판 목록에 한 페이지당 출력될 글 갯수
		int pageNum = 1; // 유저가 클릭한 페이지의 번호-> 현재 보이는 페이지 번호 (단 처음 게시판에 들어왔을때 1페이지를 보여야 하기때문)
		int blockSize = 5; // 페이지 블럭에 표시될 페이지의 수 (<< < 1 , 2 , 3 , 4 , 5 > >>)
		
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt( request.getParameter("pageNum"));
		}
		
		int startRow = (pageNum * pageSize) - 9; // 페이징 되었을 때 시작할 행의 번호 (1->1 , 2-> 11, 3-> 21)
		// == (pageNum - 1) * pageSize
		int endRow = pageNum * pageSize; // 페이징 되었을 때 행의 끝 번호 (1->10 , 2-> 20, 3-> 30)
		
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		List<BoardDto> bDtos = bDao.pageBoardListDao(startRow, endRow);
		
		int totalPage = (int)Math.ceil((double)bDao.allBoardCountDao() /pageSize);
		int startPage = (((pageNum-1) / blockSize) *blockSize) + 1; 
		int endPage = Math.min(startPage + (blockSize-1),totalPage );
		// int endPage = startPage + (blockSize-1);
		
		model.addAttribute("bDtos",bDtos);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("pageNum",pageNum);
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
	@RequestMapping(value = "/boardModify")
	public String boardModify(HttpServletRequest request, Model model) {
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		
		int modifyResult= bDao.boardModifyDao(request.getParameter("btitle") ,request.getParameter("bcontent") ,Integer.parseInt(request.getParameter("bnum"))); 
		
		if(modifyResult == 1) {
			model.addAttribute("msg" , "글 수정 완료 되었습니다");
			model.addAttribute("url", "contentView");
		} else {
			model.addAttribute("msg" , "글 수정이 실패 하였습니다. 다시 확인 해 주세요");
			model.addAttribute("url", "contentView");
		}
		
		return"alert/alert";
	}
	@RequestMapping(value = "/pageList")
	public String pageList(HttpServletRequest request, Model model) {
		
		int pageSize = 10; // 게시판 목록에 한 페이지당 출력될 글 갯수
		int pageNum = 1; // 유저가 클릭한 페이지의 번호-> 현재 보이는 페이지 번호 (단 처음 게시판에 들어왔을때 1페이지를 보여야 하기때문)
		int blockSize = 5; // 페이지 블럭에 표시될 페이지의 수 (<< < 1 , 2 , 3 , 4 , 5 > >>)
		
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt( request.getParameter("pageNum"));
		}
		
		int startRow = (pageNum * pageSize) - 9; // 페이징 되었을 때 시작할 행의 번호 (1->1 , 2-> 11, 3-> 21)
		// == (pageNum - 1) * pageSize
		int endRow = pageNum * pageSize; // 페이징 되었을 때 행의 끝 번호 (1->10 , 2-> 20, 3-> 30)
		
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		List<BoardDto> bDtos = bDao.pageBoardListDao(startRow, endRow);
		
		int totalPage = (int)Math.ceil((double)bDao.allBoardCountDao() /pageSize);
		int startPage = (((pageNum-1) / blockSize) *blockSize) + 1; 
		int endPage = Math.min(startPage + (blockSize-1),totalPage );
		// int endPage = startPage + (blockSize-1);
		
		model.addAttribute("bDtos",bDtos);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("count",bDao.allBoardCountDao());
		return"pageList";
	}
	@RequestMapping(value = "/test")
	public String test(HttpServletRequest request, Model model) {
		
		return"test";
	}
	@RequestMapping(value = "/mapTest")
	public String mapTest(HttpServletRequest request, Model model) {
		
		return"mapTest";
	}
	@RequestMapping(value = "/kakaoMap")
	public String kakaoMap(HttpServletRequest request, Model model) {
		
		return"kakaoMap";
	}
	
}
