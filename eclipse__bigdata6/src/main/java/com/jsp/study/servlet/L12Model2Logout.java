package com.jsp.study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout.do")
public class L12Model2Logout extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 로그아웃하는 2가지 방법
		// 1. session을 만료시켜서 새로운 세션이 생성되도록 한다.
		// 2. session에 있는 loginEmp만 삭제
//		session.invalidate(); 	// 1.
		session.removeAttribute("loginEmp");
		resp.sendRedirect(req.getContextPath() + "/");
	}
}
