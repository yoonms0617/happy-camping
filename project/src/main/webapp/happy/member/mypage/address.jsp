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
                            <a class="small text-dark text-underline-hover" href="/mypage/post.do">게시글 관리</a>
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
                            <a class="small text-underline-hover text-dark" href="/mypage/info.do">개인정보확인/수정</a>
                        </div>
                        <div>
                            <a class="small text-underline-hover text-primary active" href="/mypage/address.do">배송지 관리</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col p-0">
            <div class="px-5">
                <h3>배송지 관리</h3>
            </div>
            <div class="p-5" style="max-width: 800px;">
                <div class="input-group">
                    <input type="text" class="form-control" id="postcode" name="postcode" placeholder="우편번호" value="${address.postcode}">
                    <input type="button" class="btn btn-lg btn-outline-primary" onclick="daumAddressAPI()" value="우편번호 찾기"><br>
                </div>
                <div class="mt-1">
                    <input type="text" class="form-control" id="homeAddress" name="homeAddress" placeholder="주소" value="${address.homeAddr}">
                </div>
                <div class="mt-1">
                    <input type="text" class="form-control" id="detailAddress" name="detailAddress" placeholder="상세주소" value="${address.detailAddr}">
                </div>
                <div class="mt-3">
                    <button type="button" class="btn btn-lg btn-primary border-0" id="addr-change-btn">수정하기</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script rel="script" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script rel="script" src="/assets/project/member/js/address.js"></script>
<script rel="script" src="/assets/project/member/js/mypage.js"></script>
</body>
</html>
