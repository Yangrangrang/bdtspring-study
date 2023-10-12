<%@page import="com.jsp.study.dto.EmpDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사원 등록 페이지</title>
</head>
<body>
	<%@include file="/headerNav.jsp" %>
	<main class="container">
	    <h1 class="mt-5 text-center"> 사원 등록 페이지 (등록 FORM)</h1>
	    <form action="insert.do" method="POST" name="empInsert" class="m-auto col-xl-5 col-lg-7 col-sm-10">
	        <p class="form-floating">
                <input class="form-control" type="text" value="" name="empno" placeholder="id">
	            <label>사번: (중복을 허용하지 않는다) </label>
	        </p>
	        <p class="form-floating">
                <input class="form-control" type="text" value="" name="ename" placeholder="id">
	            <label>이름:</label>
	        </p>
	        <p class="form-floating"> 
                <input class="form-control" type="text" value="" name="job" placeholder="id">
	            <label>직책:</label>
	        </p>
	        <p class="form-floating">
                <input class="form-control" type="text" value="" name="mgr" placeholder="id">
	            <label>
	                상사:
	                (존재하지 않는 사원은 참조할 수 없다(참조의 무결성 err))
	            </label>
	        </p>
	        <p class="form-floating">
                <input class="form-control" type="date" value="" name="hiredate" placeholder="id">
	            <label>
	                입사일:
	                (input type="date" 문자열="yyyy-MM-dd")
	            </label>
	        </p>
	        <p class="form-floating">
                <input class="form-control" type="text" value="" name="sal" placeholder="id">
	            <label>
	                급여:
	            </label>
	        </p>
	        <p class="form-floating">
                <input class="form-control" type="text" value="" name="comm" placeholder="id">
	            <label>
	                커미션:
	                (null 데이터는 문자열 "null"을 출력하면 안된다.)
	            </label>
	        </p>
	        <p class="form-floating">
                <input class="form-control" type="text" value="" name="deptno" placeholder="id">
	            <label>
	                부서:
	                (존재하지 않는 부서를 참조할 수 없다(참조의 무결성 err))
	
	            </label>
	        </p>
	        <p>
	            <button class="btn btn-outline-warning" type="reset">초기화</button>
	            <button class="btn btn-outline-primary">제출</button>
	        </p>
	    </form>
    </main>
</body>
</html>