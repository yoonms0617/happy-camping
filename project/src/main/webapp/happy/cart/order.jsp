<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<link rel="stylesheet" href="/assets/slick/css/slick.css">
<link rel="stylesheet" href="/assets/slick/css/slick-theme.css">
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
	    <tr>
	      <!-- 상품 구매 내역 -->
	      <td width=10%><!-- 이미지 --></td>
	      <td width=45%><!-- 상품명 --></td>
	      <td width=15%><!-- 판매가 --></td>
	      <td width=15%><!-- 수량 --></td>
	      <td width=15%><!-- 합계 --></td>
	    </tr>
	    <tr>
	      <!-- 합계 금액 -->
	    </tr>
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
	      <input type="text" class="form-control" id="oName" name="oName">
	    </td>
	  </tr>
	  <tr>
        <th>주소</th>
        <td>
          <input style="width:100px" type="text" class="form-control" id="oPostcode" name="oPostcode" placeholder="우편번호">
          <input type="button" class="btn btn-lg btn-outline-primary inline" onclick="daumAddressAPI()"
                   value="우편번호 찾기"><br>
          <input type="text" class="form-control" id="oHomeAddress" name="oHomeAddress" placeholder="주소">
          <input type="text" class="form-control" id="oDetailAddress" name="oDetailAddress" placeholder="상세주소">
        </td>
      </tr>
      <tr>
          <th>휴대전화</th>
          <td>
            <input type="text" class="form-control" id="oPhone" name="oPhone" maxlength="13" oninput="autoHyphen(this)">
          </td>
        </tr>
	  <tr>
        <th>이메일</th>
        <td>
          <input type="text" class="form-control" id="oEmail" name="oEmail">
        </td>
       </tr>
	</table>
	
	<div class="wrapper row3">
      <div> 
        <ul>
          <li>배송 정보</li>
	    </ul>
	  </div>
	</div>
	<!-- 라디오버튼 추가 : 주문자와 동일한 정보 여부 체크할 수 있도록 -->
	<form id="order-form" method="post"><!-- action="/order.do" -->
	  <table class="table">
        <tr>
          <th>받으시는 분<th>
          <td>
            <input type="text" class="form-control" id="name" name="name">
          <small>
             <span id="nameMsg"></span>
          </small>
          </td>
        </tr>
        <tr>
          <th>배송지 주소</th>
          <td>
            <input style="width:100px" type="text" class="form-control" id="postcode" name="postcode" placeholder="우편번호">
            <input type="button" class="btn btn-lg btn-outline-primary inline" onclick="daumAddressAPI()"
                   value="우편번호 찾기"><br>
            <input type="text" class="form-control" id="homeAddress" name="homeAddress" placeholder="주소">
            <input type="text" class="form-control" id="detailAddress" name="detailAddress" placeholder="상세주소">
            <small>
              <span id="addrMsg"></span>
            </small>
          </td>
        </tr>
		<tr>
          <th>일반전화</th>
          <td>
            <input type="text" class="form-control" id="phone1" name="phone1" maxlength="13" oninput="autoHyphen(this)">
          </td>
        </tr>
		<tr>
          <th>휴대전화</th>
          <td>
            <input type="text" class="form-control" id="phone" name="phone" maxlength="13" oninput="autoHyphen(this)">
            <small>
              <span id="phoneMsg"></span>
            </small>
          </td>
        </tr>
	  </table>
	</form>

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
	      <td> </td>
	      <td> </td>
	      <td colspan=3> </td>
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