<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<%@include file="/happy/fragments/head.jsp" %>

 <style type="text/css">
	.clear{ /* css초기화 */
		clear: both;
	}
	#content_box{
		padding-top: 30px;
	}
	#content_box h3{
		float: left;
		margin-left: 620px;
	}
	
	#content_box p{
		float: right;
		margin-top: 50px;
	}
	
	.box{
		width: 1400px; 
		margin: 0 auto; /* 중앙정렬 */
	}
/* 상품정렬 */
 section#content ul li { display:inline-block; margin:10px; }
 section#content div.one_quarter img { width:300px; height:300px; }
 section#content div.itemsName { padding:10px 0; text-align:center; width: 300px; text-overflow: ellipsis; white-space : nowrap; overflow : hidden;}
 section#content div.itemsPrice { padding:10px 0; text-align:center; width: 300px; }

/* 페이지 버튼 인터페이스 */
.pageMaker_wrap{
	text-align: center;
    margin-top: 30px;
    margin-bottom: 40px;
}
.pageMaker{
    list-style: none;
    display: inline-block;
}	
.pageMaker_btn {
    float: left;
    width: 12px;
    height: 40px;
    line-height: 40px;
    margin-left: 20px;
}
.active{
	border : 2px solid black;
	font-weight:100;
}
.next, .prev {
    border: 1px solid #ccc;
    padding: 0 10px;
    width: 65px;
    font-size: 13px;
}
.pageMaker_btn a:link {color: black;}
.pageMaker_btn a:visited {color: black;}
.pageMaker_btn a:active {color: black;}
.pageMaker_btn a:hover {color: black;}
.next a, .prev a {
    color: #ccc;
}
</style>

<%@include file="/happy/fragments/header.jsp" %>
<body>
<section id="content_box">
  <div class="box">
	<h3>${title }</h3>
	<p> 판매자추천순 | 인기도순 | 평점높은순 | 최신등록순 </p> 
	<div class="clear"></div>		
	  <section id="content">
		<ul class="items">
			<c:forEach var="vo" items="${list }" varStatus="s">
	  		<li>
	  		  <div class="one_quarter ${s.index%4==0?'first':'' }">
	            <a href="#"><img src="${vo.image }" title="${vo.name }"></a>
	          </div>
	         
	         <div class="itemsName">
	         	<a href="#">${vo.name }</a>
	         </div>
	         <div class="itemsPrice">
	         	
	         	<fmt:formatNumber value="${vo.price}" pattern="#,###"/>원
	         </div>
	          </li>
	          </c:forEach>
		</ul>
	  </section>
	
	
  </div>
  <div class="clear"></div>
  <div class="pageMaker_wrap">
	  <ul class="pageMaker">
	     <c:if test="${startPage>1 }">
	         <li class="pageMaker_btn prev"><a href="list.do?page=${startPage-1 }">&laquo; Pre</a></li>
	     </c:if>
	     
	     <c:forEach var="i" begin="${startPage }" end="${endPage }">
	        <li class="pageMaker_btn" ${i==curpage?"class=current":"" }><a href="list.do?type=${type }&type1=${type1 }&page=${i }">${i }</a></li>
	     </c:forEach>
	     <c:if test="${endPage<totalpage }">
	        <li class="pageMaker_btn next"><a href="list.do?type=${type }&page=${endPage+1 }">Next &raquo;</a></li>
	     </c:if>
	     <%-- <c:if test="{endPage=totalpage }">
	     	<li class="pageMaker_btn" ${endPage = totalpage}><a href="list.do?page=${i }">${i }</a></li>
	     </c:if> --%>
	  </ul>
  </div>
  <div class="clear"></div>
  
  <!-- -------------------------------------------------------------- -->
  <!-- -------------------------------------------------------------- -->
  <div class="clear"></div>
  <div class="mt-5">
    <nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	     <c:if test="${startPage>1 }">
	         <li class="page-item">
	         	<a href="list.do?page=${startPage-1 }">&laquo;◀︎ </a>
	         </li>
	     </c:if>
	     
	     <c:forEach var="i" begin="${startPage }" end="${endPage }">
	        <li class="page-item" ${i==curpage?"class=current":"" }>
	          <a class="page-link" href="list.do?type=${type }&type1=${type1 }&page=${i }">${i }</a>
	        </li>
	     </c:forEach>
	     
	     <c:if test="${endPage<totalpage }">
	        <li class="page-item">
	        	<a class="page-link" href="list.do?type=${type }&page=${endPage+1 }"> ▶︎ &raquo;</a>
	        </li>
	     </c:if>
	     <%-- <c:if test="{endPage=totalpage }">
	     	<li class="pageMaker_btn" ${endPage = totalpage}><a href="list.do?page=${i }">${i }</a></li>
	     </c:if> --%>
	  </ul>
	  </nav>
  </div>
  <div class="clear"></div>
  
</section>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
</body>
</html>

































<div class="mt-5">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <a class="page-link <c:if test="${page.first || page.curPage - 10 <= 0}">disabled</c:if>"
                           href="camp_list.do?page=${page.curPage - 10}">&laquo;</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link <c:if test="${page.first}">disabled</c:if>"
                           href="camp_list.do?page=${page.curPage - 1}">◀︎</a>
                    </li>
                    <c:forEach var="pageNumber" begin="${page.startPage}" end="${page.endPage}">
                        <li class="page-item <c:if test="${pageNumber == page.curPage}">active</c:if>">
                            <a class="page-link" href="camp_list.do?page=${pageNumber}">${pageNumber}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link <c:if test="${page.last}">disabled</c:if>"
                           href="camp_list.do?page=${page.curPage + 1}">▶︎</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link <c:if test="${page.last || (page.curPage + 10) > page.totalPage}">disabled</c:if>"
                           href="camp_list.do?page=${page.curPage + 10}">&raquo;</a>
                    </li>
                </ul>
            </nav>
        </div>