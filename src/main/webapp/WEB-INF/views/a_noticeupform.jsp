<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
</head>
<%
// 	HkDto dto=(HkDto)request.getAttribute("dto");
%>
<body>
<jsp:useBean id="dto" class="com.hk.sns.dtos.BoardDto" scope="request" />
<h1>게시글 수정하기</h1>
<form action="noticeupboard.do" method="post">
<!-- 	<input type="hidden" name="command" value="updateboard" /> -->
	<input type="hidden" name="seq" value="<jsp:getProperty property="seq" name="dto"/>"/>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td><jsp:getProperty property="seq" name="dto"/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><jsp:getProperty property="id" name="dto"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="<jsp:getProperty property="title" name="dto"/>"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="content"><jsp:getProperty property="content" name="dto"/></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정"/>
				<button type="button" onclick="boardList()">목록</button>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	function boardList(){
		location.href="anoticeboard.do?";
	}
</script>
</body>
</html> 