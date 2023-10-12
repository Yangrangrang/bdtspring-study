package com.jsp.study.servlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.study.dao.ScottDB;
import com.jsp.study.dto.EmpDto;

@WebServlet("/emp/update.do")
public class L04EmpUpdate extends HttpServlet{

	// post 요청은 form의 양식을 파라미터로 요청하는 방식으로 요청 본문에 포함해서 서버에 보낸다.
	// 공유할 수 없는 주소로 새로고침을 피해야하고 get방식보다 서버에 요청하는 속도가 느리다. 
	// 파라미터 길이제한이 없고 보안이 조금 강하고 Blob데이터(파일)을 포함할 수 있다.
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 모든 요청 처리는 파라미터 받는 것부터 시작
		String ename = req.getParameter("ename");
		String job = req.getParameter("job");
		String hiredate = req.getParameter("hiredate");	// 1981-05-01 (html input : date -> mysql date로 파싱가능한 문자열)
		String empno_str = req.getParameter("empno");
		String mgr_str = req.getParameter("mgr");
		String sal_str = req.getParameter("sal");
		String comm_str = req.getParameter("comm");
		String deptno_str = req.getParameter("deptno");
		System.out.println("deptno_str: " + deptno_str);
		System.out.println("deptno_str.equals(\"\"): " + deptno_str.equals(""));
		// input name = "deptno" value = "" => &deptno = & => ""
		// input name = "deptno" value = "" => & => null
		String sql = "UPDATE EMP SET ename=?,job=?,mgr=?,deptno=?,sal=?,comm=?,hiredate=? WHERE empno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int update = 0;
		try {
			EmpDto emp = new EmpDto();
			emp.setEname(ename);
			emp.setJob(job);
			emp.setEmpno(Integer.parseInt(empno_str)); 	// null 일수 없는 데이터
			emp.setMgr(((mgr_str == null || mgr_str.equals(""))?null : Integer.parseInt(mgr_str)));
			emp.setDeptno(((deptno_str == null || deptno_str.equals(""))?null : Integer.parseInt(deptno_str)));
			emp.setSal(Float.parseFloat(sal_str)); 		// null 일수 없는 데이터
			emp.setComm(((comm_str == null || comm_str.equals(""))?null : Float.parseFloat(comm_str)));
			Class.forName(ScottDB.DRIVER);
			conn = DriverManager.getConnection(ScottDB.URL,ScottDB.USER,ScottDB.PW);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getEname());
			pstmt.setString(2, emp.getJob());
			pstmt.setObject(3, emp.getMgr());	// null 일수 있는 것은 Object
			pstmt.setObject(4, emp.getDeptno());
			pstmt.setFloat(5, emp.getSal());
			pstmt.setObject(6, emp.getComm());
			pstmt.setString(7, hiredate);
			pstmt.setInt(8, emp.getEmpno());
			update = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		if (update > 0) {
			resp.sendRedirect("list.do");
		} else {	// 파라미터 파싱 오류가 생기면 수정 실패로 취급
			resp.sendRedirect("detail.do?empno="+empno_str);
		}
	}
}
