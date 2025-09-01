package com.ddu.oracle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ddu.oracle.dao.MemberDao;

@Controller
public class MemberController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/memberJoin")
	public String memberJoin(HttpServletRequest request, Model model) {
		
		String error = request.getParameter("error");
		
		if (error != null) {
			model.addAttribute("error", error);
		}
		return "memberJoin";
	}
	@RequestMapping(value = "/memberJoinOk")
	public String memberJoinOk(HttpServletRequest request, Model model) {
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		int idResult = mDao.memberIdCheckDao(request.getParameter("memberid"));
		
			if(idResult == 1) { // 아이디가 이미 존재 할때
				model.addAttribute("msg", "이미 존재하는 아이디입니다. 다시 가입해 주세요.");
				model.addAttribute("url", "memberJoin");
				
				return "alert/alert";
			} else {
				int joinResult = mDao.memberJoinDao(request.getParameter("memberid"), request.getParameter("memberpw"), request.getParameter("membername"));
				model.addAttribute("mid", request.getParameter("memberid"));
				return "memberJoinOk";
			}
	}
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {
		
		String error = request.getParameter("error");
		
			if (error != null) {
				model.addAttribute("error", error);
			}
		
		return "login";
	}
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request, Model model, HttpSession session) {
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		int loginResult = mDao.loginOkDao(request.getParameter("memberid"), request.getParameter("memberpw"));
				
		if (loginResult != 1) {
			model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			model.addAttribute("url", "login");
			
			return"alert/alert";
		} else {
			session.setAttribute("sessionId",request.getParameter("memberid") );
			model.addAttribute("msg", "로그인 성공. 회원님 반갑습니다");
			model.addAttribute("url", "loginSuccess");
			
			return "alert/alert";
		}
	}
	@RequestMapping(value = "/loginSuccess")
	public String loginSuccess(HttpServletRequest request, Model model) {
		
		
		return "loginSuccess";
	}
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, Model model, HttpSession session) {
		session.invalidate();
		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "login");
		return "alert/alert";
	}
}
