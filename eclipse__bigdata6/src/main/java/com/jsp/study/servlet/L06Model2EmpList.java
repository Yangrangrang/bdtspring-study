package com.jsp.study.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.study.dao.EmpDaoImp;
import com.jsp.study.dto.EmpDto;

@WebServlet("/model2/emp/list.do")	// 상대경로 두가지 emp/ , /emp/
public class L06Model2EmpList extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 처리와 응답 처리 =>Servlet controller가 하는 일
		int page = (req.getParameter("page")!=null)?Integer.parseInt(req.getParameter("page")):1;
		int rows = (req.getParameter("rows")!=null)?Integer.parseInt(req.getParameter("rows")):5;
		List<EmpDto> empList = null;
		EmpDaoImp empDao = null;
		
		try {
			empDao = new EmpDaoImp();
			empList = empDao.list(page, rows);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (empDao!=null)empDao.close();
		}
//		System.out.println(empList);
		if (empList == null) {	// db서버 오류 => 오류페이지 500 or 이전페이지 or 처음화면으로 보냄
//			resp.sendRedirect(req.getContextPath()+ "/");
			resp.sendError(500);
		} else {
			req.setAttribute("empList", empList);
			req.getRequestDispatcher("/WEB-INF/templates/emp/list.jsp").forward(req, resp);
		}
	}
}
