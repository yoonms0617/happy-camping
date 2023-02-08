<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/happy/fragments/head.jsp" %>
<style>
    .text-underline-hover {
        text-decoration: none;
    }

    .text-underline-hover:hover {
        text-decoration: underline;
    }
</style>
<body>
<%@include file="/happy/fragments/header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col p-0" style="max-width: 200px;">
            <div class="border">
                <div class="p-5 text-center bg-light">
                    <a class="text-decoration-none text-dark" href="/mypage.do">
                        <h3 class="m-auto">MY 캠핑</h3>
                    </a>
                </div>
            </div>
            <div class="border-start border-end border-bottom p-0">
                <div class="p-3">
                    <div class="mx-3 mb-2">
                        <p class="fs-5 m-0">MY 쇼핑</p>
                        <div class="mt-3">
                            <a class="small text-underline-hover text-dark" href="/mypage.do">주문 목록</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="border-start border-end border-bottom p-0">
                <div class="p-3">
                    <div class="mx-3 mb-2">
                        <p class="fs-5 m-0">MY 활동</p>
                        <div class="mt-3">
                            <a class="small text-underline-hover text-dark" href="/mypage/post.do">게시글 관리</a>
                        </div>
                        <div>
                            <a class="small text-dark text-underline-hover" href="/mypage/review.do">리뷰 관리</a>
                        </div>
                        <div>
                            <a class="small text-primary text-underline-hover active" href="/mypage/like.do">좋아요 목록</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="border-start border-end border-bottom p-0">
                <div class="p-3">
                    <div class="mx-3 mb-2">
                        <p class="fs-5 m-0">MY 정보</p>
                        <div class="mt-3">
                            <a class="small text-dark text-underline-hover"
                               href="/mypage/info.do">개인정보확인/수정</a>
                        </div>
                        <div>
                            <a class="small text-dark text-underline-hover" href="/mypage/address.do">배송지 관리</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col p-0">
            <div class="px-5">
                <h3>좋아요(캠핑장, 상품)</h3>
            </div>
            <div class="p-5">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="home-tab" data-bs-toggle="tab"
                                data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane"
                                aria-selected="true">캠핑장
                        </button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="profile-tab" data-bs-toggle="tab"
                                data-bs-target="#profile-tab-pane" type="button" role="tab"
                                aria-controls="profile-tab-pane" aria-selected="false">상품
                        </button>
                    </li>
                </ul>
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab"
                         tabindex="0">
                        <div id="camp-like-list">
                            <!-- 캠핑장 좋아요 목록나오는 곳 -->
                            <!-- -->
                        </div>
                        <hr>
                        <div>
                            <div class="d-flex justify-content-start align-items-center">
                                <input class="form-check-input me-2" type="checkbox" id="select-all">
                                <label class="form-check-label me-3" for="select-all">전체선택</label>
                                <button type="button" class="btn btn-sm btn-outline-primary">선택삭제</button>
                            </div>
                        </div>
                        <div class="mt-3 mb-2">
                            <nav class="d-flex justify-content-center">
                                <ul class="pagination pagination-sm" id="camp-page-buttons">
                                    <!-- 페이징 버튼 나오는 곳 -->
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab"
                         tabindex="0">
                        <div id="item-like-list">
                            <!-- 아이템 좋아요 목록나오는 곳 -->
                        </div>
                        <div class="mt-3 mb-2">
                            <nav class="d-flex justify-content-center">
                                <ul class="pagination pagination-sm" id="item-page-buttons">
                                    <!-- 페이징 버튼 나오는 곳 -->
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script rel="script" src="/assets/project/member/js/mypage-like.js"></script>
</body>
</html>
