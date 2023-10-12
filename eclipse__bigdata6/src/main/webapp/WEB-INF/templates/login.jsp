<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<%@ include file="/headerNav.jsp" %>
	<main class="container">
		<h1 class="text-center my-5">EMP 유저 로그인</h1>
		<form class="col-lg-5 col-md-6 col-sm-8 col-10 m-auto" action="login.do" method="post" name="loginForm">
		  <div class="row mb-3">
		    <label for="inputEmail3" class="col-sm-4 col-form-label">사원번호</label>
		    <div class="col-sm-8">
		      <input type="text" class="form-control" name="empno" id="loginEmpno">
		    </div>
		  </div>
		  <div class="row mb-3">
		    <label for="inputPassword3" class="col-sm-4 col-form-label">비밀번호</label>
		    <div class="col-sm-8">
		      <input type="password" class="form-control" name="pw" id="loginPw">
		    </div>
		  </div>
		  <fieldset class="row mb-3">
		    <legend class="col-form-label col-sm-4 pt-0">상태</legend>
		    <div class="col-sm-8">
		      <div class="form-check">
		        <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="option1" checked>
		        <label class="form-check-label" for="gridRadios1">
		         로그인 유지
		        </label>
		      </div>
		    </div>
		  </fieldset>
		  <div class="d-flex justify-content-end">
			  <button type="submit" class="btn btn-outline-success w-100">로그인</button>
		  </div>
		</form>		
	</main>
</body>
</html>