<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!-- scope객체: ${requestScope.name}, ${sessionScope.name} -->
<!-- script요소: request.getArrtibute("list") -->
<!-- parameter: ${param} -->
<!-- request.getParameter("msg") -->
<h1>시스템오류가발생했습니다.(관리자에게 문의하세요)</h1>
<h2>오류내용:${param.msg}</h2>
<h3><a href="index.jsp">메인</a></h3>
</body>
</html> 