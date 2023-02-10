<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper row3">
  <main class="container clear"> 
    <h2 class="sectiontitle">수정하기</h2>
	<div style="height: 5px"></div>
	<form id="qnaUpFrm">
	<table class="table">
	  <tr>
	    <th width=15% class="text-right">작성자</th>
	    <td width=80%>
	    	<input type=text name=mid size=20 class="input-sm" value="${qvo.mid }" id="mid">
	    	<input type=hidden name=qano value="${qvo.qano }" id="qano">
	    	<input type=hidden name=ino value="${qvo.ino }" id="ino">
	    </td>
	  </tr>
	  
	  <tr>
	    <th width=15% class="text-right">제목</th>
	    <td width=80%>
	    	<input type=text name=title size=60 class="input-sm" value="${qvo.title }" id="subject">
	    </td>
	  </tr>
	  
	  <tr>
	    <th width=15% class="text-right">내용</th>
	    <td width=80%>
	    	<textarea rows="10" cols="60" name="content" id="content">${qvo.content }</textarea>
	    </td>
	  </tr>
	  
	   <tr>
	    <th width=15% class="text-right">비밀번호</th>
	    <td width=80%>
	    	<input type=password name=password size=15 class="input-sm" id="password">
	    </td>
	  </tr>
	  
	  <tr>
	    <td colspan="2" class="text-center">
	      <input type=button value="수정" class="btn btn-sm btn-success" onclick = "qnaUpdateOk()">
	      <input type=button value="취소" class="btn btn-sm btn-danger" onclick = "qnaList(${qvo.qano})">
	    </td>
	  </tr>
	
	</table>
	</form>
  </main>
 </div>
</body>
</html>