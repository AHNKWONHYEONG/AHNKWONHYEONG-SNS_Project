<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 보내기</title>
<script type="text/javascript">
	function boardList(s_id){
		location.href="usmessage.do?s_id="+s_id;
	}
</script>
</head>
<body>
<div id="tablebox">
<h1>메시지 보내기</h1>
<form action="insertmessage.do" method="post">
	<table class="table table-hover">
		<tr>
			<th>작성자</th>
			<td><input readonly="readonly" type="text" name="s_id" value="${ldto.id }" /></td>
		</tr>
		<tr>
			<th>받는 분</th>
			<td><input type="text" name="id" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" class="btn btn-success" value="등록" />
				<input type="button" class="btn btn-info" value="목록" onclick="boardList('${ldto.id }')" />
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>





