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

    th {
        vertical-align: middle;
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
                            <a class="small text-underline-hover text-primary active" href="/mypage/info.do">개인정보확인/수정</a>
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
                <h3>개인정보확인/수정</h3>
            </div>
            <div class="p-5">
                <table class="table table-bordered">
                    <tbody>
                    <tr>
                        <th width="150px;" class="p-4">이름</th>
                        <td class="p-4">${member.name}</td>
                    </tr>
                    <tr>
                        <th class="p-4">아이디</th>
                        <td class="p-4">${member.mid}</td>
                    </tr>
                    <tr>
                        <th class="p-4">이메일</th>
                        <td class="p-4">${member.email}</td>
                    </tr>
                    <tr>
                        <th class="p-4">전화번호</th>
                        <td class="p-4">${member.tel}</td>
                    </tr>
                    <tr>
                        <th class="p-4">비밀번호변경</th>
                        <td class="p-4">
                            <div class="row g-3 align-items-center">
                                <div class="col-2">
                                    <label for="oldPwd" class="col-form-label">현재 비밀번호</label>
                                </div>
                                <div class="col-auto">
                                    <input type="password" id="oldPwd" class="form-control">
                                </div>
                                <span>
                                    <small id="oldPwd-msg"></small>
                                </span>
                            </div>
                            <div class="row g-3 mt-1 align-items-center">
                                <div class="col-2">
                                    <label for="newPwd" class="col-form-label">새 비밀번호</label>
                                </div>
                                <div class="col-auto">
                                    <input type="password" id="newPwd" class="form-control">
                                </div>
                                <span>
                                    <small id="newPwd1-msg"></small>
                                </span>
                            </div>
                            <div class="row g-3 mt-1 align-items-center">
                                <div class="col-2">
                                    <label for="newPwdCheck" class="col-form-label">비밀번호 확인</label>
                                </div>
                                <div class="col-auto">
                                    <input type="password" id="newPwdCheck" class="form-control">
                                </div>
                                <span>
                                    <small id="newPwd2-msg"></small>
                                </span>
                            </div>
                            <div class="mt-2">
                                <button type="button" class="btn btn-sm btn-secondary" id="pwd-change-btn"
                                        style="width: 130px;">비밀번호 변경
                                </button>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="mt-1 d-flex justify-content-end align-items-center">
                    <span class="m-0 me-1">
                        <small class="text-muted">탈퇴를 원하시면 우측의 회원탈퇴 버튼을 눌러주세요.</small>
                    </span>
                    <button role="button" class="btn btn-sm btn-secondary" id="account-delete-btn">회원탈퇴</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script rel="script" src="/assets/project/member/js/mypage.js"></script>
</body>
</html>