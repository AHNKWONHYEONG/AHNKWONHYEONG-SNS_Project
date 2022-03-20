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
		width: 600px;margin: 0 auto;
	}
	
</style>
</head>
<body>
<div id="tablebox">
<h1>메인게시글 수정하기</h1>
	<form action="mypostupdate.do" method="post" enctype="multipart/form-data">
<!-- 	<input type="hidden" name="command" value="updateboard"/> -->
	<input type="hidden" name="id" value="${requestScope.dto.id}"/>
	<input type="hidden" name="seq" value="${requestScope.dto.seq}"/>
		<table class="table" class="table table-hover">
			<tr>
				<th>작성자</th>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>${dto.category }</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td>
					<img src="upload/${dto.img_1}" width="300px;"
					height="300px;">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${dto.content}</td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="답글달기" class="btn btn-primary"/>
				<input type="button" value="돌아가기" class="btn btn-primary" 
				onclick="location.href='other1.do?id=${ldto.id}&category=${dto.category}'"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html> 