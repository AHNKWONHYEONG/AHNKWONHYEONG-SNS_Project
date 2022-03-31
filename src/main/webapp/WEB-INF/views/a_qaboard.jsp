<%@include file="header.jsp" %>
<%@page import="com.hk.sns.utils.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록보기</title><!-- 합쳐지고 최소화된 최신 CSS -->
<script type="text/javascript">
	function allSel(bool){
		var chks=document.getElementsByName("chk");
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=bool;
		}
	}
	
	function isChecked(){
		var count=0;
		var chks=document.getElementsByName("chk");
		for (var i = 0; i < chks.length; i++) {
			if(chks[i].checked){
				count++;
			}
		}
		
		if(count==0){
			alert("최소 하나이상 체크해야 됩니다.");
			return false;
		}else{
			return true;
		}
	}
	
	function adminMain(){
		location.href="a_main.do";
	}
	
	$(function(){
		//내용미리보기를 ajax로 구현
		//해당 제목에 마우스를 올리면 내용을 보여주자->필요한 값은 seq
		$("td>a").hover(function(){
			//			a		td		tr		tds			 td[0]
			var seq=$(this).parent().parent().children("td").eq(0).find("input").val();
// 			alert("마우스올린 seq;"+seq);
			$.ajax({
				url:"contentAjax.do",//요청 URL
				mrthod:"post",//전송방식
				data:{"seq":seq},//server로 보낼 값
// 				dataType:"text",//server로부터 응답받는 값(json, xml, html, text....)
				dataType:"json",//server로부터 응답받는 값(json, xml, html, text....)
				async:false,//javascript에서 ajax메서드의 실행을 비동기로 할지 말지 여부
				success:function(obj){//통신성공하면 기능 실행(obj변수는 전달된 데이터 받는다.)
					console.log("성공");
//------------------하나의 값(text타입의 값하나)을 받을 경우 처리 코드 예시
				//실행코드 작성
// 					$("#contentview").val(obj);//text()
// 					alert(obj);
//------------------여러값을 받아서 처리하는 경우(server로 부터 json객체를 받을 때 처리 코드 예시)
					//글 내용뿐아니라 추가 정보를 더 필요로 한다면 (ID, TITLE),CONTENT,REGDATE(date>json변환X)
					//kye:value -> 값이 여러개면 값을 구별할 수 없기 때문에 값마다 이름이 있어야 한다.
					//k:v의 형태로 구성되는 객체 -> JS: JSON, JAVA: MAP
					//처리해줄 내용: 서버에서 응답할대 중간에서 MAP을 JSON으로 변환시켜서 JS로 보내준다.
					
					//json으로 변환된 객체를 받아서 내부 내용을 확인
					//obj->{"dto":{id:..., title:...., content:....}}
// 					alert(obj["dto"]["id"]);
					$("#regdate").val(obj.dto.regdateStr);
					$("#id").val(obj.dto.id);
					$("#title").val(obj.dto.title);
					$("#contentview").val(obj.dto.content);//날짜가 문자열 타입이라 표현가능
					
				},
				error:function(){
// 					alert("서버통신실패");
					console.log("통신실패");
				}
			});
		},function(){
			//마우스 커서 나갔을때 textarea의 값 지우기
// 			$("textarea").val("");
			$("#id").val("");
			$("#title").val("");
			$("#contentview").val("");
			$("#regdate").val("");
		});
	})
	
	//게시판에서 refer, step.... 등의 값을 감추거나 보이게 하는 기능
	function attrShow(){
		$("th").slice(5,8).toggle().end().eq(9).toggle();
		$("tr").each(function(){
			$(this).children("td").slice(5,8).toggle().end().eq(9).toggle();			
		});
	}
</script>
<style type="text/css">
/* 	#tablebox{ */
/* 		width: 800px; */
/* 		margin: 0 auto; */
/* 	} */
	
	#ajaxform{
		width:600px;
		margin-left: 450px;		
	}
</style>
</head>
<body>
<%
// 	Utils utils=new Utils();
%>
<jsp:useBean id="util" class="com.hk.sns.utils.Util"></jsp:useBean>
<div id="tablebox">
<h1>관리자 Q&A 게시판</h1>
<div id="ajaxform">
	작성일:<input type="text" id="regdate" /><br/>
	작성자:<input type="text" id="id" /><br/>
	제목:<input type="text" id="title" /><br/>
	내용:<textarea rows="2" cols="40" id="contentview"></textarea>
</div>
<form action="muldel.do" method="post" onsubmit="return isChecked()">
<input type="button" value="속성보기" onclick="attrShow()">
<table class="table table-hover">
	<tr>
		<th><input type="checkbox" name="all" onclick="allSel(this.checked)"/></th>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성일</th>
		<th>refer</th>
		<th>step</th>
		<th>depth</th>
		<th>좋아요</th>
		<th>delflag</th>
	</tr>
	<c:set var="count" value="0" />
	<c:choose>
		<c:when test="${empty list}">
			<tr><td colspan="10">---작성된 글이 없습니다.---</td></tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td><input type="checkbox" name="chk" value="${dto.seq}"/></td>
					<td>${count=count+1}</td>
					<td>${dto.id}</td>
					<c:choose>
						<c:when test="${dto.delflag eq 'N'}">
							<td style="width:300px;">
								<jsp:setProperty property="arrow" name="util" value="${dto.dep }"/>
								<jsp:getProperty property="arrow" name="util"/>
								<a href="detailboard.do?seq=${dto.seq}">${dto.title}</a>
							</td>					
						</c:when>
						<c:otherwise>
							<td style="width:300px;">--삭제된 글입니다.--</td>
						</c:otherwise>
					</c:choose>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.regdate}"/></td>
					<td>${dto.refer}</td>
					<td>${dto.step}</td>
					<td>${dto.dep}</td>
					<td>${dto.readcount}</td>
					<td>${dto.delflag}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="10">
			<input class="btn btn-primary" type="submit" value="삭제">
			<input class="btn btn-success" type="button" value="메인" onclick="adminMain()" />
		</td>
	</tr>

</table>
</form>
</div>
</body>
</html> 