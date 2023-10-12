<%@page import="com.jsp.study.dao.ScottDB"%>
<%@page import="com.jsp.study.dto.EmpDto"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!	// 전체 영역 필드 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 리스트</title>
</head>
<body>
	<%@include file="/headerNav.jsp" %>
	<main class="container">
		<h1 class="mt-5">사원 리스트</h1>
<%	// doGet(request,response) 내부
int pageParam = (request.getParameter("page")!=null)?Integer.parseInt(request.getParameter("page")):1;
int rows = 5;
int startRow = (pageParam-1) * rows;
String sql = "SELECT * FROM EMP ORDER BY empno DESC LIMIT ?,?";
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
List<EmpDto> empList = null;
try {
	conn = ScottDB.getConnection();	// 싱글톤 패턴으로 받아오는 커넥션
	pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, startRow);
	pstmt.setInt(2, rows);
	rs= pstmt.executeQuery();
	empList = new ArrayList<EmpDto>();
	while(rs.next()) {
		EmpDto emp=new EmpDto();
		emp.setEmpno(rs.getInt("empno"));
		emp.setMgr((Integer)rs.getObject("mgr"));
		emp.setDeptno((Integer)rs.getObject("deptno"));
		emp.setEname(rs.getString("ename"));
		emp.setJob(rs.getString("job"));
		emp.setSal(rs.getFloat("sal"));
		emp.setComm((Float)rs.getObject("comm"));
		emp.setHiredate(rs.getDate("hiredate"));
		empList.add(emp);
	}
} catch (Exception e){
	e.printStackTrace();
} finally {
	ScottDB.close(rs, pstmt, conn);
}
%>
		<h2>보안에 취약한 jsp 문서</h2>
		<ul>
			<li>톰캣에서 사용가능한 템플릿 엔진인 jsp는 서버사이드 뷰로 자바코드를 작성할 수 있고 서블릿으로도 동작한다.</li>
			<li>jsp는 자바와는 다르게 (컴파일 되지 않고 그대로 실행됨) class파일로 변환(컴파일) 없이 실행되기(인터프리터, 스크립트) 때문에 개발시간을 단축할 수 있다.</li>
			<li>톰캣이 jsp를 기본 정적파일 취급을 해서 Pulic(정적파일의 위피)한 폴더에 작성 가능하기 떄문에 보안 취약하다.</li>
			<li>그래서 jsp를 쓰지 않아요. 물론 해결방법은 있다</li>
			<li>해결방법 : webapp/WEP-INF/ 웹 설정을 하는 배포되지 않는 폴더에서만 jsp를 작성하고 오직 뷰로만 (db작성하는 코드없이) 작성하면 된다.</li>
		</ul>

		<table class="table table-striped">
			<thead>
				<tr>
					<th class="col">사번</th>
					<th class="col-2">이름</th>
					<th class="col-2">직책</th>
					<th class="col">상사</th>
					<th class="col-2">입사일</th>
					<th class="col">급여</th>
					<th class="col">커미션</th>
					<th class="col">부서</th>
				</tr>
			</thead>
			<tbody>
				<%for (EmpDto emp : empList){ %>
				<tr>
					<td><%=emp.getEmpno() %></td>
					<td><%=emp.getEname() %></td>
					<td><%=emp.getJob() %></td>
					<td><%=emp.getMgr() %></td>
					<td><%=emp.getHiredate() %></td>
					<td><%=emp.getSal() %></td>
					<td><%=emp.getComm() %></td>
					<td><%=emp.getDeptno() %></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<nav class="d-flex justify-content-center my-4">
		  <ul class="pagination">
		    <li class="page-item"><a class="page-link" href="?page=<%=pageParam-1%>">Previous</a></li>
		    <li class="page-item"><a class="page-link" href="?page=1">1</a></li>
		    <li class="page-item"><a class="page-link" href="?page=2">2</a></li>
		    <li class="page-item"><a class="page-link" href="?page=3">3</a></li>
		    <li class="page-item"><a class="page-link" href="?page=<%=pageParam+1%>">Next</a></li>
		  </ul>
		</nav>
	</main>
</body>
</html>