<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.jsp.study.dto.DeptDto"%>
<%@page import="java.util.List"%>
<%@page import="com.jsp.study.dto.EmpDto"%>
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
	<%
	EmpDto emp = (EmpDto)request.getAttribute("emp"); 
	List<DeptDto> deptList = (List<DeptDto>)request.getAttribute("deptList");
	%>
	<p><%=deptList %></p>
	<main class="container">
	    <h1 class="mt-5 text-center"> 사원 상세 페이지 (수정 FORM)</h1>
	    <form action="update.do" method="POST" name="empUpdate" class="m-auto col-xl-5 col-lg-7 col-sm-10">
	        <p class="form-floating">
                <input class="form-control" type="text" value="<%=emp.getEmpno() %>" name="empno" readonly placeholder="id">
	            <label>사번:</label>
	        </p>
                (대표키는 바꾸지 않는다.) 
	        <p class="form-floating">
                <input class="form-control" type="text" value="<%=emp.getEname() %>" name="ename" placeholder="id">
	            <label>이름:</label>
	        </p>
	        <p class="form-floating"> 
                <input class="form-control" type="text" value="<%=emp.getJob() %>" name="job" placeholder="id">
	            <label>직책:</label>
	        </p>
	        <p class="form-floating">
                <input class="form-control" type="text" value="<%=((emp.getMgr()!=null)?emp.getMgr():"") %>" name="mgr" placeholder="id">
	            <label>
	                상사:
	                (존재하지 않는 사원은 참조할 수 없다(참조의 무결성 err))
	            </label>
	        </p>
	        <p class="form-floating">
                <input class="form-control" type="date" value="<%=emp.getHiredate() %>" name="hiredate" placeholder="id">
	            <label>
	                입사일:
	                (input type="date" 문자열="yyyy-MM-dd")
	            </label>
	        </p>
	        <p class="form-floating">
                <input class="form-control" type="text" value="<%=emp.getSal() %>" name="sal" placeholder="id">
	            <label>
	                급여:
	            </label>
	        </p>
	        <p class="form-floating">
                <input class="form-control" type="text" value="<%=((emp.getComm()!=null)?emp.getComm():"") %>" name="comm" placeholder="id">
	            <label>
	                커미션:
	                (null 데이터는 문자열 "null"을 출력하면 안된다.)
	            </label>
	        </p>
	        <p class="form-floating">
	        <!-- {10:false. 20:false, 30:true, 40:false} js Object => Map -->
	        <%
	        /* Map<Integer,Boolean> deptSelected = new HashMap<Integer, Boolean>();
	        deptSelected.put(null,false);	// 선택안됨 
	        for (DeptDto d : deptList){
	        	deptSelected.put(d.getDeptno(), false);
	        }
	        deptSelected.put(emp.getDeptno(), true); */
	        %>
	        	<select class="form-control" name="deptno">
	                <option value="" 
	                	<% if(emp.getDeptno()==null){out.append("selected");} %> 
	                >
	                	선택안됨
	                </option>
	                <%for (DeptDto dept : deptList){  %>
	                	<option value="<%=dept.getDeptno()%>"
	                		<%if(dept.getDeptno()==emp.getDeptno()){out.append("selected"); }%> 
	                	>
	                		<%=dept.getDname() %>
	                		(<%=dept.getLoc() %>)
	                	</option>
	                <%} %>
                </select>
	            <label>
	                부서:(존재하지 않는 부서를 참조할 수 없다(참조의 무결성 err))
	            </label>
	        </p>
	        <p>
	            <button class="btn btn-outline-warning" type="reset">초기화</button>
	            <button class="btn btn-outline-primary">제출</button>
	            <a data-bs-toggle="tooltip" data-bs-placement="right"
	            data-bs-title ="(ON DELETE SET NULL : 상사로 참조되는 사원을 삭제하면 자식들의 MGR이 null이된다.)"
	            class="btn btn-outline-danger" id="empDelBtn" href="delete.do?empno=<%=emp.getEmpno()%>">삭제</a>
	            
	        </p>
	    </form>
    </main>
</body>
</html>