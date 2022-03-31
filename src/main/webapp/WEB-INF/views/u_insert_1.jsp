<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 추가하기</title>
<script type="text/javascript">
	function boardList(){
		location.href="mypage.do";
	}
</script>
</head>
<body>
<div id="tablebox">
<h1>게시글 작성하기</h1>
<form action="upload.do" method="post" enctype="multipart/form-data">
	<table class="table table-hover">
		<tr>
			<th>작성자</th>
			<td><input readonly="readonly" type="text" name="id" value="${ldto.id }" /></td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td>
				<input readonly="readonly" type="radio" name="category" value="여행장소" />여행장소
				<input readonly="readonly" type="radio" name="category" value="숙박시설" />숙박시설
				<input readonly="readonly" type="radio" name="category" value="맛집" />맛집
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<input type="file" name="filename" />
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" class="btn btn-success" value="등록" />
				<input type="button" class="btn btn-info" value="목록" onclick="boardList()" />
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>





