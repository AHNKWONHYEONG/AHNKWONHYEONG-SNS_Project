<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정폼</title>
<style type="text/css">
	#tablebox{
		width: 500px;margin: 0 auto;
	}
	#replyForm{
		display: none;
	}
	#tablebox{
		overflow: auto;
		height: 500px;
	}
</style>
</head>
<body>
<div id="tablebox">
<h1>유저 게시판 수정하기</h1>
	<form action="uupdateboard.do" method="post">
<!-- 	<input type="hidden" name="command" value="updateboard"/> -->
	<input type="hidden" name="seq" value="${requestScope.dto.seq}"/>
		<table class="table" class="table table-hover">
			<tr>
				<th>작성자</th>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input name="title" value="${dto.title}" required="required" type="text"  class="form-control"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" required="required" rows="10" cols="60"  class="form-control">${dto.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="수정완료" class="btn btn-primary"/>
				<input type="button" value="수정취소" class="btn btn-primary" 
				onclick="location.href='udetailboard.do?seq=${dto.seq}'"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html> 