<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/happy/fragments/head.jsp" %>
<!-- category css -->
<link rel="stylesheet" href="/assets/project/main/css/category.css">
<!-- slider css -->
<link rel="stylesheet" href="/assets/slick/css/slick.css">
<link rel="stylesheet" href="/assets/slick/css/slick-theme.css">
<body>
<%@include file="/happy/fragments/header.jsp" %>
<div class="container">
    <!-- main slider -->
    <div class="main-slider">
        <div>
            <img src="/assets/images/main/main-01.jpg">
        </div>
        <div>
            <img src="/assets/images/main/main-02.jpg">
        </div>
        <div>
            <img src="/assets/images/main/main-03.jpg">
        </div>
        <div>
            <img src="/assets/images/main/main-04.jpg">
        </div>
    </div>
    <div class="m-auto">
        <div class="mt-5">
            <span class="display-6">브랜드 상품 퀵메뉴</span>
            <div class="brand-menu-slider">
                <div class="border">
                    <img src="/assets/images/main/brand/brand_001.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_002.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_003.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_004.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_005.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_006.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_007.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_008.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_009.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_010.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_011.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_012.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_013.png">
                </div>
                <div class="border">
                    <img src="/assets/images/main/brand/brand_014.png">
                </div>
            </div>
        </div>
    </div>
    <div class="m-auto">
        <div class="mt-5">
            <span class="display-6">초특가 상품</span>
            <!-- 초특가 상품 slider -->
            <div class="sale-item-slider">
                <c:forEach var="hotSaleItem" items="${hotSaleItems}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <div class="card-img-top" width="100%" height="100%">
                                <img class="m-auto" src="${hotSaleItem.image}" width="312"/>
                            </div>
                            <div class="card-body">
                                <p class="card-text text-truncate">${hotSaleItem.name}</p>
                                <p class="card-text">
                                    <fmt:formatNumber value="${hotSaleItem.price}" pattern="#,###"/>원
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="m-auto mb-3">
        <div class="mt-5">
            <span class="display-6">따끈따끈한 신규상품을 만나보세요!</span>
            <!-- 신규상품 슬라이더 -->
            <div class="new-item-slider">
                <c:forEach var="newItem" items="${newItems}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <div class="card-img-top" width="100%" height="100%">
                                <img class="m-auto" src="${newItem.image}" width="312"/>
                            </div>
                            <div class="card-body">
                                <p class="card-text text-truncate">${newItem.name}</p>
                                <p class="card-text">
                                    <fmt:formatNumber value="${newItem.price}" pattern="#,###"/>원
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<!-- slider js -->
<script rel="script" src="/assets/slick/js/slick.min.js"></script>
<script rel="script" src="/assets/project/main/js/slider.js"></script>
</body>
</html>