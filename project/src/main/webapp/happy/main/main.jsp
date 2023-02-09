<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/happy/fragments/head.jsp" flush="false"/>
<!-- slider css -->
<link rel="stylesheet" href="/assets/slick/css/slick.css">
<link rel="stylesheet" href="/assets/slick/css/slick-theme.css">
<body>
<jsp:include page="/happy/fragments/header.jsp" flush="false"/>
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
    <div style=" height:5px"></div>
    <div class="m-auto">
        <div class="mt-5">
            <span class="display-6">초특가 상품</span>
            <!-- 초특가 상품 slider -->
            <div class="sale-item-slider">
                <c:forEach var="hotSaleItem" items="${hotSaleItems}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <div class="card-img-top" width="100%" height="100%">
                                <a href="/item/item_before_detail.do?ino=${hotSaleItem.ino }">
                                  <img class="m-auto" src="${hotSaleItem.image}" width="312"/>
                                </a>
                            </div>
                            <div class="card-body">
                                <a href="/item/item_before_detail.do?ino=${hotSaleItem.ino }"class="text-decoration-none text-underline-hover text-dark">
                                  <p class="card-text text-truncate">${hotSaleItem.name}</p>
                                </a>
                                <div class="d-flex position-relative">
                                    <p class="align-items-center text-danger display-6 flex-shrink-0 mb-0 me-2">
                                            ${hotSaleItem.sale}%
                                    </p>
                                    <div>
                                        <p class="text-decoration-line-through mb-0">
                                            <fmt:formatNumber value="${hotSaleItem.price}" pattern="#,###"/>원 <br/>
                                        </p>
                                        <fmt:formatNumber value="${hotSaleItem.price * ((100 - hotSaleItem.sale) * 0.01)}" pattern="#,###"/>원
                                    </div>
                                </div>
                                <span>
                                </span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div style=" height:5px"></div>
    <div class="m-auto mb-3">
        <div class="mt-5">
            <span class="display-6">따끈따끈한 신규상품을 만나보세요!</span>
            <!-- 신규상품 슬라이더 -->
            <div class="new-item-slider">
                <c:forEach var="newItem" items="${newItems}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <div class="card-img-top" width="100%" height="100%">
                             <a href="/item/item_before_detail.do?ino=${newItem.ino }">
                                <img class="m-auto" src="${newItem.image}" width="312"/>
							</a>                              
                            </div>
                            <div class="card-body">
                            <a href="/item/item_before_detail.do?ino=${newItem.ino }"class="text-decoration-none text-underline-hover text-dark">
                                <p class="card-text text-truncate">${newItem.name}</p>
                                </a>
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
     <div style=" height:30px"></div>
     <div class="container">
        <div class=" row" >
        <div class="col-lg-6 col-md-6 col-12">
         <div class="m-auto mb-3">
        <div class="mt-6">
            <span class="display-6">재입고 상품</span>
            <!-- 재입고 상품 슬라이더 -->
            <div class="main-slider">
                <c:forEach var="reItems" items="${reItems}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <div class="card-img-top" width="100%" height="100%">
                             <a href="/item/item_before_detail.do?ino=${reItems.ino }">
                                <img class="m-auto" src="${reItems.image}" width="312"/>
							</a>                              
                            </div>
                            <div class="card-body">
                            <a href="/item/item_before_detail.do?ino=${reItems.ino }"class="text-decoration-none text-underline-hover text-dark">
                                <p class="card-text text-truncate">${reItems.name}</p>
                                </a>
                                <p class="card-text">
                                    <fmt:formatNumber value="${reItems.price}" pattern="#,###"/>원
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    </div>
    <!-- ////////////////// --> 
    <div class="col-lg-6 col-md-6 col-12">
         <div class="m-auto mb-3">
        <div class="mt-6">
            <span class="display-6">추천 상품</span>
            <!-- 추천상품 슬라이더 -->
            <div class="main-slider">
                <c:forEach var="pickItems" items="${pickItems}">
                    <div class="col">
                    
                        <div class="card shadow-sm">
                            <div class="card-img-top" width="100%" height="100%">
                             <a href="/item/item_before_detail.do?ino=${pickItems.ino }">
                                <img class="m-auto" src="${pickItems.image}" width="312"/>
							</a>                              
                            </div>
                            <div class="card-body">
                            <a href="/item/item_before_detail.do?ino=${pickItems.ino }"class="text-decoration-none text-underline-hover text-dark">
                                <p class="card-text text-truncate">${pickItems.name}</p>
                                </a>
                                <p class="card-text">
                                    <fmt:formatNumber value="${pickItems.price}" pattern="#,###"/>원
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    </div>
      </div>
    </div>
    <!-- 분할 창 끝 -->
    <div style=" height:5px"></div>
    <div class="m-auto mb-3">
        <div class="mt-5">
            <span class="display-6">베스트 캠핑장</span>
            <!-- 신규상품 슬라이더 -->
            <div class="new-item-slider">
                <c:forEach var="campItems" items="${campItems}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <div class="card-img-top" width="100%" height="100%">
                                <img class="m-auto" src="${campItems.image}" width="312" height="300"/>
                            </div>
                            <div class="card-body">
                                <p class="card-text text-truncate">${campItems.name}</p>
                                <%-- <p class="card-text">
                                    <fmt:formatNumber value="${newItem.price}" pattern="#,###"/>원
                                </p> --%>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<!-- 메인페이지 : 퀵메뉴바 관련 기능 -->
<!-- 메인페이지 : 퀵메뉴바 관련 기능 -->
<c:if test="${count!=0 }">
   <!-- 상품을 1개 본 경우 -->
   <c:if test="${count==1 }">
    <div id="Quick" class="bg-lite" style="position: absolute; right: 20px; top: 400px;">
    <div>
    <button type="button" class="btn btn-sm btn-secondary" style="width:70px;height: 18px;font-size: 2px" onclick="window.scrollTo(0,0)" >△</button>
    </div>
    <div class="border bg-light"style="height:90px">
    <c:forEach var="ivo" items="${iList }" varStatus="s">
    <div  colspan="2" style="cursor:pointer;">
      <c:if test="${s.index<3 }">  
         <a href="/item/item_detail.do?ino=${ivo.ino }">
         <img class="border"src="${ivo.image }" style="width: 70px; height: 90px"></a>
      </c:if>
     </div>
	 </c:forEach>
   </div>
   <div class="">
    <button type="button" class="btn btn-sm btn-secondary"onclick="window.scrollTo(10000,10000)"  style="width:70px;height: 18px;font-size: 2px" >▽</button>
   </div>
   </div>
   </c:if>
   
   <!-- 상품을 2개 본 경우 -->
   <c:if test="${count==2 }">
    <div id="Quick" class="bg-lite" style="position: absolute; right: 20px; top: 400px;">
    <div>
    <button type="button" class="btn btn-sm btn-secondary"  style="width:70px;height: 18px;font-size: 2px"onclick="window.scrollTo(0,0)"  >△</button>
    </div>
    <div class="border bg-light"style="height:180px">
    <c:forEach var="ivo" items="${iList }" varStatus="s">
    <div  colspan="2" style="cursor:pointer;">
      <c:if test="${s.index<3 }">  
         <a href="/item/item_detail.do?ino=${ivo.ino }">
         <img class="border"src="${ivo.image }" style="width: 70px; height: 90px"></a>
      </c:if>
     </div>
	 </c:forEach>
   </div>
   <div class="">
    <button type="button" class="btn btn-sm btn-secondary"onclick="window.scrollTo(10000,10000)"   style="width:70px;height: 18px;font-size: 2px">▽</button>
   </div>
   </div>
   </c:if>
   
   
  <!-- 상품을 3개이상 본 경우 -->
  <c:if test="${count>3 }">
   <div id="Quick" class="bg-lite" style="position: absolute; right: 20px; top: 400px;">
   <div>
    <button type="button" class="btn btn-sm btn-secondary"  style="width:70px;height: 18px;font-size: 2px"onclick="window.scrollTo(0,0)"  >△</button>
   </div>
   <div class="border bg-light"style="height:270px">
   <c:forEach var="ivo" items="${iList }" varStatus="s">
   <div  colspan="2" style="cursor:pointer;">
       <c:if test="${s.index<3 }">  
         <a href="/item/item_detail.do?ino=${ivo.ino }">
         <img class="border"src="${ivo.image }" style="width: 70px; height: 90px"></a>
       </c:if> 
       </div>
	 </c:forEach>
   </div>
   <div class="">
    <button type="button" class="btn btn-sm btn-secondary" onclick="window.scrollTo(10000,10000)"   style="width:70px;height: 18px;font-size: 2px" >▽</button>
   </div>
   </div>
  </c:if>
</c:if>
<jsp:include page="/happy/fragments/footer.jsp" flush="false"/>
<jsp:include page="/happy/fragments/common-script.jsp" flush="false"/>
<!-- slider js -->
<script rel="script" src="/assets/slick/js/slick.min.js"></script>
<script rel="script" src="/assets/project/main/js/slider.js"></script>
<script rel="script" src="/assets/project/main/js/main.js"></script>
</body>
</html>