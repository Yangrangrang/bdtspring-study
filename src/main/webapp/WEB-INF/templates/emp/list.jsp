<%@page import="com.jsp.study.dto.EmpDto"%>
<%@page import="com.jsp.study.dao.EmpDaoImp"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! //전체 영역 필드  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 리스트</title>
</head>
<style>
	#empTable>tbody>tr:hover{
		cursor: pointer;
	}
</style>
<body>
	<%@include file="/headerNav.jsp" %>
	<main class="container">
		<h1 class="mt-5">사원 리스트</h1>

		<table id="empTable" class="table table-striped table-hover">
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
				<% Object empList_obj = request.getAttribute("empList"); %>
				<%if(empList_obj!=null){
					List<EmpDto> empList = (List<EmpDto>)empList_obj;
					for (EmpDto emp : empList){
				%>
					<tr data-bs-toggle="tooltip" data-bs-title="사원상세"
						onclick="location.href='detail.do?empno=<%=emp.getEmpno() %>'">
						<td><%=emp.getEmpno() %></td>
						<td><%=emp.getEname() %></td>
						<td><%=emp.getJob() %></td>
						<td><%=emp.getMgr() %></td>
						<td><%=emp.getHiredate() %></td>
						<td><%=emp.getSal() %></td>
						<td><%=emp.getComm() %></td>
						<td><%=emp.getDeptno() %></td>
					</tr>
				<%
					}
				}
				%>
			</tbody>
		</table>
		<nav class="d-flex justify-content-center my-4">
		  <ul class="pagination">
		    <li class="page-item"><a class="page-link" href="?page=">Previous</a></li>
		    <li class="page-item"><a class="page-link" href="?page=1">1</a></li>
		    <li class="page-item"><a class="page-link" href="?page=2">2</a></li>
		    <li class="page-item"><a class="page-link" href="?page=3">3</a></li>
		    <li class="page-item"><a class="page-link" href="?page=">Next</a></li>
		  </ul>
		</nav>
		<h2>a 태그의 사용 원리</h2>
		<ul>
			<li>get 방식으로 링크로 걸린 url로 이동</li>
			<li>a 태그가 많이 참조한 사이트를 검색엔진이 검색 우선 순위로 올린다.</li>
			<li>nav가 자손으로 a가 있으면 해당사이트의 구조인 네비게이션을 파악</li>
			<li>a 태그가 할 수 있는 일을 onclick으로 정의하는 것은 좋은 방법이 아니다.</li>
			 
		</ul>
		
	</main>
</body>
</html>