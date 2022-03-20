<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글추가</title>
<style type="text/css">
	#tablebox{width: 500px;margin: 0 auto;}
</style>
</head>
<body>
<div id="tablebox">
	<h1>유저 Q&A</h1>
	<form action="qainsertboard.do" method="post">
<!-- 	<input type="hidden" name="command" value="insertboard"/> -->
		<table class="table" class="table table-hover">
			<tr>
				<th>작성자</th>
				<td><input readonly="readonly" required="required" type="text" name="id" class="form-control" value="${ldto.id }" /><td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input required="required" type="text" name="title" class="form-control"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea required="required" rows="10" cols="60" name="content" class="form-control"></textarea></td>
			</tr>
			<tr>
				<td colspan="1"><input type="submit" value="글추가" class="btn btn-primary"/></td>
				<td colspan="1"><input type="button" onclick="boardList()" value="목록" class="btn btn-info"/></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
function boardList(){
		location.href="uqaboard.do";
	}
</script>
</body>
</html> 