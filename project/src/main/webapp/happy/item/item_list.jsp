<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<jsp:include page="/happy/fragments/head.jsp" flush="false" /> 
<jsp:include page="/happy/fragments/header.jsp" flush="false" /> 
<!doctype html>
<html>
   
    <!-- <link rel="stylesheet" href="/assets/boostrap/css/bootstrap.min.css"> -->

    <style type="text/css">
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, 0);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }
	img:hover {
	 filter: brightness(50%);
	
	}
	
		.clear{ /* css초기화 */
		clear: both;
	}
	#content_box{
		padding-top: 30px;
	}
	.box h3{
		float:inherit;
		/* margin-left: 770px; */
	}
	
	.box p{
		float: right;
		margin-top: 50px;
		/* margin-right: 300px; */
	}
	
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
   
    <!--  <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/album/"> -->

<script type="text/javascript">
</script>
<body>
 <main>
  <div class="album py-5 bg-light" style="background-color: #ffffff">
    <div class="container" style="width=100px; height=300px" >
    
<!--     <div class="box"> -->
<%-- 		<h3>${title }</h3> --%>
<!-- 		<p> 판매자추천순 | 인기도순 | 평점높은순 | 최신등록순 </p>  -->
<!-- 	</div> -->
	<div class="clear"></div>
    
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-md-4 g-5">
       <c:forEach var="vo" items="${list }" varStatus="s">
	        <div class="col" style= "padding:4px">
	          <div class="card shadow-sm" >
	           
				 <a href="/item/item_before_detail.do?ino=${vo.ino }"> <img src="${vo.image }" title="${vo.name }" class="bd-placeholder-img card-img-top" width="100%"
					height="300"  focusable="false"/></a>  
					<title>${vo.name }</title>
					
					
				<div class="card-body" style="width=100%; height=300px" >
	              <a href="/item/item_before_detail.do?ino=${vo.ino }" style="text-decoration:none; color: #555555; font-size: 15px" ><p class="card-text" style="text-overflow: ellipsis; white-space : nowrap; overflow : hidden;">${vo.name }</p></a>
	              <div class="d-flex justify-content-between align-items-center" style="font-size: 14px">
	              	<fmt:formatNumber value="${vo.price}" pattern="#,###"  />원
	              </div>
	            </div>
	          </div>
	        </div>
        </c:forEach>
      </div>
    </div>
  </div>

</main>

  <!-- -------------------------------------------------------------- -->
  <!-- -------------------------------------------------------------- -->
<div class="clear"></div>
  <div class="mt-5">
  	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	     
	     <li class="page-item">
             <a class="page-link <c:if test="${curpage<= 10}">disabled</c:if>"
                href="list.do?type=${type }&type1=${type1 }&page=${startPage}">&laquo;</a>
         </li>
	     
	     
	      <li class="page-item">
              <a class="page-link <c:if test="${curpage <= 1}">disabled</c:if>"
                 href="list.do?type=${type }&type1=${type1 }&page=${curpage - 1}">◀︎</a>
          </li>
                    
                    
	    <c:forEach var="i" begin="${startPage}" end="${endPage}">
             <li class="page-item <c:if test="${i == curpage}">active</c:if>">
                 <a class="page-link" href="list.do?type=${type }&type1=${type1 }&page=${i}">${i}</a>
             </li>
         </c:forEach>
	     
	     <li class="page-item">
             <a class="page-link <c:if test="${curpage == totalpage}">disabled</c:if>"
                href="list.do?type=${type }&type1=${type1 }&page=${curpage + 1}">▶︎</a>
         </li>

	     <li class="page-item">
            <a class="page-link <c:if test="${curpage >= totalpage}">disabled</c:if>"
              href="list.do?type=${type }&type1=${type1 }&page=${endPage}">&raquo;</a>
        </li>
	     
	     
	     
	   </ul>
	  </nav>
  </div>
  <div class="clear"></div>







  <!-- -------------------------------------------------------------- -->
  <!-- -------------------------------------------------------------- -->

<!-- <div class="clear"></div> -->
<!--   <div class="pageMaker_wrap"> -->
<!-- 	  <ul class="pageMaker"> -->
<%-- 	     <c:if test="${startPage>1 }"> --%>
<%-- 	         <li class="pageMaker_btn prev"><a href="list.do?type=${type }&page=${startPage-1 }">&laquo; Pre</a></li> --%>
<%-- 	     </c:if> --%>
	     
	     
<%-- 	     <c:forEach var="i" begin="${startPage }" end="${endPage }"> --%>
<%-- 	        <li class="pageMaker_btn" ${i==curpage?"class=current":"" }><a href="list.do?type=${type }&type1=${type1 }&page=${i }">${i }</a></li> --%>
<%-- 	     </c:forEach> --%>
	     
	     
<%-- 	     <c:if test="${endPage<totalpage }"> --%>
<%-- 	        <li class="pageMaker_btn next"><a href="list.do?type=${type }&page=${endPage+1 }">Next &raquo;</a></li> --%>
<%-- 	     </c:if> --%>
<%-- 	     <c:if test="{endPage=totalpage }">
<%-- 	     	<li class="pageMaker_btn" ${endPage = totalpage}><a href="list.do?page=${i }">${i }</a></li> --%>
<%-- 	     </c:if> --%>
<!-- 	  </ul> -->
<!--   </div> -->
<!--   <div class="clear"></div> -->


  <jsp:include page="/happy/fragments/footer.jsp" flush="false" /> 
  <jsp:include page="/happy/fragments/common-script.jsp" flush="false" /> 
  </body>
</html>
