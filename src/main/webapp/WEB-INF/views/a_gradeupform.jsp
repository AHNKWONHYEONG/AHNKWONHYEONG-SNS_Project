<%@include file="header.jsp" %>
<%@page import="com.hk.sns.dtos.LDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%
	response.setHeader("Cache-control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<%
	LDto dto=(LDto)request.getAttribute("dto");
%>
<body>
<div id="tablebox">
<h1>회원등급변경하기</h1>
<form action="afterupdaterole.do" method="post">
	<input type="hidden" name="id" value="<%=dto.getId() %>" />
	<table  class="table table-hover">
		<tr>
			<th>아이디</th>
			<td><%=dto.getId() %></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=dto.getName() %></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=dto.getEmail() %></td>
		</tr>
		<tr>
			<th>등급</th>
			<td>
				<select name="grade">
					<option value="ADMIN"<%=dto.getGrade().equals("ADMIN")?"selected":"" %>>관리자</option>
					<option value="MANAGER" <%=dto.getGrade().equals("MANAGER")?"selected":"" %>>정회원</option>
					<option value="USER" <%=dto.getGrade().equals("USER")?"selected":"" %>>일반회원</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input class="btn btn-success" value="변경완료" />
				<input class="btn btn-info" type="button" value="목록" onclick="location.href='userlist.do'" />
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>





