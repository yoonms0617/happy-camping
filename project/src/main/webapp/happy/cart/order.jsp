<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<link rel="stylesheet" href="/assets/slick/css/slick.css">
<link rel="stylesheet" href="/assets/slick/css/slick-theme.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-{SDK-최신버전}.js"></script>
<script type="text/javascript">
$(function(){
	$('.orderBtn').click(function(){
		requestPay();
	});
	const IMP = window.IMP; // 생략 가능
	IMP.init("imp73704104"); // 예: imp00000000
	function requestPay() {
	    // IMP.request_pay(param, callback) 결제창 호출
		IMP.request_pay({
		    pg : 'kakaopay', // version 1.1.0부터 지원.
		        /*
		            'kakao':카카오페이,
		            'inicis':이니시스, 'html5_inicis':이니시스(웹표준결제),
		            'nice':나이스,
		            'jtnet':jtnet,
		            'uplus':LG유플러스
		        */
		    pay_method : 'card', // 'card' : 신용카드 | 'trans' : 실시간계좌이체 | 'vbank' : 가상계좌 | 'phone' : 휴대폰소액결제
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : $('#item_name').text(),
		    amount : $('#quantity').text(),
		    buyer_email : $('#email').val(),
		    buyer_name : $('#name').val(),
		    buyer_tel : $('#tel').val(),
		    buyer_addr : $('#homeAddr').val()+' '+$('#detailAddr').val(),
		    buyer_postcode : $('#postcode').val(),
		    app_scheme : 'iamporttest' //in app browser결제에서만 사용 
		},
		function(rsp) {
			if(rsp.success){
				alert("아무리 테스트여도 결제 처리는 일단 다음에...")	
			}
			else{
				alert("주문이 완료되었습니다");
			    let name = $('#item_name').text();
			    let image = $('#image').val();
			    let price = $('#price').val();
			    let quantity = $('#quantity').val();
			    let total_price = $('#total_price').val();
			    $.ajax({
		            type: 'post',
		            url: '/cart/order_detail.do',
		            data: {
		                "name": name,
		                "image" : image,
		                "price" : price,
		                "quantity" : quantity,
		                "total_price" : total_price
		            },
		            success: function () {
		            	location.href = "/cart/order_detail.do?ono=${vo.ono}";
		            }
		        })
			}
		});
	}
})
</script>
</head>
<body>
<jsp:include page="/happy/fragments/head.jsp"></jsp:include>
<jsp:include page="/happy/fragments/header.jsp"></jsp:include>
<div style="height: 10px"></div>
<div class="wrapper row3">
  <main class="container clear"> 
    <h2 class="sectiontitle">주문서 작성</h2>
    <div style="height: 5px"></div>
    <div class="wrapper row3">
      <div> 
        <ul>
          <li>주문 내역</li>
	    </ul>
	  </div>
	</div>
	<table class="table table-hover" width=100%>
	  <thead align="center">
	    <tr>
	      <th colspan=2 width=55%>상품명</th>
	      <th width=15%>판매가</th>
	      <th width=15%>수량</th>
	      <th width=15%>합계</th>
	    </tr>
	  </thead>
	  <tbody>
	   <form method="post" action="/cart/order_detail.do">
	    <tr>
	      <input type="hidden" id="price" value="${item.price}">
	      <input type="hidden" id="total_price" value="${item.price * item.quantity}">
	      <td width=10% id="image">	<!-- 이미지 -->
	        <img src="${item.image}" style="width:40px;height: 40px">
	      </td>
	      <td width=45% id="item_name">	<!-- 상품명 -->
	        ${item.name}
	      </td>
	      <td width=15%>	<!-- 상품 가격 -->
	        <fmt:formatNumber value="${item.price}" pattern="#,###"/>원
	      </td>
	      <td width=15% id="quantity">	<!-- 상품 수량 -->
	        ${item.quantity}
	      </td>
	      <td width=15%>	<!-- 상품 수량 * 가격 -->
	        <fmt:formatNumber value="${item.price * item.quantity}" pattern="#,###"/>원
	      </td>
	    </tr>
	   </form>
	  </tbody>
	</table>
	<div style="height: 150px"></div>
	
	<div class="wrapper row3">
      <div> 
        <ul>
          <li>주문 정보</li>
	    </ul>
	  </div>
	</div>
	<table class="table">
	  <tr>
	    <th>주문자 정보</th>
	    <td>
	      <input type="text" class="form-control" id="name" name="name" value="${sessionScope.name }">
	    </td>
	  </tr>
	  <tr>
        <th>주소</th>
        <td>
          <input style="width:100px" type="text" class="form-control" id="postcode" name="postcode" placeholder="우편번호" value="${sessionScope.postcode }">
          <input type="button" class="btn btn-lg btn-outline-primary inline" onclick="daumAddressAPI()"
                   value="우편번호 찾기"><br>
          <input type="text" class="form-control" id="homeAddr" name="homeAddr" placeholder="주소" value="${sessionScope.homeAddr }">
          <input type="text" class="form-control" id="detailAddr" name="detailAddr" placeholder="상세주소" value="${sessionScope.detailAddr }">
        </td>
      </tr>
      <tr>
          <th>휴대전화</th>
          <td>
            <input type="text" class="form-control" id="tel" name="tel" maxlength="13" oninput="autoHyphen(this)" value="${sessionScope.tel }">
          </td>
        </tr>
	  <tr>
        <th>이메일</th>
        <td>
          <input type="text" class="form-control" id="email" name="email" value="${sessionScope.email }">
        </td>
       </tr>
	</table>

	<div style="height: 70px"></div>

	<!-- 결제 버튼 -->
	<div style="margin-left: 1180px;">
	  <button type="button" class="btn btn-xs btn-outline-secondary orderBtn" style="height:40px; width:120px;">
	    결제하기
	  </button>
	</div>
	<div style="height: 100px"></div>
  </main>
</div>
<jsp:include page="/happy/fragments/footer.jsp"></jsp:include>
<jsp:include page="/happy/fragments/common-script.jsp"></jsp:include>
<script rel="script" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>