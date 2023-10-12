package com.jsp.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.study.dao.ScottDB;
import com.jsp.study.dto.EmpDto;

public class L02EmpDetail extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("content-type", "text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String empno_str = req.getParameter("empno");
		String sql = "SELECT * FROM EMP WHERE empno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmpDto emp = null;
		try {
			int empno = Integer.parseInt(empno_str);	// 무조건 정수의 사번이 와야 db접속 가능
			
			Class.forName(ScottDB.DRIVER);
			conn = DriverManager.getConnection(ScottDB.URL,ScottDB.USER,ScottDB.PW);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			while(rs.next()) {	// {empno:4444, sal: 123.00 , comm:null}
				emp = new EmpDto();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
//				emp.setDeptno(rs.getInt("deptno"));	// getInt data null 이면 0으로 바꾼다.
				emp.setDeptno((Integer)rs.getObject("deptno"));	// getInt data null 이면 0으로 바꾼다.
				emp.setMgr((Integer)rs.getObject("mgr"));
				emp.setSal(rs.getFloat("sal"));
				emp.setComm((Float)rs.getObject("comm"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setJob(rs.getString("job"));
			}
		} catch  (NumberFormatException e) {
			e.printStackTrace();	//여기서 bad Request 400 (톰캣이 400 애러는 자동 처리 불능)
			resp.setStatus(400);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(emp!=null) {
			System.out.println(emp);
			out.append("<h1>사원 상세페이지 입니다.</h1>");
			String html="    <form action='update.do' method='POST' name='empUpdate'>"
					+ "        <p>"
					+ "            <label>"
					+ "                사번:"
					+ "                <input type='text' value='"+emp.getEmpno()+"' name='empno' readonly>"
					+ "                (대표키는 바꾸지 않는다.) "
					+ "            </label>"
					+ "        </p>"
					+ "        <p>"
					+ "            <label>"
					+ "                이름:"
					+ "                <input type='text' value='"+emp.getEname()+"' name='ename'>"
					+ "            </label>"
					+ "        </p>"
					+ "        <p>"
					+ "            <label>"
					+ "                직책:"
					+ "                <input type='text' value='"+emp.getJob()+"' name='job'>"
					+ "            </label>"
					+ "        </p>"
					+ "            <label>"
					+ "                상사:"
					+ "                <input type='text' value='"+(emp.getMgr()==null?"":emp.getMgr())+"' name='mgr'>"
					+ "                (존재하지 않는 사원은 참조할 수 없다(참조의 무결성 err))"
					+ "            </label>"
					+ "        </p>"
					+ "        <p>"
					+ "            <label>"
					+ "                입사일:"
					+ "                <input type='date' value='"+emp.getHiredate()+"' name='hiredate'>"
					+ "                (input type='date' 문자열='yyyy-MM-dd')"
					+ "            </label>"
					+ "        </p>"
					+ "        <p>"
					+ "            <label>"
					+ "                급여:"
					+ "                <input type='text' value='"+emp.getSal()+"' name='sal'>"
					+ "            </label>"
					+ "        </p>"
					+ "        <p>"
					+ "            <label>"
					+ "                커미션:"
					+ "                <input type='text' value='"+(emp.getComm()==null?"":emp.getComm())+"' name='comm'>"
					+ "                (null 데이터는 문자열 'null'을 출력하면 안된다.)"
					+ "            </label>"
					+ "        </p>"
					+ "        <p>"
					+ "            <label>"
					+ "                부서:"
					+ "                <input type='text' value='"+(emp.getDeptno()==null?"":emp.getDeptno())+"' name='deptno'>"
					+ "                (존재하지 않는 부서를 참조할 수 없다(참조의 무결성 err))"
					+ ""
					+ "            </label>"
					+ "        </p>"
					+ "        <p>"
					+ "            <button type='reset'>초기화</button>"
					+ "            <button>제출</button>"
					+ "            <a id='empDelBtn' href='delete.do?empno="+emp.getEmpno()+"'>삭제</a>"
					+ "            (ON DELETE SET NULL : 상사로 참조되는 사원을 삭제하면 자식들의 MGR이 null이된다.)"
					+ "        </p>"
					+ "    </form>";
			out.append(html);
			
		} else {	// 조회하기 직전에 레코드가 삭제된 경우!
//			out.append("<h1>해당 레코드가 삭제되었습니다</h1>");
//			out.append("<h2><a href='list.do'>사원리스트 다시 조회하기</h2>");
			resp.sendRedirect("list.do");	// sendRedirect (status 302) : redirect상태
		}
		
		
		
		
	}

}
