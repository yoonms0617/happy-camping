<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.search input
{
  border-radius: 5px 5px 5px 5px;
}
.search img
{
   left: 1000px; top: 81px;
   position: absolute;
}
</style>
<header>
    <div class="py-2 bg-light border-bottom">
        <div class="container d-flex flex-wrap justify-content-end">
            <ul class="nav">
              <c:if test="${sessionScope.mid!=null }">
                  <input type="hidden" id="loginMid" value="${sessionScope.mid}">
               <li class="nav-item me-3">
               <li class="nav-item" >
                 <a href="/member/update.do" class="text-decoration-none"style="font-size:18">My Page</a>
               </li>
               <li>
                   <a href="/member/logout.do" class="text-decoration-none"style="font-size:18">로그아웃</a>
                </li>
            </c:if>
             <c:if test="${sessionScope.mid==null }">
                <li class="nav-item me-3">
                    <a href="/member/login1.do" class="text-decoration-none" style="font-size:18">로그인</a>
                </li>
                <li class="nav-item">
                    <a href="/member/signup.do" class="text-decoration-none"style="font-size:18">회원가입</a>
                </li>
           </c:if>
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="mt-4 mb-4">
            <div class="row flex-nowrap justify-content-between align-items-center">
                <div class="col-4 text-start">
                     <a class="text-dark h1 text-decoration-none" href="/main.do"><img src="\happy\fragments\hclogo.png" style="width: 400px; height: auto"></a>
                </div>
                <div name="검색" class="search">
                  <input type=text style="height:40px;width:400px;" placeholder="  검색어를 입력하세요" >
                    <img src="/assets/images/main/search/search.png" width="20" >
                 </div>
            </div>
        </div>
        <hr>
    </div>
    <%@include file="navigation.jsp"%>
</header>