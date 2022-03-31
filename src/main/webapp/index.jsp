<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
	.table, .tr, .td{border: 1px solid pink;}
	.table{	display: table;	}
	.tr{display: table-row;}
	.td{display: table-cell; padding: 5px;}
	#container{	width: 400px; margin:100px auto;}
</style>
<script type="text/javascript">
	//회원가입 폼으로 이동하기
	function registForm(){
		location.href="registform.do";
	}
</script>
</head>
<body>
<div id="container">
	<h1>회원 로그인</h1>
	<form action="afterlogin.do" method="post">
		<div class="table">
			<div class="tr">
				<div class="td">아이디</div>
				<div class="td"><input type="text" name="id" value="123"/></div>
			</div>
			<div class="tr">
				<div class="td">비밀번호</div>
				<div class="td"><input type="password" name="password" value="123"/></div>
			</div>
			<div class="tr">
				<div class="td">
					<input type="submit" value="로그인"/>
					<input type="button" value="회원가입" onclick="registForm()"/>
					<input type="button" value="비회원" onclick="nonmember()"/>
				</div>
			</div>
		</div>
	</form>
</div>
</body>
</html> 