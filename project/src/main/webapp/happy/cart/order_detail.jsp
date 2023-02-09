<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/happy/fragments/head.jsp" />
<style>
    .text-underline-hover {
        text-decoration: none;
    }

    .text-underline-hover:hover {
        text-decoration: underline;
    }
</style>
<body>
<%-- 
	 주문 기본키 : ${order.ono}
	 주문 코드 : ${order.code}
	 주문 상태 : ${order.status}
	 총 주문 가격 : ${order.price}
	 주문 날짜 : ${order.orderedAt}
	 상품 이미지 : <img src="${order.orderItemVO.image}">
	 상품 이름 : ${order.orderItemVO.name}
	 상품 가격 : ${order.orderItemVO.price}
	 상품 수량 : ${order.orderItemVO.quantity}
	 주문자 이름 : ${order.deliveryVO.name}
	 우편번호 : ${order.deliveryVO.postcode}
	 집주소 : ${order.deliveryVO.homeAddr}
	 상세주소 : ${order.deliveryVO.detailAddr}
	 --%>
<jsp:include page="/happy/fragments/header.jsp" />
<div class="container">
    <div class="row">
        <div class="col p-0" style="max-width: 200px;">
            <div class="border">
                <div class="p-5 text-center bg-light">
                    <a class="text-decoration-none text-dark" href="/mypage.do">
                        <h3 class="m-auto">MY 캠핑</h3>
                    </a>
                </div>
            </div>
            <div class="border-start border-end border-bottom p-0">
                <div class="p-3">
                    <div class="mx-3 mb-2">
                        <p class="fs-5 m-0">MY 쇼핑</p>
                        <div class="mt-3">
                            <a class="small text-underline-hover text-dark"
                               href="/mypage.do">주문 목록</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="border-start border-end border-bottom p-0">
                <div class="p-3">
                    <div class="mx-3 mb-2">
                        <p class="fs-5 m-0">MY 활동</p>
                        <div class="mt-3">
                            <a class="small text-underline-hover text-primary active" href="/mypage/post.do">게시글 관리</a>
                        </div>
                        <div>
                            <a class="small text-dark text-underline-hover" href="/mypage/review.do">리뷰 관리</a>
                        </div>
                        <div>
                            <a class="small text-dark text-underline-hover" href="/mypage/like.do">좋아요 목록</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="border-start border-end border-bottom p-0">
                <div class="p-3">
                    <div class="mx-3 mb-2">
                        <p class="fs-5 m-0">MY 정보</p>
                        <div class="mt-3">
                            <a class="small text-dark text-underline-hover"
                               href="/mypage/info.do">개인정보확인/수정</a>
                        </div>
                        <div>
                            <a class="small text-dark text-underline-hover" href="/mypage/address.do">배송지 관리</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 주문 상세 페이지 -->
        <div class="col p-0">
            <div class="px-5">
                <h3>주문상세</h3>
            </div>
            <div>
              <span>${order.orderedAt}&nbsp;주문&nbsp;&nbsp;</span>
              <span style="color:gray">주문번호&nbsp;${order.code}</span>
            </div>
            <div class="p-5">
                <table class="table table-bordered">
                    <thead>
                    <tr class="text-center">
                        <th width="10%"> </th>
                        <th width="60%" colspan=2>상품 이름</th>
                        <th width="10%">상품 가격</th>
                        <th width="10%">상품 수량</th>
                        <th width="10">결제 금액</th>
                    </tr>
                    </thead>
                    <tbody class="text-center" id="board-list">
                    <tr>
                      	<td width="10%">${order.status}</td>
                        <td width="15%">
                          <img src="${order.orderItemVO.image}" style="width:70px; height:70px">
                        </td>
                        <td width="45%">${order.orderItemVO.name}</td>
                        <td width="10%">${order.orderItemVO.price}</td>
                        <td width="10%">${order.orderItemVO.quantity}</td>
                        <td width="10">${order.price}</td>
                    </tr>
                    </tbody>
                </table>
                <div style="margin-left: 800px;">
                  <button type="button" style="height:30px;width:60px;" class="btn btn-sm btn-outline-secondary" onClick="javascript:history.back()">목록</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/happy/fragments/footer.jsp" />
<jsp:include page="/happy/fragments/common-script.jsp" />
<script rel="script" src="/assets/project/member/js/mypage-board.js"></script>
</body>
</html>