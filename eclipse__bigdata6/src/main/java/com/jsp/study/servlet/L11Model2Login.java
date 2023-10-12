package com.jsp.study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.study.dao.EmpDao;
import com.jsp.study.dao.EmpDaoImp;
import com.jsp.study.dto.EmpDto;

@WebServlet("/login.do")
public class L11Model2Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/templates/login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empno_str = req.getParameter("empno");
		String pw = req.getParameter("pw");
		HttpSession session = req.getSession();	// 요청한 클라이언트의 id와 동일한 id를 갖는 세션객체를 전달
		System.out.println(session.getId());
		
		EmpDto loginEmp = null;
		EmpDao empDao = null;
		try {
			int empno=Integer.parseInt(empno_str);
			empDao = new EmpDaoImp();
			loginEmp = empDao.findByEmpnoAndPw(empno, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(loginEmp);
		
		if (loginEmp==null) {
			resp.sendRedirect("login.do");
		} else {
			session.setAttribute("loginEmp", loginEmp);
			resp.sendRedirect(req.getContextPath()+"/");
		}
	}

}
