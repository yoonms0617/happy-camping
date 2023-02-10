<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/happy/fragments/head.jsp" flush="false"/>
<jsp:include page="/happy/fragments/header.jsp" flush="false"/>
<link rel="stylesheet" href="/assets/project/search/css/search.css"/>
<body>

<div class="album py-5 " >
  <div class="container" style="max-width: 1200px" >
    <div  class="mb-4">
        <h3>"${ss }"&nbsp;관련 검색어 검색 결과 <small>(${itemtotal })</small></h3>
    </div>
	<div class="clear"></div>
	
	<c:if test="${itemtotal==0 }">
	  <c:choose> 
	     <c:when test="${ss.equals('')}">
		     <h5 class="text-danger" >검색어를 입력해주세요</h5>
	     </c:when> 
	     <c:otherwise>
		     <h5 class="text-danger" >검색어를 확인해주세요</h5>
	     </c:otherwise> 
      </c:choose> 
   </c:if>

  <c:if test="${itemtotal>0 }">
  <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-md-4 g-5">
    <c:forEach var="vo" items="${list }" varStatus="s">
	    <div class="col" style= "padding:4px">
	       <div class="card shadow-sm" >
	          <a href="/item/item_before_detail.do?ino=${vo.ino }"> 
				 <img src="${vo.image }" title="${vo.name }" class="bd-placeholder-img card-img-top" 
				 width="100%" height="300px"  focusable="false"/>
			  </a>  
			  <a href="/item/item_before_detail.do?ino=${vo.ino }">
			     <title>${vo.name }</title>
			  </a>
			 <div class="card-body" style="width=100%; height=300px" >
	            <a href="/item/item_before_detail.do?ino=${vo.ino }" style="text-decoration:none; color: #555555; font-size: 15px" >
	             <p class="card-text" style="text-overflow: ellipsis; white-space : nowrap; overflow : hidden;">
	              <font size="4"> ${vo.name }</font></p></a>
	               <div class="d-flex position-relative">
	                <c:if test="${vo.sale!=0 }">
                      <p class="col-form1 fs-4 text-danger pt-sm-2 pe-2">
                          ${vo.sale}%
                      </p>
                     <div class="">
                      <p class="text-decoration-line-through mb-0 form-check-inline">
                         <fmt:formatNumber value="${vo.price}" pattern="#,###"/>원 
                      </p>
                      <p>
                        <fmt:formatNumber value="${vo.price * ((100 - vo.sale) * 0.01)}" pattern="#,###"/>원
                      </p>
                    </div> 
                    <div class="pt-sm-1"   style="    position: absolute;right: 0">
	                   <button type="button" style="height:40px;width:50px;" class="btn btn-sm btn-outline-secondary">
                          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                          </svg>
                        </button>
	                </div> 
                    </c:if>
                    <c:if test="${vo.sale==0 }">
                    <div>
                      <p class="me-3 mt-sm-1 mb-0 me-md-4 form-check-inline">
                         <fmt:formatNumber value="${vo.price}" pattern="#,###"/>원 
                      </p>
                    </div>
                    <div class="btn-group1 ml-sm-3 ps-5" style="position: absoulte; left:60">
                    <!-- 회원이 좋아요 했는지 안했는지 비교 조건문 -->
	                    <button type="button" style="height:50px;width:60px;" class="btn btn-sm btn-outline-secondary" >
<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
        </svg>
</button>
	                 <div style="height:5px"></div>
	                </div>
	                </c:if>
                 </div>               
	         </div>
	       </div>
	     </div>
      </c:forEach>
    </div>
  </c:if>
  </div>
</div>

<!-- 페이징 관련 -->
<div class="clear"></div>
  <div class="mt-5">
  	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	     <li class="page-item">
           <a class="page-link <c:if test="${curpage<= 10}">disabled</c:if>"
              href="list.do?page=${startPage}">&laquo;</a>
         </li>
	     <li class="page-item">
           <a class="page-link <c:if test="${curpage <= 1}">disabled</c:if>"
              href="list.do?page=${curpage - 1}">◀︎</a>
         </li>
     <c:forEach var="i" begin="${startPage}" end="${endPage}">
         <li class="page-item <c:if test="${i == curpage}">active</c:if>">
             <a class="page-link" href="list.do?page=${i}">${i}</a>
         </li>
    </c:forEach>
	     <li class="page-item">
             <a class="page-link <c:if test="${curpage == totalpage}">disabled</c:if>"
                 href="list.do?page=${curpage + 1}">▶︎</a>
         </li>
	     <li class="page-item">
            <a class="page-link <c:if test="${curpage >= totalpage}">disabled</c:if>"
              href="list.do?page=${endPage}">&raquo;</a>
        </li>
	</ul>
  </nav>
</div>

<div class="clear"></div>
<jsp:include page="/happy/fragments/footer.jsp" flush="false"/>
<jsp:include page="/happy/fragments/common-script.jsp" flush="false"/>
  </body>
</html>