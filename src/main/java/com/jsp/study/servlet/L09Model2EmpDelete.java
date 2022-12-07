package com.jsp.study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.study.dao.EmpDaoImp;

@WebServlet("/model2/emp/delete.do")
public class L09Model2EmpDelete extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 삭제하려면 꼭 필요한것 empno
		String empno_str = req.getParameter("empno");
		int delete = 0;
		EmpDaoImp empDao = null;
		try {
			int empno = Integer.parseInt(empno_str);
			empDao = new EmpDaoImp();
			delete = empDao.delete(empno);
		} catch (NumberFormatException e){ // null,"11s" 체크할 수있는 장점
			resp.sendError(400); // bad Request
			return;
		} catch (Exception e) {	
			e.printStackTrace(); 
		} finally {
			if (empDao!=null)empDao.close();
		}
		if (delete > 0 ) {
			resp.sendRedirect("list.do");
		} else {
			resp.sendRedirect("./update.do?empno="+empno_str);
			System.out.println("gogo");
		}
	}
}
