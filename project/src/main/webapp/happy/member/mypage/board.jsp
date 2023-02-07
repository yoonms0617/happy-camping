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
                            <a class="small text-underline-hover text-dark"
                               href="/mypage.do">주문 목록</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="border-start border-end border-bottom p-0">
                <div class="p-3">
                    <div class="mx-3 mb-2">
                        <p class="fs-5 m-0">MY 활동</p>
                        <div class="mt-3">
                            <a class="small text-underline-hover text-primary active" href="/mypage/post.do">게시글 관리</a>
                        </div>
                        <div>
                            <a class="small text-dark text-underline-hover" href="/mypage/review.do">리뷰 관리</a>
                        </div>
                        <div>
                            <a class="small text-dark text-underline-hover" href="/mypage/like.do">좋아요 목록</a>
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
                <h3>게시글 관리</h3>
            </div>
            <div class="p-5">
                <table class="table table-bordered">
                    <thead>
                    <tr class="text-center">
                        <th width="10%" scope="col">글번호</th>
                        <th width="60%" scope="col">제목</th>
                        <th width="15%" scope="col">작성자</th>
                        <th width="15%" scope="col">작성일</th>
                    </tr>
                    </thead>
                    <tbody class="text-center" id="board-list">
                        <!-- 게시글 목록이 나오는 곳 -->
                    </tbody>
                </table>
                <div class="mt-3 mb-2">
                    <nav class="d-flex justify-content-center">
                        <ul class="pagination pagination-sm" id="board-page-buttons">
                            <!-- 페이징 버튼 나오는 곳 -->
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script rel="script" src="/assets/project/member/js/mypage-board.js"></script>
</body>
</html>
