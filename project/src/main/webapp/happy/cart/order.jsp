<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/happy/fragments/head.jsp"/>
<body>
<jsp:include page="/happy/fragments/header.jsp"></jsp:include>
<div class="container">
    <div class="mt-5">
        <h3>주문/결제</h3>
        <hr>
        <div class="mt-3">
            <div>
                <span class="fs-5">구매자 정보</span>
            </div>
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <th width="150px;" class="p-4">이름</th>
                    <td class="p-4" id="name">${sessionScope.name}</td>
                </tr>
                <tr>
                    <th class="p-4">이메일</th>
                    <td class="p-4" id="email">${sessionScope.email}</td>
                </tr>
                <tr>
                    <th class="p-4">휴대폰 번호</th>
                    <td class="p-4" id="tel">${sessionScope.tel}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="mt-3">
            <div>
                <span class="fs-5">배송지 정보</span>
            </div>
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <th width="150px;" class="p-4">우편번호</th>
                    <td class="p-4" id="postcode">${sessionScope.postcode}</td>
                </tr>
                <tr>
                    <th class="p-4">배송주소</th>
                    <td class="p-4" id="homeAddr">${sessionScope.homeAddr}</td>
                </tr>
                <tr>
                    <th class="p-4">상세주소</th>
                    <td class="p-4" id="detailAddr">${sessionScope.detailAddr}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="mt-3">
            <div class="d-flex justify-content-start">
                <div class="me-3">
                    <span class="fs-5">받는사람정보</span>
                </div>
                <div class="d-flex justify-content-end align-baseline">
                    <div class="me-2">
                        <small class="text-muted" style="font-size: 13px">구매자 정보와 동일합니다.</small>
                    </div>
                    <div>
                        <input type="checkbox" id="info-check" class="form-check">
                    </div>
                </div>
            </div>
        </div>
        <table class="table">
            <tr>
                <th width="150px;" class="p-4">
                    이름
                </th>
                <td class="p-3">
                    <input type="text" class="form-control" id="d-name" name="name" style="width: 400px;">
                </td>
            </tr>
            <tr>
                <th class="p-4">주소</th>
                <td class="p-3">
                    <div class="input-group" style="width: 400px;">
                        <input type="text" class="form-control" id="d-postcode" name="postcode" placeholder="우편번호">
                        <input type="button" class="btn btn-lg btn-outline-primary" onclick="daumAddressAPI()"
                               value="우편번호 찾기"><br>
                    </div>
                    <div class="mt-1" style="width: 400px;">
                        <input type="text" class="form-control" id="d-homeAddress" name="homeAddress" placeholder="주소">
                    </div>
                    <div class="mt-1" style="width: 400px;">
                        <input type="text" class="form-control" id="d-detailAddress" name="detailAddress" placeholder="상세주소">
                    </div>
                </td>
            </tr>
            <tr>
                <th class="p-4">휴대전화</th>
                <td class="p-3">
                    <input type="text" class="form-control" id="d-tel" name="tel" maxlength="13"
                           oninput="autoHyphen(this)" style="width: 400px;">
                </td>
            </tr>
            <tr>
                <th class="p-4">이메일</th>
                <td class="p-3">
                    <input type="text" class="form-control" id="d-email" name="email" style="width: 400px;">
                </td>
            </tr>
        </table>
    </div>
    <div class="mt-5 mb-5">
        <div class="mb-3">
            <span class="fs-5">상품 정보</span>
        </div>
        <table class="table table-hover">
            <thead>
            <tr class="text-center">
                <th width="60%">상품정보</th>
                <th width="15%">상품금액</th>
                <th width="15%">배송비</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    <div class="p-3">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex justify-content-start align-items-center">
                                <div class="me-2">
                                    <img id="item-image" src="${item.image}" width="100px;">
                                </div>
                                <div class="m-0 d-inline-block text-truncate" style="max-width: 400px;">
                                    <span class="fs-6" id="item-name">
                                        ${item.name}
                                    </span>
                                </div>
                            </div>
                            <div class="d-flex justify-content-end align-items-center">
                                <div class="me-2">
                                    <small class="text-muted">
                                        <fmt:formatNumber value="${item.price != 0 ? item.price : 0}"
                                                          pattern="#,###"/>원 | ${item.quantity} 개
                                    </small>
                                </div>
                            </div>
                        </div>
                    </div>
                </th>
                <td class="text-center">
                    <div class="py-5 border-start">
                        <fmt:formatNumber value="${(item.price != 0 ? item.price : 0) * item.quantity}"
                                          pattern="#,###"/>원
                    </div>
                </td>
                <td class="text-center">
                    <div class="p-5 border-start">
                        <p class="m-auto">${(item.price * item.quantity) >= 50000 ? 0 : 3000}</p>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="mt-3">
        <div>
            <span class="fs-5">결제 정보</span>
        </div>
        <table class="table table-bordered">
            <tbody>
            <tr>
                <th width="150px;" class="p-3">총상품가격</th>
                <td class="p-3">
                    <fmt:formatNumber value="${(item.price != 0 ? item.price : 0) * item.quantity}"
                                      pattern="#,###"/>원
                </td>
            </tr>
            <tr>
                <th class="p-3">배송비</th>
                <td class="p-3">
                    ${(item.price * item.quantity) >= 50000 ? 0 : 3000}
                </td>
            </tr>
            <tr>
                <th class="p-3">총결제금액</th>
                <td class="p-3" id="total-price">
                    <fmt:formatNumber
                            value="${((item.price * item.quantity) >= 50000 ? 0 : 3000) + (item.price != 0 ? item.price : 0) * item.quantity}"
                            pattern="#,###"/>원
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
        <input type="hidden" id="ino" value="${item.ino}">
        <input type="hidden" id="item-quantity" value="${item.quantity}">
        <input type="hidden" id="item-price" value="${((item.price * item.quantity) >= 50000 ? 0 : 3000) + (item.price != 0 ? item.price : 0) * item.quantity}">
    </div>
    <div class="mt-5">
        <div class="d-flex justify-content-center">
            <div class="me-3">
                <button type="button" class="btn btn-lg btn-outline-danger" style="width: 200px"
                        onclick="location.href='/main.do'">취소
                </button>
            </div>
            <div>
                <button type="button" class="btn btn-lg btn-outline-primary" id="order-btn" style="width: 200px">결제하기
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/happy/fragments/footer.jsp"></jsp:include>
<jsp:include page="/happy/fragments/common-script.jsp"></jsp:include>
<script rel="script" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script rel="script" src="/assets/project/member/js/address.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script>
    $(function () {
        $('#info-check').change(function () {
            var isChecked = $('#info-check').is(':checked');
            if (isChecked) {
                var name = $('#name').text();
                var email = $('#email').text();
                var tel = $('#tel').text();
                var postCode = $('#postcode').text();
                var homeAddr = $('#homeAddr').text();
                var detailAddr = $('#detailAddr').text();
                $('#d-name').val(name);
                $('#d-email').val(email);
                $('#d-tel').val(tel);
                $('#d-postcode').val(postCode);
                $('#d-homeAddress').val(homeAddr);
                $('#d-detailAddress').val(detailAddr);
            } else {
                $('#d-name').val('');
                $('#d-email').val('');
                $('#d-tel').val('');
                $('#d-postcode').val('');
                $('#d-homeAddress').val('');
                $('#d-detailAddress').val('');
            }
        });

        $('#order-btn').click(function () {
            requestPay();
        });

    });
    var IMP = window.IMP;
    IMP.init("imp67011510");

    var today = new Date();
    var hours = today.getHours(); // 시
    var minutes = today.getMinutes();  // 분
    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours + minutes + seconds + milliseconds;
    function requestPay() {
        var name = $('#item-name').text();
        var image = $('#item-image').attr('src');
        var ino = $('#ino').val();
        var cno = getCno();
        var price = $('#item-price').val();
        var quantity = $('#item-quantity').val()
        var amount = $('#total-price').text();
        var bEmail = $('#d-email').val();
        var bName = $('#d-name').val();
        var bTel = $('#d-tel').val();
        var bPostCode = $('#d-postcode').val();
        var homeAddr = $('#d-homeAddress').val();
        var detailAddr = $('#d-detailAddress').val();
        var addr = homeAddr + detailAddr;
        if (bName === '' || bEmail === '' || bTel === '' || bPostCode === '' || homeAddr === '' || detailAddr === '') {
            alert("받는사람 정보를 입력해 주세요.")
            return false;
        }
        IMP.request_pay({
            pg: 'kcp',
            pay_method: 'card',
            merchant_uid: "IMP" + makeMerchantUid,
            name: name,
            amount: amount,
            buyer_email: bEmail,
            buyer_name: bName,
            buyer_tel: bTel,
            buyer_addr: addr,
            buyer_postcode: bPostCode,
            display: {
                card_quota: [3]  // 할부개월 3개월까지 활성화
            }
        }, function (rsp) { // callback
            if (rsp.success) {
                console.log("주문완료")
            } else {
                console.log("주문취소")
                $.ajax({
                    type: 'post',
                    url: '/order.do',
                    data: {
                        name: name,
                        image: image,
                        ino: ino,
                        quantity: quantity,
                        price: price,
                        cno: cno
                    },
                    success: function () {
                        alert("주문을 완료했습니다.");
                        location.href = '/mypage.do'
                    }
                })
            }
        });

        function getCno() {
            let params = new URLSearchParams(location.search);
            return params.get('cno');
        }
    }
</script>
</body>
</html>