<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <th>수량</th>
                <th>상품구매금액</th>
                <th>할인금액</th>
                <th>배송비</th>
                <th>주문</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test=" ">
                    <tr>
                        <td colspan=8>
                            <strong>장바구니에 담긴 상품이 없습니다</strong>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="vo" items="${list}">
                        <tr>
                            <!-- 장바구니에 담긴 상품 -->
                            <td><!-- checkbox --></td>
                            <td>
                                <img src="${vo.image}" style="width:30px;height: 30px">
                            </td>
                            <td>${vo.name}</td>
                            <td>${vo.quantity}</td>
                            <td>${vo.price!=null?vo.price:"-"}</td>
                            <td>${vo.ivo.sale}</td>
                            <td>${vo.price*vo.quantity>=50000?"무료":3000}</td>
                            <td>
                                <span class="btn btn-xs orderBtn">구매</span>
                                <a href="/cart/cart_cancel.do?cno=${vo.cno}" class="btn btn-xs btn-warning">삭제</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <tr>
                <!-- 합계 금액 -->
            </tr>
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
                <td></td>
                <td></td>
                <td colspan=3></td>
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