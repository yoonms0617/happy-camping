<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/happy/fragments/head.jsp"/>
<style>
    .text-underline-hover {
        text-decoration: none;
    }

    .text-underline-hover:hover {
        text-decoration: underline;
    }
</style>
<body>
<jsp:include page="/happy/fragments/header.jsp"/>
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
            <div class="p-5">
                <div class="mb-1">
                    <span class="fs-4">${order.orderedAt}&nbsp;주문&nbsp;&nbsp</span>
                    <span class="text-muted">주문번호&nbsp;${order.code}</span>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr class="text-center">
                        <th class="p-3" width="50%" colspan=2>상품 정보</th>
                        <th class="p-3" width="15%">상품 가격</th>
                        <th class="p-3" width="10%">상품 수량</th>
                        <th class="p-3" width="15%">결제 금액</th>
                        <th class="p-3" width="10%">상태</th>
                    </tr>
                    </thead>
                    <tbody class="text-center" id="board-list">
                    <tr>
                        <td class="p-3">
                            <div>
                                <img src="${order.orderItemVO.image}" style="width:70px;">
                            </div>
                        </td>
                        <td class="p-3">
                            <div>
                                ${order.orderItemVO.name}
                            </div>
                        </td>
                        <td class="p-3">
                            <div>
                                <fmt:formatNumber value="${order.orderItemVO.price}" pattern="#,###"/>원
                            </div>
                        </td>
                        <td class="p-3">
                            <div>
                                <span>${order.orderItemVO.quantity}</span>
                            </div>
                        </td>
                        <td class="p-3">
                            <div>
                                <fmt:formatNumber value="${order.price}" pattern="#,###"/>원
                            </div>
                        </td>
                        <td class="p-3">
                            <div>
                                ${order.status}
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="mt-1">
                    <span class="fs-5">배송 정보</span>
                </div>
                <table class="table table-bordered">
                    <tbody>
                    <tr>
                        <th class="p-3" width="15%">주문자</th>
                        <td class="p-3">${order.deliveryVO.name}</td>
                    </tr>
                    <tr>
                        <th class="p-3">우편번호</th>
                        <td class="p-3">${order.deliveryVO.postcode}</td>
                    </tr>
                    <tr>
                        <th class="p-3">집주소</th>
                        <td class="p-3">${order.deliveryVO.homeAddr}</td>
                    </tr>
                    <tr>
                        <th class="p-3">상세주소</th>
                        <td class="p-3">${order.deliveryVO.detailAddr}</td>
                    </tr>
                    </tbody>
                </table>
                <div class="text-center">
                    <button type="button" class="btn btn-lg btn-outline-secondary"
                            onClick="javascript:history.back()" style="width: 200px;">목록
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/happy/fragments/footer.jsp"/>
<jsp:include page="/happy/fragments/common-script.jsp"/>
</body>
</html>