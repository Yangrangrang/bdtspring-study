<%@page import="com.jsp.study.dto.EmpDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String contextPath=request.getContextPath();
%>
<link rel="stylesheet" href="<%=contextPath%>/public/bootstrap/css/bootstrap.min.css">
<script src="<%=contextPath%>/public/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded",init);
	
	function init(){
		const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
		const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
	}
</script>
		
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
  <div class="container-fluid">
    <a class="navbar-brand" href="<%=contextPath%>">HOME</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item dropdown mx-2">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            사원관리
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<%=contextPath%>/emp/list.do">Model1 servlet 리스트</a></li>
            <li><a class="dropdown-item" href="<%=contextPath%>/emp/list.jsp">Model1 jsp 리스트</a></li>
            <li><a class="dropdown-item" href="<%=contextPath%>/model2/emp/list.do">Model2 리스트</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="<%=contextPath%>/model2/emp/insert.do">Model2 등록</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown mx-2">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            부서관리
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<%=contextPath%>/dept/list.do">Model1 리스트</a></li>
            <li><a class="dropdown-item" href="<%=contextPath%>/model2/dept/list.do">Model2 리스트</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="<%=contextPath%>/model2/dept/insert.do">Model2 등록</a></li>
          </ul>
        </li>
      </ul>
      
<%	// doGet (request,response)
// HttpSession session = request.getSession();
Object loginEmp_obj = session.getAttribute("loginEmp");
//out.print(loginEmp_obj);
%>
      <form class="d-flex mx-2">
	      <%if(loginEmp_obj==null){ %>
		        <a href="<%=contextPath %>/login.do" class="me-2 btn btn-sm btn-outline-light">로그인</a>
		        <a href="<%=contextPath %>/signup.do" class="me-2 btn btn-sm btn-outline-light">회원가입</a>
	      <%} else { 
	      	EmpDto loginEmp = (EmpDto)loginEmp_obj;
	      %>
		        <a href="<%=contextPath %>/model2/emp/detail.do?empno=<%=loginEmp.getEmpno()%>"
		        class="me-2 btn btn-sm btn-outline-light">
			        (<%=loginEmp.getEmpno() %>)
			        <%=loginEmp.getEname() %>님 로그인
		        </a>
		        
		        <a href="<%=contextPath %>/logout.do" class="me-2 btn btn-sm btn-outline-light">로그아웃</a>
	      <%} %>
      </form>
    </div>
  </div>
</nav>


