<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="jquery-3.6.0.js"></script>
<style type="text/css">
.table, .tr, .td {border: 1px solid pink;}
.table {display: table;}
.tr {display: table-row;}
.td {display: table-cell;padding: 5px;}
#container {width: 400px;margin: 100px auto;}
@media screen and (max-width:800px) {
	.table, .tr, .td {display: block;}
	.td {padding: 5px;}
}
</style>
<script type="text/javascript">
	function idChk(){
		var id=document.getElementsByName("id")[0].value;//입력된 아이디 구함
		console.log(id);
		if(id==null||id==""||id==undefined){
			alert("반드시 아이디를 입력하세요");
			document.document.getElementsByName("id")[0].focus();
		}else{
// 			location.href="idchk.do?id="+id;
			open("idchk.do?id="+id,"중복체크","width=300px, height=300px,");	
		}
	}
	
	$(function(){
		$("input[name]").not("[name=id]").focus(function(){
			var idTitle=$("input[name=id]").attr("title");
			if(idTitle=="n"){
				alert("아이디 중복체크를 확인하세요.");
				document.getElementsByName("id")[0].focus();
			}
		});
		
		$("input[name=id]").focus(function(){
			$(this).attr("title","n");
		});
	});
</script>
</head>
<body>
	<div>회원가입하기</div>
	<form action="regist.do" method="post">
		<div class="table">
			<div class="tr">
				<div class="td">아이디</div>
				<div class="td">
					<input type="text" name="id" title="n" autocomplete="off" required="required" />
					<input type="button" value="중복체크" onclick="idChk()" />
				</div>
			</div>
			<div class="tr">
				<div class="td">비밀번호</div>
				<div class="td">
					<input type="password" name="password" required="required" />
				</div>
			</div>
			<div class="tr">
				<div class="td">이름</div>
				<div class="td">
					<input type="text" name="name" required="required" />
				</div>
			</div>
			<div class="tr">
				<div class="td">주소</div>
				<div class="td">
					<input type="text" name="address" required="required" />
				</div>
			</div>
			<div class="tr">
				<div class="td">전화번호</div>
				<div class="td">
					<input type="tel" name="phone" required="required" />
				</div>
			</div>
			<div class="tr">
				<div class="td">이메일</div>
				<div class="td">
					<input type="email" name="email" required="required" />
				</div>
			</div>
			<div class="tr">
				<div class="td">
					<input type="submit" value="가입" /> 
					<input type="button" value="취소"	onclick="location.href='index.jsp'" />
				</div>
			</div>
		</div>
	</form>
</body>
</html>


