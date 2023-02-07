<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .position {
        position: fixed;
    }
</style>
<header>
    <div class="py-2 bg-light border-bottom">
        <div class="container d-flex flex-wrap justify-content-end">
            <ul class="nav">
                <c:if test="${sessionScope.mid!=null }">
                    <input type="hidden" id="loginMid" value="${sessionScope.mid}">
                    <li class="nav-item me-3">
                    <li class="nav-item">
                        <a href="/member/mypage.do" class="text-decoration-none me-3 fs-5">My Page</a>
                    </li>
                    <li class="nav-item">
                        <a href="/cart/cart.do" class="text-decoration-none me-3 fs-5">장바구니</a>
                    </li>
                    <li>
                        <a href="/member/logout.do" class="text-decoration-none fs-5">로그아웃</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.mid==null }">
                    <li class="nav-item me-3">
                        <a href="/member/login1.do" class="text-decoration-none fs-5">로그인</a>
                    </li>
                    <li class="nav-item">
                        <a href="/member/signup.do" class="text-decoration-none fs-5">회원가입</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
 <div class="container">
        <div class="mt-4 mb-4">
            <div class="d-flex flex-nowrap align-items-center">
                <div class="col-4 text-start">
                    <a class="text-dark h1 text-decoration-none" href="../main.do">
                        <img src="\happy\fragments\hclogo.png" class="img-thumbnail border-0" style="width:350px">
                    </a>
                </div>
                <form action="../search/list.do" method="post">
                    <div class="d-flex justify-content-start mt-5">
                        <div class="input-group" style="width:450px;">
                            <input type="text" class="form-control" id="ss" name="ss"
                                   value="${ss}">
                            <button class="btn btn-outline-success" type="submit" id="search-btn" style="width: 80px;">
                                검색
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
 <%@include file="navigation.jsp" %>
</header>