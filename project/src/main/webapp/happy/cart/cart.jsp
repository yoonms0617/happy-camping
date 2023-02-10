<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/happy/fragments/head.jsp"/>
<style>
    .text-underline-hover {
        text-decoration: none;
    }

    .text-underline-hover:hover {
        text-decoration: underline;
    }
</style>
<jsp:include page="/happy/fragments/header.jsp"/>
<body>
<div class="container">
    <div class="mt-5">
        <h2>장바구니</h2>
        <div class="mt-3">
            <table class="table table-hover">
                <thead>
                <tr class="text-center">
                    <th width="60%">상품정보</th>
                    <th width="15%">상품금액</th>
                    <th width="15%">배송비</th>
                    <th width="10%">구매</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${count == 0}">
                        <tr class="text-center">
                            <th colspan="3">
                                <div class="p-3">
                                    <h3>장바구니에 담은 상품이 없습니다.</h3>
                                </div>
                            </th>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="vo" items="${list}">
                            <tr>
                                <th>
                                    <div class="p-3">
                                        <div class="d-flex justify-content-between">
                                            <div class="d-flex justify-content-start align-items-center">
                                                <div class="me-2">
                                                    <a role="button" class="btn btn-sm btn-outline-danger"
                                                       onclick="location.href='/cart/cart_cancel.do?cno=${vo.cno}'">
                                                        <small style="font-size: 3px">X</small>
                                                    </a>
                                                </div>
                                                <div class="me-2">
                                                    <img src="${vo.image}" width="100px;">
                                                </div>
                                                <div class="m-0 d-inline-block text-truncate" style="max-width: 400px;">
                                                    <span class="fs-6">
                                                        <a class="text-dark text-underline-hover"
                                                           href="/item/item_detail.do?ino=${vo.ivo.ino}">
                                                                ${vo.name}
                                                        </a>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="d-flex justify-content-end align-items-center">
                                                <div class="me-2">
                                                    <small class="text-muted">
                                                        <fmt:formatNumber value="${vo.price != 0 ? vo.price : 0}"
                                                                          pattern="#,###"/>원
                                                    </small>
                                                </div>
                                                <input value="${vo.quantity}" type="text" readonly
                                                       style="width: 50px;">
                                                <div>
                                                    <a href="/cart/cart_update_up.do?cno=${vo.cno}"
                                                       style="display:block;">
                                                        <img src="https://img.echosting.cafe24.com/skin/base_ko_KR/product/btn_count_up.gif"
                                                             alt="수량증가" class="QuantityUp up">
                                                    </a>
                                                    <a href="/cart/cart_update_down.do?cno=${vo.cno}"
                                                       style="display:block;">
                                                        <img src="https://img.echosting.cafe24.com/skin/base_ko_KR/product/btn_count_down.gif"
                                                             alt="수량감소" class="QuantityDown down">
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <td class="text-center">
                                    <div class="py-5 border-start">
                                        <fmt:formatNumber value="${(vo.price != 0 ? vo.price : 0) * vo.quantity}"
                                                          pattern="#,###"/>원
                                    </div>
                                </td>
                                <td class="text-center">
                                    <div class="p-5 border-start">
                                        <p class="m-auto">${(vo.price * vo.quantity) >= 50000 ? "무료" : 3000}</p>
                                    </div>
                                </td>
                                <td class="text-center">
                                    <div class="py-5 border-start">
                                        <button type="button" class="btn btn-sm btn-outline-secondary" onclick="location.href='/order/item.do?cno=${vo.cno}'">
                                            구매하기
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
            <div class="mt-5">
                <div class="d-flex justify-content-center">
                    <div>
                        <button type="button" class="btn btn-lg btn-outline-primary"
                                onclick="location.href='/main.do'"
                                style="width: 200px; height: 60px;">
                            계속 쇼핑하기
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/happy/fragments/footer.jsp"/>
<jsp:include page="/happy/fragments/common-script.jsp"/>
</body>
</html>