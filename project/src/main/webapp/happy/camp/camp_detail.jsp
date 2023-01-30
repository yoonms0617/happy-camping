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

            </div>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a610b7237df91c7b85cb6a328aacca2e&libraries=services"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
    // 지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);
    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();
    var address = $('#address').text();
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(address, function (result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });
            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }
    });
</script>
</body>
</html>