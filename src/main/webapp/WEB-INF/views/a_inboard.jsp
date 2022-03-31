<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
	function boardList(){
		location.href="anoticeboard.do";
	}
</script>
</head>
<body>
<div id="tablebox">
<h1>관리자 공지작성하기</h1>
<form action="insertboard.do" method="post">
	<table  class="table table-hover">
		<tr>
			<th>작성자</th>
			<td><input type="text" name="id" value="${ldto.id }" readonly="readonly" /></td>
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
				<input class="btn btn-success" value="등록" />
				<input class="btn btn-info" type="button" value="목록" onclick="boardList()" />
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>




