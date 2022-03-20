<%@include file="header.jsp" %>
<%@page import="com.hk.sns.dtos.FriendDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.sns.utils.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찬구목록</title>
<script type="text/javascript">
	//전체 체크박스 기능
	function allSel(bool){//bool은 체크여부를 받는다.
		var chks=document.getElementsByName("chk");//chks[chk,chk,....]
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=bool;//각각의 체크박스에 체크여부 적용
		}
	}
	
	function add(){
		$("#friendadd").show();
		var replyPosition=$("#friendadd").offset().top;
		$("#tablebox").animate({
			"scrollTop":replyPosition
		},1000);
	}
	
	function mypage() {
		location.href="mypage.do";
	}
	
	$(function(){
		$("#friendchk").click(function(){
			var id=$("#idchk").val();
			
// 			console.log(id);
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
// 						console.log(obj);
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
	
	function frienddel() {
		location.href="mypage.do?id=";
	}
	
</script>
<style type="text/css">
	#friendadd{	display: none;}
</style>
</head>
<%
	//requst객체에 저장되는 객체들은 모두 object로 형변환되기 떄문에
	//다시 downcasting해야한다.
	List<FriendDto> list=(List<FriendDto>)request.getAttribute("list");
// 이미 저장할 데이터의 타입을 알고 있는 경우
// 	int[] i={1,2,3,4};
// 	List<HkDto> list2;
	if(list==null){
		request.setAttribute("msg", "요청글에 대한 정보가 없습니다.");
		pageContext.forward("error.jsp");
	}
%>
<body>
<h1>친구 목록</h1>
<table border="1">
	<col width="30px">
	<col width="50px">
	<col width="100px">
	<col width="300px">
	<col width="200px">
	<tr>
		<th><input type="checkbox" name="all" onclick="allSel(this.checked)" /></th>
		<th>번호</th><th>아이디</th><th>이름</th><th>친구등록일</th>
<!-- 		<th>삭제여부</th> -->
	</tr>
	<%
		for(int i=0; i<list.size(); i++){
			FriendDto dto=list.get(i);//list[dto,dto,dto....]->순차적으로 하나씩 꺼냄
			%>
			<tr>
				<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>"/></td>
				<td><%=dto.getSeq()%></td>
				<td><%=dto.getId()%></td>
				<td><%=dto.getName()%></td>
				<td><%=dto.getFridate()%></td>
<%-- 				<td><%=dto.getReport() %></td> --%>
<%-- 				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.regdate}"/></td> --%>
			</tr>				
			<%
		}
	%>
	<tr>
		<td colspan="5">
			<input type="button" onclick="mypage()" value="메인" />
			<input type="button" onclick="add()" value="친구추가" />
			<input type="button" onclick="frienddel()" value="친구삭제" />
		</td>
	</tr>
</table>
<hr/>
	<div id="friendadd">
	<h1>친구추가</h1>
		<table class="table" class="table table-hover">
			<tr>
				<th>아이디 확인</th>
				<td>
					<input id="idchk" type="text" />
					<input type="button" id="friendchk" value="아이디확인" />
					<b id="chk"></b>
				</td>
			</tr>
		</table>
	</div>
</body>
</html> 