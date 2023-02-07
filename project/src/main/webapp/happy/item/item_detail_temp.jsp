<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="/happy/fragments/head.jsp" %>
<%@include file="/happy/fragments/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
console.log("아이템디테일들어옴")
</script>
</head>
<body>
	<div class="wrapper row3">
		<main class="container clear">
			<h5 class="sectiontitle">상품 상세보기</h5><!-- 상품카테고리 넣고싶어  -->
			<div style="height: 5px"></div>
			<table class="">
				<tr>
					<td width=30% class="text-center" rowspan="7">
						<img src="${vo.image }" style="width:100%">
					</td>
					<td colspan="2">
						<h3>${vo.name }</h3>
					</td>
				</tr>

				
				
				
<!-- 				<tr> -->
<!-- 					<td colspan="2"> -->
<!-- 					    &nbsp; -->
<%-- 					     <h3> <span style="color:#AA0000">${vo.sale }</span> </h3> --%>
<!-- 					    <tr> -->
<!-- 					    	<th><h6>판매가</h6></th> -->
<!-- 					    </tr> -->
<!-- 					    <tr> -->
<%-- 					    	<td><h5>${vo.price }원</h5></td> --%>
<!-- 					    </tr> -->
					 
<!-- 					</td> -->
<!-- 				</tr> -->
				
				<c:choose>
				  <c:when test="${vo.sale==0}">
				    <tr>
					  <td width=20%>판매가 : </td>
					   <td><h5><fmt:formatNumber value="${vo.price}" pattern="#,###"  />원</h5></td>
					</tr>
				  </c:when>
				  <c:otherwise>
				  	<tr>
			      	  <td colspan="2">
			    		<h3><span style="color:magenta">${vo.sale }%</span>&nbsp;${vo.price }원</h3>
			      	  </td>
			   		</tr>
				  </c:otherwise>
				</c:choose>
				
				
				<tr>
					<td width=10%>적립금 : </td>
					<td width=90%><fmt:formatNumber value="${vo.price * 0.05}" pattern="#,###"  />원 (5%) </td>
				</tr>
				<tr>
					<td width=20%>브랜드 : </td>
					<td width=80%>${vo.brand }</td>
				</tr>
				<tr>
					<td width=20%>배송비 : </td>
					<td width=80%>
						<select>
						<option selected>주문시 결제(선결제)</option>
						<option>수령시 결제(착불)</option>
						</select><br>
						<strong>3,000원</strong> &nbsp; (50,000원 이상 구매 시 무료)
					</td>
				</tr>
				<tr>
			    	<td colspan="3" class="text-right">
			    		<input type=button value="장바구니" class="btn btn-sm btn-success">
			    		<input type=button value="구매하기" class="btn btn-sm btn-danger">
			    		<input type=button value="찜하기" class="btn btn-xs btn-info">
			    	</td>
    			</tr>	
			</table>
		</main>
	</div>
	
	
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>


</body>
</html>