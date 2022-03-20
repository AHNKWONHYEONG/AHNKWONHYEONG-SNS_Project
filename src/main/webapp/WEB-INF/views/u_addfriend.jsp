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
function mypage() {
	location.href="mypage.do";
}

$(function(){
	$("#friendchk").click(function(){
		var id=$("#idchk").val();
		
//			console.log(id);
		$.ajax({
			url:"addfriend.do",
			method:"get",
			data:{"id":id},
			dataType:"text",
			async:false,
			success:function(obj){
				console.log(obj);
				if(obj==""){
					$("#chk").text('아이디가 없습니다.');
//						console.log(obj);
				}else{
					$("#chk").text('아이디가 존재합니다.');
					if(confirm("친구를 추가 하십니까??")){
						location.href="addfriend2.do?id="+obj+"&my_id="+${ldto.id};
					}else{
						alert("취소 합니다.");
					}
				}
			},error:function(){
				console.log("실패");
			}
		});
	});
});
</script>
</head>
<%
		String id=(String)request.getAttribute("id");
%>
<body>
	<div id="friendadd">
	<h1>친구추가</h1>
		<table class="table" class="table table-hover">
			<tr>
				<th>아이디 확인</th>
				<td>
					<input id="idchk" type="text" value="<%=id %>" />
					<input type="button" id="friendchk" value="아이디확인" />
					<b id="chk"></b>
				</td>
			</tr>
			<tr>
				<td><input type="button" onclick="mypage()" value="돌아가기" /></td>
			</tr>
		</table>
	</div>
</body>
</html>