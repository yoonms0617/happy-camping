<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<link rel="stylesheet" href="/assets/slick/css/slick.css">
<link rel="stylesheet" href="/assets/slick/css/slick-theme.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<!-- <script type="text/javascript">
$(function(){
	$('#QuantityDown').click(function(){
		let stat = $('#quantity').text();
		let num = parseInt(stat,10);
		num--;
		if(num<=0){
		alert('최소 수량입니다.');
		num =1;
		}
		$('.quantity').text(num);
	});
	$('#QuantityUp').click(function(){
		let stat = $('#quantity').text();
		let num = parseInt(stat,10);
		num++;
	
		if(num>10){
		alert('최대 수량입니다.');
		num=5;
		}
		$('.quantity').text(num);
	});
});
</script> -->
</head>
<body>
<jsp:include page="/happy/fragments/head.jsp"></jsp:include>
<jsp:include page="/happy/fragments/header.jsp"></jsp:include>
<div style="height: 10px"></div>
<div class="wrapper row3">
  <main class="container clear"> 
    <h2 class="sectiontitle">장바구니</h2>
    <div style="height: 5px"></div>
    <div class="wrapper row3">
      <div> 
        <ul>
          <li>상품 목록</li>
	    </ul>
	  </div>
	</div>
	<table class="table table-hover" width=100%>
	  <thead align="center">
	    <tr>
	      <th>구분</th>
	      <th colspan=2>상품명</th>
	      <th colspan=2>수량</th>
	      <th>상품구매금액</th>
	      <th>할인금액</th>
	      <th>배송비</th>
	      <th>주문</th>
	    </tr>
	  </thead>
	  <tbody>
	    <c:choose>
	      <c:when test="${list==null} ">
	        <tr>
	          <td colspan=8>
	            <strong>장바구니에 담긴 상품이 없습니다</strong>
	          </td>
	        </tr>
	      </c:when>
	      <c:otherwise>
	      <c:forEach var="vo" items="${list}">
	        <tr height=70px>
	          <!-- 장바구니에 담긴 상품 -->
	          <td><!-- checkbox --></td>
<%-- margin 조절 해보기! --%>	      	  <td style="margin-top:15px">
	      	    <img src="${vo.image}" style="width:30px;height: 30px">
	      	  </td>
	      	  <td>${vo.name}</td>
	      	  <td class="text-center"  style="margin-top:15px">
	      	    <input id="quantity" name="quantity_opt" style="width:50px" value="${vo.quantity }" type="text" readonly>
<%-- 	      	    ${vo.quantity} --%>
	      	  </td>
	      	  <td class="text-center">
	      	    <div>
	      	      <a href="cart_update_up.do?cno=${vo.cno}" style="display:block;">
	      	        <img src="https://img.echosting.cafe24.com/skin/base_ko_KR/product/btn_count_up.gif" alt="수량증가" class="QuantityUp up">
	      	      </a>
	      	      <a href="cart_update_down.do?cno=${vo.cno}" style="display:block;">
	      	        <img src="https://img.echosting.cafe24.com/skin/base_ko_KR/product/btn_count_down.gif" alt="수량감소" class="QuantityDown down">
	      	      </a>
	      	    </div>
	      	  </td>
	      	  <td class="text-center">${vo.price!=0 ? vo.price : "-"}</td>
	      	  <td class="text-center">${vo.ivo.sale}</td>
	      	  <td class="text-center">${(vo.price * vo.quantity) >= 50000 ? "무료" : 3000}</td>
	      	  <td class="text-center">
	      	    <button class="btn btn-xs btn-outline-secondary orderBtn" style="height:30px; width:60px;">구매</button>
	            <button class="btn btn-xs btn-outline-secondary" style="height:30px; width:60px;" onClick="location.href='/cart/cart_cancel.do?cno=${vo.cno}'">삭제</button>
	          </td>
	        </tr>
	      </c:forEach>
	      </c:otherwise>
	    </c:choose>
	    <%-- <tr style="text-align:right">
	      <!-- 합계 금액 -->
	      <c:forEach var="i" begin="1" end="${list.size()}">
	        <c:choose>
	          <c:when test="${vo.price * vo.quantity >= 50000}">
				<
	          </c:when>
	          <c:otherwise>
	          
	          </c:otherwise>
	        </c:choose>
	      </c:forEach>
	    </tr> --%>
	  </tbody>
	</table>
	<div style="height: 150px"></div>
	<table class="" width=100%>
	  <tbody>
	    <tr align="center">
	      <td>총 상품 금액</td>
	      <td>총 배송비</td>
	      <td colspan=3>결제 예정 금액</td>
	    </tr>
	    <tr>
	    <!-- 결제 금액 -->
	      <td>
	        <fmt:formatNumber value="${total_price}" pattern="#,###"/>원
	      </td>
	      <td>${total_price>=50000?"무료":"3,000원"}</td>
	      <td colspan=3>${total_price>=50000?total_price:total_price+3000}</td>
	    </tr>
	      <div style="height: 150px"></div>
	    <tr>
		  <div>
            <button type="button" style="height:50px;width:160px;" class="btn btn-sm btn-dark" onclick="location.href='/cart/order.do'">구매하기</button>
          </div>
          <div>
            <button type="button" style="height:50px;width:160px;" class="btn btn-sm btn-outline-secondary" onClick="location.href='/main.do'">쇼핑 계속하기</button>
          </div>
	    </tr>
	  </tbody>
	</table>
	<div style="height: 100px"></div>
  </main>
</div>
<jsp:include page="/happy/fragments/footer.jsp"></jsp:include>
<jsp:include page="/happy/fragments/common-script.jsp"></jsp:include>
</body>
</html>