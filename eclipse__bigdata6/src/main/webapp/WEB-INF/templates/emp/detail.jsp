<%@ page import="com.jsp.study.dto.EmpDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사원 상세 페이지(수정)</title>
</head>
<body>
	<%@include file="/headerNav.jsp" %>
	<%EmpDto emp = (EmpDto)request.getAttribute("emp"); %>
    <main class="container">
	    <h1 class="mt-5 mb-4"> 사원 상세 페이지</h1>
	    <p>
	    	<a href="update.do?empno=<%=emp.getEmpno()%>"
	    	class="btn-outline-primary float-end">사원 수정 페이지</a>
	    </p>
        <p>
            <label>
                사번: <%=emp.getEmpno() %>
            </label>
        </p>
        <p>
            <label>
                이름: <%=emp.getEname() %>
            </label>
        </p>
        <p>
            <label>
                직책: <%=emp.getJob() %>
            </label>
        </p>
        <p>
            <label>
                상사: <%=emp.getMgr() %>
            </label>
        </p>
        <p>
            <label>
                입사일: <%=emp.getHiredate() %>
            </label>
        </p>
        <p>
            <label>
                급여: <%=emp.getSal() %>
            </label>
        </p>
        <p>
            <label>
                커미션: <%=emp.getComm() %>
            </label>
        </p>
        <%if(emp.getDept()!=null){ %>
	        <p>
	            <label>
	                부서번호: <%=emp.getDept().getDeptno() %>
	            </label>
	        </p>
	        <p>
	            <label>
	                부서이름: <%=emp.getDept().getDname() %>
	            </label>
	        </p>
	        <p>
	            <label>
	                부서위치: <%=emp.getDept().getLoc() %>
	            </label>
        	</p>
        <%} else { %>
	        <p>
	            <label>
	                부서: 배치된 부서가 없다.
	            </label>
        	</p>
        <%} %>
    </main>
</body>
</html>