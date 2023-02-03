<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/happy/fragments/head.jsp" %>
<%@include file="/happy/fragments/header.jsp" %>
<body>
<div class="container">
    <div class="mt-5">
        <h1>${camp.name}</h1>
    </div>
    <hr class="border border-dark border-2">
    <div class="card mb-3">
        <div class="row g-0">
            <div class="col-md">
                <img src="${camp.image}" style="width: 100%; height: 400px;">
            </div>
            <div class="col-md">
                <div class="card-body">
                    <table class="table m-auto">
                        <tbody>
                        <tr>
                            <th class="p-3" scope="row">주소</th>
                            <td class="p-3" id="address">${camp.address}</td>
                        </tr>
                        <tr>
                            <th class="p-3" scope="row">문의처</th>
                            <td class="p-3">${camp.tel}</td>
                        </tr>
                        <tr>
                            <th class="p-3" scope="row">캠핑장 환경</th>
                            <td class="p-3">${camp.campEnv}</td>
                        </tr>
                        <tr>
                            <th class="p-3" scope="row">캠핑장 유형</th>
                            <td class="p-3">${camp.campType}</td>
                        </tr>
                        <tr>
                            <th class="p-3" scope="row">운영기간</th>
                            <td class="p-3">${camp.period}</td>
                        </tr>
                        <tr>
                            <th class="p-3" scope="row">운영일</th>
                            <td class="p-3">${camp.day}</td>
                        </tr>
                        <tr>
                            <th class="p-3" scope="row">홈페이지</th>
                            <td class="p-3">
                                <c:if test="${camp.homePage != null}">
                                    <a href="${camp.homePage}" target="_blank">
                                        홈페이지 바로가기
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div>
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane"
                        type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">
                    위치정보
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane"
                        type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">
                    캠핑후기
                </button>
            </li>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab"
                 tabindex="0">
                <div id="map" style="width:100%;height:550px;"></div>
            </div>
            <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
                <div class="mt-3">
                    <ul class="list-group list-group-flush" id="review-list">
                        <div class="d-flex justify-content-end">
                        </div>
                    </ul>
                </div>
                <div class="mt-1 mb-2">
                    <nav class="d-flex justify-content-center">
                        <ul class="pagination pagination-sm" id="page-buttons"></ul>
                    </nav>
                </div>
                <div class="mt-3">
                    <div class="form-floating">
                        <textarea id="review-form" onkeydown="resize(this)" class="form-control"
                                  onkeyup="resize(this)" style="width: 100%; min-height: 5rem; overflow-y: hidden; resize: none;"></textarea>
                        <label for="review-form">댓글을 남겨보세요</label>
                        <div class="mt-1 d-flex justify-content-end">
                            <button class="btn btn-sm btn-primary" id="review-write" onclick="writeReview('${sessionScope.mid}')">등록</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script rel="script" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a610b7237df91c7b85cb6a328aacca2e&libraries=services"></script>
<script rel="script" src="/assets/project/camp/js/camp_location.js"></script>
<script rel="script" src="/assets/project/camp/js/camp_review.js"></script>
</body>
</html>