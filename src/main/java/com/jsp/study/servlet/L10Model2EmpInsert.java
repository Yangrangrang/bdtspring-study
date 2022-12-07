package com.jsp.study.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.study.dao.EmpDaoImp;
import com.jsp.study.dto.EmpDto;

@WebServlet("/model2/emp/insert.do")
public class L10Model2EmpInsert extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/templates/emp/insert.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String empno_str=req.getParameter("empno");
		String ename=req.getParameter("ename");
		String job=req.getParameter("job");
		String mgr_str=req.getParameter("mgr");
		String hiredate_str=req.getParameter("hiredate");
		String sal_str=req.getParameter("sal");
		String comm_str=req.getParameter("comm");
		String deptno_str=req.getParameter("deptno");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		// input : date value = "1992-09-17"
		// input : datetime value = "1992-09-17T13:23" "yyyy-MM-dd'T'hh:mm"	(T가 예약어가 있다면 ''로)
		EmpDaoImp empDao = null;
		int insert = 0;
		try {
			EmpDto emp = new EmpDto();
			emp.setEmpno(Integer.parseInt(empno_str));
			emp.setMgr((!mgr_str.trim().equals(""))?Integer.parseInt(mgr_str):null);
			emp.setDeptno((!deptno_str.trim().equals(""))?Integer.parseInt(deptno_str):null);
			emp.setComm((!comm_str.trim().equals(""))?Float.parseFloat(comm_str):null);
			emp.setSal((!sal_str.trim().equals(""))?Float.parseFloat(sal_str):null);
			emp.setHiredate(sdf.parse(hiredate_str));
			emp.setEname(ename);
			emp.setJob(job);
			System.out.println(emp);
			empDao = new EmpDaoImp();
			insert = empDao.insert(emp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (insert > 0) {
			resp.sendRedirect("detail.do?empno="+empno_str);
		} else {
			resp.sendRedirect("insert.do");
		}
	}
	
}
