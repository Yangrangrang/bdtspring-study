<%@page import="com.jsp.study.dto.DeptDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사원 등록 폼</title>
</head>
<body>
<%
	// Jsp 문서는 servlet 자바코드와 html이 역전된 형태
	int i=0;	// 자바 문서를 작성하는 곳 (doGet()함수 내부)
	// HttpServletRequest request
	// HttpServletResponse response
	// PrintWriter out = response.getWriter();
	Object title_obj = request.getAttribute("title");
	Object deptList_obj = request.getAttribute("deptList");
	List<DeptDto> deptList = (List<DeptDto>)deptList_obj;	
	// 검사하고 형변환 하라고 경고!(객체를 만들어서 전달했기 때문에 )
	
	
	
%>
    <h1><%=title_obj%></h1>
    <form action="insert.do" method="POST" name="empInsert">
        <p>
            <label>
                사번:
                <input type="text" value="1111" name="empno" >
                ((PK)대표키는 UK + Not Null + Index) 
            </label>
            <br>
            <strong id="empnoMsg"></strong>
        </p>
        <p>
            <label>
                이름:
                <input type="text" value="홍길동" name="ename">
            </label>
        </p>
        <p>
            <label>
                직책:
                <input type="text" value="프로그래머" name="job">
            </label>
        </p>
        <p>
            <label>
                상사:
;                <input type="text" value="7839" name="mgr">
                (존재하지 않는 사원은 참조할 수 없다(참조의 무결성 err))
            </label>
            <br>
            <strong id="mgrMsg"></strong>
        </p>
        <p>
            <label>
                입사일:
                <script>
                    document.write(new Date())
                </script>
            </label>
        </p>
        <p>
            <label>
                급여:
                <input type="text" value="1000" name="sal">
            </label>
        </p>
        <p>
            <label>
                커미션:
                <input type="text" value="10" name="comm">
                (null 데이터는 문자열 "null"을 출력하면 안된다.)
            </label>
        </p>
        <p>
            <label>
                부서:
                <select type="text" value="40" name="deptno">
                    <%
                    // html은 reqonse.getWriter().append()로 출력 중이다.
                    for (DeptDto dept : deptList){
	                    //	out.append("<option value='"+dept.getDeptno()+"'>"+dept.getDname()+"</option>");
	                    // out.append("") == < %=""% >
	                    
                    %>
	                    <option value="<%=dept.getDeptno()%>"><%=dept.getDname()%>(<%=dept.getDeptno()%>)</option>
	                <%
                    }
                    
                    %>
                </select>        
                (존재하지 않는 부서를 참조할 수 없다(참조의 무결성 err))
            </label>
        </p>
        <p>
            <button type="reset">초기화</button>
            <button>제출</button>
        </p>
    </form>


</body>
</html>