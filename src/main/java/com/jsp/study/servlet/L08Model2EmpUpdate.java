package com.jsp.study.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.study.dao.DeptDao;
import com.jsp.study.dao.DeptDaoImp;
import com.jsp.study.dao.EmpDaoImp;
import com.jsp.study.dto.DeptDto;
import com.jsp.study.dto.EmpDto;

@WebServlet("/model2/emp/update.do")
public class L08Model2EmpUpdate extends HttpServlet{
	
	//update action
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
		int update = 0;
//		SimpleDateFormat : Date 객체를 특정 문자열 형식으로 반환하거나 문자열을 데이트로 파싱하는 객체
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EmpDaoImp empDao = null;
		try {
//			System.out.println(sdf.parse("1986-05-25"));
			EmpDto emp = new EmpDto();
			emp.setEmpno(Integer.parseInt(empno_str));
			emp.setMgr((!mgr_str.trim().equals(""))?Integer.parseInt(mgr_str):null);
			emp.setDeptno((!deptno_str.trim().equals(""))?Integer.parseInt(deptno_str):null);
			emp.setComm((!comm_str.trim().equals(""))?Float.parseFloat(comm_str):null);
			emp.setSal((!sal_str.trim().equals(""))?Float.parseFloat(sal_str):null);
			emp.setHiredate(sdf.parse(hiredate_str));
			emp.setEname(ename);
			emp.setJob(job);
			empDao = new EmpDaoImp();
			update = empDao.update(emp);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(empDao!=null)empDao.close();
		}
		
		if (update > 0) {	// 상세페이지로
			resp.sendRedirect("detail.do?empno="+empno_str);
		} else {	// 다시 수정페이지로
			resp.sendRedirect("update.do?empno="+empno_str);
		}
	}
	
	
	// update form
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("empno")==null) {
			resp.sendError(400);
			return;	// 함수 실행 종료
		} 
		EmpDto emp = null;
		EmpDaoImp empDao = null;
		DeptDao deptDao = null;
		List<DeptDto> deptList = null;
		try {
			int empno = Integer.parseInt(req.getParameter("empno"));
			empDao = new EmpDaoImp();
			emp = empDao.detail(empno);
			
			deptDao = new DeptDaoImp();
			deptList = deptDao.list();	// select options
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(empDao!=null)empDao.close();
			if(deptDao!=null)deptDao.close();
		}
		if(emp!=null) {
			req.setAttribute("emp", emp);
			req.setAttribute("deptList", deptList);
			req.getRequestDispatcher("/WEB-INF/templates/emp/update.jsp").forward(req, resp);
		} else {	// emp==null : 오류가 있거나 삭제된 레코드
			resp.sendRedirect("list.do");
		}
	}
}
