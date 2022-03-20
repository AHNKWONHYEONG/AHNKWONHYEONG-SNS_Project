<%@include file="header.jsp" %>
<%@page import="com.hk.sns.dtos.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 공지게시판 상세보기</title>
</head>
<%
	BoardDto dto=(BoardDto)request.getAttribute("dto");
%>
<body>
<h1>유저 공지게시판 상세보기</h1>
<table border="1">
	<tr>
		<th>글번호</th>
		<td><%=dto.getSeq()%></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=dto.getId()%></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><%=dto.getTitle()%></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent()%></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<button onclick="boardList()">목록</button>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function boardList(){
		location.href="unoticeboard.do";
	}
</script>
</body>
</html> 