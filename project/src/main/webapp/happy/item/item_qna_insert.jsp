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
    <h2 class="sectiontitle">문의</h2>
	<div style="height: 5px"></div>
	<form id="qnaFrm">
	<table class="table">
<!-- 	  <tr>
	    <th width=15% class="text-right">제목</th>
	    <td width=80%>
	    	<input type=text name=name size=20 class="input-sm">
	    </td>
	  </tr> -->
	  
	  
	  <tr>
	    <th width=15% class="text-right">제목</th>
	    <td width=80%>
	    	<input type=text name=title size=60 class="input-sm">
	    	<input type= hidden name=ino value=${ino }>
	    </td>
	  </tr>
	  
	  <tr>
	    <th width=15% class="text-right">내용</th>
	    <td width=80%>
	    	<textarea rows="10" cols="60" name="content"></textarea>
	    </td>
	  </tr>
	  
	   <tr>
	    <th width=15% class="text-right">비밀번호</th>
	    <td width=80%>
	    	<input type=password name=pwd size=15 class="input-sm">
	    </td>
	  </tr>
	  
	  <tr>
	    <td colspan="2" class="text-center">
	      <input type=button value="글쓰기" class="btn btn-sm btn-danger" onclick="qnaInsertOk()">
	      <input type=button value="취소" class="btn btn-sm btn-danger" onclick = "qnaList(${ino})">
	    </td>
	  </tr>
	
	</table>
	
  </main>
 </div>
</body>
</html>