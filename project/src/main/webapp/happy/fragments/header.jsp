<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
<style>
.position
{
   position:fixed;
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
        <div class="mt-4 mb-4"><!--justify-content-between  -->
            <div class="row flex-nowrap align-items-center                                                                                                                                                                                                                                                                                                                                                                                                                              ">
                <div class="col-4 text-start" >
                     <a class="text-dark h1 text-decoration-none" href="../main.do"><img src="\happy\fragments\hclogo.png" class="img-thumbnail border-0" style="width:350px"></a>
                </div>
               
                 <div>
            <form action="../search/list.do" method="get">
                <input type="hidden" value="${orderType}" name="orderType">
                <div class="d-flex justify-content-start mt-5">
                    <div class="me-2" style="width: 130px;">
                        <select class="form-select" id="search-type" name="searchType">
                            <option value="all" <c:if test="${searchType == 'all'}">selected</c:if>>통합검색</option>
                            <option value="item" <c:if test="${searchType == 'item'}">selected</c:if>>상품</option>
                        </select>
                    </div>
                    <div class="input-group" style="max-width: 400px;">
                        <input type="text" class="form-control" id="search-key" name="searchKeyword" value="${searchKeyword}">
                        <button class="btn btn-outline-success" type="submit" id="search-btn" style="width: 80px;">검색</button>
                    </div>
                </div>
            </form>
        </div>
            </div>
        </div>
        <hr>
    </div>
    <%@include file="navigation.jsp"%>
</header>