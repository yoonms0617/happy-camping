<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
 <script  type="text/javascript" >
let i = 0; // insert(댓글)
let u = 0; // 업데이트
let r = 0; // 대댓글
$(document).ready(function() {
    $('textarea').on('keyup keypress', function() {
        $(this).height(0);
        $(this).height(this.scrollHeight);
    });
});

 $(function(){

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
		});
			
			$('#del_btn').on('click',function(){
				let pwd = $('#del_pwd').val();
				if(pwd.trim()===""){
					$('#del_pwd').focus()
					return;
				}
				let qano = $('#del').attr("data-no");
				let ino=$('#d_ino').attr("value")
				$.ajax({
					type : 'post',
					url : '/item/item_qna_delete.do',
					data : {"qano":qano, "pwd":pwd, "ino":ino},
					success : function(result){
						alert("result:"+result)
						let res = result.trim();
						let ress=res.split(",")
						if(ress[0] === 'yes'){
							$.ajax({
								type:'post',
								url:'item_qna_list.do',
								data:{"ino":ress[1]},
								success:function(response)
								{
									$('#print').html(response)
								}
							})
						}else{
							alert("비밀번호가 틀립니다.\n다시 입력해주세요")
							$('#del_pwd').val("");
							$('#del_pwd').focus();
						}
					}
				})
				
			});
			
	})  
</script>
<title>Insert title here</title>
</head>
<body>

    <h2 class="sectiontitle">내용보기</h2>
	<div style="height: 5px"></div>

	     <table class="table">
		<tr>
		  <th width="20%" class="text-center" style="background-color:  #fefbd8">번호</th>
		  <td width="30%" class="text-center">${vo.qano }</td>
		  <th width="20%" class="text-center" style="background-color:  #fefbd8">작성일</th>
		  <td width="30%" class="text-center">${vo.regdate }</td>
		</tr>
		
		<tr>
		  <th width="20%" class="text-center" style="background-color:  #fefbd8">이름</th>
		  <td width="30%" class="text-center">${vo.mid }</td>
		  <th width="20%" class="text-center" style="background-color:  #fefbd8">조회수</th>
		  <td width="30%" class="text-center">${vo.hit }</td>
		</tr>
		
		
		<tr>
		  <th width="20%" class="text-center" style="background-color:  #fefbd8">제목 </th>
		  <td colspan="3">${vo.title }</td>
		  <input type= hidden name=ino value=${vo.ino } id="d_ino">
		</tr>
		
		
		<tr>
		  <td class="text-left" valign="top" colspan="4" height="300"><pre style="white-space:pre-wrap; background-color: white; border:none">${vo.content }</pre></td>
		</tr>
		<tr>
			<td class="text-left" colspan="4" style="text-align:right;">
			  <input type=button value="수정" class="btn btn-sm btn-primary ups" onclick = "qnaUpdate(${vo.qano } )">
			  <input type=button value="목록" class="btn btn-sm btn-success" onclick = "qnaList(${vo.qano})">
			  <span class="btn btn-sm btn-danger" id="delete"  >삭제</span>
			</td>
		</tr>
		<tr id = "del" style= "display:none" data-no="${vo.qano }">
			<td colspan="4" class="text-right">
			<form id="del_frm" class="inline">
				비밀번호:<input type=password class="input-sm" size=15 id="del_pwd">
				<input type=button value="삭제" class="btn btn-sm btn-primary" id="del_btn" ><!-- onclick = "qnaDel(${vo.qano})" -->
			</form>
				
			</td>
		</tr>
	</table>
	<div style="heght: 10px"></div>
	
	

	<!-- ------------------댓글 목록---------------------- -->
	<table class="table">
	 <tr>
	   <td>
	    <c:forEach var="vo" items="${list }">
	   <table class="table">
		<tr>
			<td>☘<span style="color:#198754">${vo.mid }</span>&nbsp;(${vo.dbday })</td>
			
		</tr>
		<tr>
			<td colspan ="2">
				<pre style = "white-space: pre-wrap-; background-color: white; border: none">${vo.content }</pre>
			</td>
		</tr>
	</table>
	</c:forEach>
	   </td>
	 </tr>
	</table>
	
		<!-- ------------------댓글창---------------------- -->
	<table class="table">
			<tr>
				<td>
					<form method = "post" id="qnaReins">
						<input type=hidden name= "qano" value="${vo.qano }">
						<input type= hidden name=ino value=${vo.ino } >
						<input type=hidden name= "mid" value="${vo.mid }">
						<textarea rows="3" cols="120" style="float: left" name="content"></textarea> &nbsp;
						<input type=button value="답변하기" class="btn btn-sm btn-danger" style="height: 60px" onclick="qnaReinsert(${vo.qano})">
					</form>
				</td>
			</tr>
	</table>
	
	
	
	<!--------------------------------------- QnA 댓글 --------------------------------------------->
	<!--------------------------------------------------------------------------------------------->
	
	
</body>
</html>