package com.jsp.study.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.study.dto.EmpDto;
import com.mysql.cj.jdbc.Driver;


// com.jsp.study.servlet.L01EmpList => web.xml에 동적파일의 경로를 등록해야한다.
public class L01EmpList extends HttpServlet{
// 톰캣 서버에서 실행하는 동적 파일이 되려면 타입이 HttpServlet이여야 한다.
// 톰캣 서버의 동적파일을 서블릿이라 부른다.
// HttpServletRequest reg : 요청 정보 (url, queryString, client 정보, cookie....)
// HttpServletResponse resp : 응답할 내역
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/////////////////////////////요청처리 시작/////////////////////////////
		System.out.println("/emp/list.do 를 Get 방식으로 요청");
		int page = (req.getParameter("page")!=null)? Integer.parseInt(req.getParameter("page")) : 1;	// 요청하는 파라미터
		/////////////////////////////요청처리 종료/////////////////////////////
		
		/////////////////////////////서비스 시작//////////////////////////////
		String url="jdbc:mysql://localhost:3306/SCOTT";
		String user = "root";
		String pw = "root";
		
		
		
		
		resp.setHeader("content-type", "text/html;charset=utf-8");
		StringBuffer sb = new StringBuffer();
		
		
		
		https://www.figma.com/file/0MDajUMbHD4VBEE9RadXeo/project1%EC%A1%B0?node-id=0%3A1&t=usP4io51RQmeFA51-1
		sb.append("<h1>사원 리스트 입니다. Page = "+page+"</h1>");
		sb.append("<p><a href='insert.do'>사원 등록 폼</a></p>");
		// try(closing block 통신객체를 생성하면 자동으로 close한다.){}
		Connection conn = null;
		try {
			int rows = 5;
			int startRow = (page - 1) * rows;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pw);
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM EMP LIMIT ?,?");	// 쿼리를 가져와야한다.
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, rows);
			ResultSet rs = pstmt.executeQuery();
			List<EmpDto> empList = new ArrayList();
			while(rs.next()) {
				EmpDto emp=new EmpDto();
				emp.setEmpno(rs.getInt("empno"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setDeptno(rs.getInt("deptno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setSal(rs.getFloat("sal"));
				emp.setComm(rs.getFloat("comm"));
				emp.setHiredate(rs.getDate("hiredate"));
				empList.add(emp);
			}
		/////////////////////////////서비스 종료//////////////////////////////
			
		/////////////////////////////뷰 시작/////////////////////////////////
			sb.append("<table>");
			sb.append("<tr><th>사번</th><th>이름</th><th>직책</th><th>급여</th><th>부서</th></tr>");
			
			System.out.println(conn);
			for (EmpDto emp: empList) {
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<a href='detail.do?empno="+emp.getEmpno()+"'>"+emp.getEmpno()+"</a>");
				sb.append("</td>");
				sb.append("<td>"+emp.getEname()+"</td>");
				sb.append("<td>"+emp.getJob()+"</td>");
				sb.append("<td>"+emp.getSal()+"</td>");
				sb.append("<td>"+emp.getDeptno()+"</td>");
				sb.append("</tr>");
				
			}
			sb.append("</table>");
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				if(conn!=null)conn.close();
			} catch(Exception e2) {}
		}
		sb.append("<nav>");
		sb.append("<ul>");
		sb.append("<li><a href='?page=1'>1</a></li>");
		sb.append("<li><a href='?page=2'>2</a></li>");
		sb.append("<li><a href='?page=3'>3</a></li>");
		sb.append("<li><a href='?page=4'>4</a></li>");
		sb.append("</ul>");
		sb.append("</nav>");
		/////////////////////////////뷰 시작/////////////////////////////////
		
		/////////////////////////////응답 시작///////////////////////////////
		resp.getWriter().append(sb.toString());
		/////////////////////////////응답 종료///////////////////////////////
	}
}
