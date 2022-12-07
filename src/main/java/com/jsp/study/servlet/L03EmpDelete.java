package com.jsp.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.study.dao.ScottDB;

@WebServlet("/emp/delete.do")	// 컴파일 시 자동으로 dd에 url 등록하는 어노테이션

public class L03EmpDelete extends HttpServlet{
	// GET : url에 파라미터(queryString)를 포함하는 요청방식, url을 공유할 수 있다. (브라우저 마다 길이 제한이 있고 보안에 위험이 있다.)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empno_str = req.getParameter("empno");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int delete = 0;
		String sql = "DELETE FROM EMP WHERE empno=?";	// DML(insert,delete,update) : 몇개 성공했다는 결과(int,long)
		try {
			int empno = Integer.parseInt(empno_str);
			Class.forName(ScottDB.DRIVER);
			conn = DriverManager.getConnection(ScottDB.URL,ScottDB.USER,ScottDB.PW);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			delete = pstmt.executeUpdate();	// DML sql을 실행하는 메소드
		} catch (NumberFormatException e) {
			resp.sendError(400); //empno가 잘못오거나 없으면 안되는 페이지 bad Request
		} catch (Exception e) {
			e.printStackTrace(); // db 접속이나 sql 오류
		}
		PrintWriter out = resp.getWriter();
		if (delete>0) {
			out.append("<h1>삭제 성공입니다. 리스트로 가세요!</h1>");
			resp.sendRedirect("list.do");
		} else {
			resp.sendRedirect("detail.do?empno="+empno_str);
		}
		
	}
	
}
