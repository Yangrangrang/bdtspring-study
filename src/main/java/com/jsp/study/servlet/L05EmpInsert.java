package com.jsp.study.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.study.dto.DeptDto;
import com.jsp.study.dao.ScottDB;

@WebServlet("/emp/insert.do")

public class L05EmpInsert extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sql = "SELECT * FROM DEPT";
		Connection conn = null;
		Statement stmt = null;	// 파라미터가 없을 때 사용하는 객체
		ResultSet rs = null;
		List<DeptDto> deptList = new ArrayList<DeptDto>();
		try {
			Class.forName(ScottDB.DRIVER);
			conn= DriverManager.getConnection(ScottDB.URL,ScottDB.USER,ScottDB.PW);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DeptDto dept = new DeptDto();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				deptList.add(dept);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)rs.close();
				if(conn!=null)rs.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		// pug : pug문서를 엔진으로 html로 렌더해서 해당 문서를 응답 (혼자서 서블릿이 될 수 없다.)
		// jsp : 요청 내역을 jsp문서에 전달해서(요청을 처리) 응답을 위임 (혼자서 서블릿 페이지가 될 수 있다.)
		String title = "사원등록 폼 jsp페이지로 전달된 Object.title";
		req.setAttribute("title", title);	// jsp페이지에 request전담하면서 Object를 같이 보낸다.
		req.setAttribute("deptList", deptList);
		req.getRequestDispatcher("../L05_emp_insert.jsp").forward(req, resp);
//		resp.sendRender("empInsert.pug", {);
		
	}
}
