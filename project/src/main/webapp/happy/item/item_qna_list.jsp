<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
	  <tr>
	   <th width="10%" class="text-center" style="background-color:  #fefbd8">번호</th>
	   <th width="45%" class="text-center" style="background-color:  #fefbd8">제목</th>
	   <th width="15%" class="text-center" style="background-color:  #fefbd8">작성자</th>
	   <th width="20%" class="text-center" style="background-color:  #fefbd8">작성일</th>
	   <th width="10%" class="text-center" style="background-color:  #fefbd8">조회수</th>
	  </tr>
	  
	  <c:forEach var="vo" items="${qalist }"> <%--request.setAttribute("list",list) => request.getAttribute("list" ${list} --%>
	  	<tr>
	  		<td width=10% class="text-center">${vo.qano }</td> <%--vo.no = vo.getNO() : get메소드 호출, 변수가 아니다--%>
	  		<td width=45%>
	  		<span type="button" onclick="qnaDetail(${vo.qano })">${vo.title }</span> &nbsp;
	  		  <c:if test="${vo.dbday==today }">
	  		    <sup><img src="/assets/project/item/img/new.gif"></sup>
	  		  </c:if>
	  		</td>
	  		<td width=15% class="text-center">${vo.mid }</td>
	  		<td width=20% class="text-center">${vo.dbday }</td>
	  		<td width=10% class="text-center">${vo.hit}</td>
	  	</tr>
	  </c:forEach>	
	</table>	
	<table class="table">
	  <tr>
	    <td class="text-center">
	    	<a href="/item_qna_list.do?ino=${ino }&page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-primary" style="color: white">이전</a>
	    		${curpage } page / ${totalpage } pages
	    	<a href="/item_qna_list.do?ino=${ino }&page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-primary" style="color: white">다음</a>
	    </td>
	  </tr>
	</table>
</body>
</html>