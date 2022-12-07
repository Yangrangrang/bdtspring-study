package com.jsp.study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.study.dao.EmpDaoImp;
import com.jsp.study.dto.EmpDto;

@WebServlet("/model2/emp/detail.do")
public class L07Model2EmpDetail extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("empno")==null) {
			resp.sendError(400);
			return;	// 함수 실행 종료
		} 
		EmpDto emp = null;
		try {
			int empno = Integer.parseInt(req.getParameter("empno"));
			EmpDaoImp empDao = new EmpDaoImp();
			emp = empDao.detail(empno);
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(emp!=null) {
			req.setAttribute("emp", emp);
			req.getRequestDispatcher("/WEB-INF/templates/emp/detail.jsp").forward(req, resp);
		} else {	// emp==null : 오류가 있거나 삭제된 레코드
			resp.sendRedirect("list.do");
		}
	}
}
