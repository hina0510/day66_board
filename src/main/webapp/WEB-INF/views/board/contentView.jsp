<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#model_wrap{display:none; position: fixed; z-index: 9; margin: auto;
	top: 0; left: 0; right: 0; width: 100%; height: 100%;
	background-color: rgba(0,0,0,0.4); }
	#first{display:none; position: fixed; z-index: 10; margin: auto;
	top: 30px; left: 0; right: 0; width: 350px; height: 450px;
	background-color: rgba(200,100,100,0.9); }
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function slideClick() {
		$("#first").slideDown("slow");
		$("#model_wrap").show();
	}
	function slide_hide() {
		$("#first").hide();
		$("#model_wrap").hide();
	}
	function rep(){
	    let form = {};
	    let arr = $("#frm").serializeArray();
	    arr.forEach(data=>{
	    	form[data.name] = data.value;
	    });
	    console.log( form )
	    $.ajax({
	    	url : "addReply", type : "post", 
	        data : JSON.stringify( form ), 
	        contentType : "application/json; charset=utf-8",
	        success : () => {
	        	alert("답글이 달렸습니다!!!!");
	        	slide_hide();
	        	replyData();
	        },
	        error : () => {
	           alert("문제 발생!!!");
	        }
	   });
	}
	function replyData() {
		$.ajax({
			url :"replyData/"+${dto.writeNo},
			type: "get", dataType :"json",
			success : (data)=>{
				console.log(data);
				let txt = "";
				data.forEach((d)=>{
					txt +="아이디 : "+d.id+"<br>";
					
					var date = new Date(d.write_date);
					
					txt +="작성일 : "+date.toLocaleString()+"<br>";
					txt +="제목 : "+d.title+"<br>";
					txt +="내용 : "+d.content+"<hr>";
				})
				$("#reply").html(txt);
			}, error : ()=>{alert("문제 발생");}
		})
	}
	window.onload =()=>{replyData()}
</script>
</head>
<body>
	<%@ include file="../default/header.jsp" %>
	<table border="1">
		<tr>
			<th>글 번호</th> <td>${dto.writeNo }</td>
			<th>작성자</th> <td>${dto.id }</td>
		</tr>
		<tr>
			<th>제목</th> <td>${dto.title }</td>
			<th>등록일자</th> <td>${dto.saveDate }</td>
		</tr>
		<tr>
			<th>내용</th> <td>${dto.content }</td>
			<td colspan="2">
				<a href="download?name=${dto.image_file_name }"> <%-- 다운로드 받기 --%>
					<img src="download?name=${dto.image_file_name }" width="100" height="100" alt="없음">
					<%-- 사진 경로 --%>
				</a>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<c:if test="${login==dto.id }">
				<button type="button" onclick="location.href='modify_form?writeNo=${dto.writeNo}'">수정</button>
				<button type="button" onclick="location.href='delete?writeNo=${dto.writeNo}&fileName=${dto.image_file_name }'">삭제</button>
			</c:if>
				<button type="button" onclick="slideClick()">답글</button>
				<button type="button" onclick="location.href='boardAllList'">목록</button>
			</td>
		</tr>
		<tr>
			<td id="reply" colspan="4">
				답글이 없습니다
			</td>
		</tr>
	</table>
	
	
	<div id="model_wrap">
		<div id="first">
			<div style="width: 250px; margin: auto; padding-top: 20px">
				<form id="frm">
					<b>글번호</b>
					<input type="text" name="write_group" value="${dto.writeNo }" readonly><br>
					<b>작성자</b>
					${login }<br>
					<b>제목</b>
					<input type="text" name="title" size="30">
					<b>내용</b>
					<textarea name="content"></textarea>
					<hr>
					<button type="button" onclick="rep()">답글</button>
					<button type="button" onclick="slide_hide()">취소</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>