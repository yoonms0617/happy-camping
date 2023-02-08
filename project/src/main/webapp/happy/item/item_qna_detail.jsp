<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script>
let i = 0; // insert(댓글)
let u = 0; // 업데이트
let r = 0; // 대댓글

/* $(function(){
	$('#delete').click(function(){
		if(i === 0){
			$('#del').show();
			$('#delete').text("취소");
			i = 1;
		}else{
			$('#del').hide();
			$('#delete').text("삭제");
			i = 0;
		}
	}) */
</script>
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
    <h2 class="sectiontitle">내용보기</h2>
	<div style="height: 5px"></div>
	<table class="table">
		<tr>
		  <th width="20%" class="text-center">번호</th>
		  <td width="30%" class="text-center">${vo.qano }</td>
		  <th width="20%" class="text-center">작성일</th>
		  <td width="30%" class="text-center">${vo.regdate }</td>
		</tr>
		
		<tr>
		  <th width="20%" class="text-center">이름</th>
		  <td width="30%" class="text-center">${vo.mid }</td>
		  <th width="20%" class="text-center">조회수</th>
		  <td width="30%" class="text-center">${vo.hit }</td>
		</tr>
		
		<tr>
		  <th width="20%" class="text-center">제목</th>
		  <td colspan="3">${vo.title }</td>
		  <input type= hidden name=ino value=${ino }>
		</tr>
		
		<tr>
		  <td class="text-left" valign="top" colspan="4" height="300"><pre style="white-space:pre-wrap; background-color: white; border:none">${vo.content }</pre></td>
		</tr>
		<tr>
			<td class="text-right" colspan="4">
			  <a href="item/item_qna_update.do?no=${vo.qano}" class="btn btn-xs btn-success">수정</a>
			  <span class="btn btn-xs btn-warning" id="delete" >삭제</span>
			  <input type=button value="목록" class="btn btn-sm btn-success" onclick = "qnaList(${vo.qano})">
			  <input type=button value="삭제" class="btn btn-sm btn-danger" onclick = "qnaDel(${vo.qano})">
			  
			</td>
		</tr>
		<tr id = "del" style= "display:none" data-no="${vo.qano }">
			<td colspan="4" class="text-right">
			<form id="del_frm" class="inline">
				비밀번호:<input type=password class="input-sm" size=15 id="del_pwd">
				<input type=button value="삭제" class="btn btn-sm btn-primary" onclick = "qnaDel(${vo.qano})">
			</form>
				
			</td>
		</tr>
	</table>
	</main>
	</div>
</body>
</html>