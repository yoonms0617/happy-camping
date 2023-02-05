<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/happy/fragments/head.jsp" %>
<link rel="stylesheet" href="/assets/project/member/css/signup.css"/>
<body>
<div class="container">
    <div class="m-auto" style="max-width: 400px;">
        <div class="text-center mt-2">
            <a class="text-decoration-none" href="/main.do">
                <img src="/assets/images/main/logo/happy-logo-2.jpeg" width="300">
            </a>
        </div>
        <form id="signup-form" method="post" action="/signup.do">
            <div>
                <label for="name">이름</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="name" name="name">
                </div>
                <small>
                    <span id="nameMsg"></span>
                </small>
            </div>
            <div class="mt-3">
                <label for="userid">아이디</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="userId" name="userId">
                    <button class="btn btn-outline-primary" type="button" id="checkIdBtn">중복확인</button>
                </div>
                <small>
                    <span id="userIdMsg"></span>
                </small>
            </div>
            <div class="mt-3">
                <label for="password1">비밀번호</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="password1" name="password">
                </div>
                <small>
                    <span id="pwd1Msg"></span>
                </small>
            </div>
            <div class="mt-3">
                <label for="password2">비밀번호 확인</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="password2">
                </div>
                <small>
                    <span id="pwd2Msg"></span>
                </small>
            </div>
            <div class="mt-3">
                <label for="sex">성별</label>
                <div class="input-group">
                    <select class="form-select" id="sex" name="sex">
                        <option selected>성별</option>
                        <option value="남자">남자</option>
                        <option value="여자">여자</option>
                    </select>
                </div>
                <small>
                    <span id="genderMsg"></span>
                </small>
            </div>
            <div class="mt-3">
                <label for="birth">생년월일</label>
                <div>
                    <input type="hidden" id="birth" name="birth">
                    <input type="hidden" id="age" name="age">
                    <div class="d-flex justify-content-around">
                        <div class="me-3" style="width: 120px">
                            <input type="text" maxlength="4" class="form-control" id="yy" name="yy" placeholder="년(4자)">
                            
                        </div>
                        <div class="me-3" style="width: 120px;">
                            <select class="form-select" id="mm" name="mm" style="height: 45px;">
                                <option selected>월</option>
                                <option value="01">1</option>
                                <option value="02">2</option>
                                <option value="03">3</option>
                                <option value="04">4</option>
                                <option value="05">5</option>
                                <option value="06">6</option>
                                <option value="07">7</option>
                                <option value="08">8</option>
                                <option value="09">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                            </select>
                        </div>
                        <div style="width: 120px">
                            <input type="text" class="form-control" id="dd" name="dd" placeholder="일">
                        </div>
                    </div>
                </div>
                <small>
                    <span id="birthMsg"></span>
                </small>
            </div>
            <div class="mt-3">
                <label for="phone">전화번호</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="phone" name="phone" maxlength="13" oninput="autoHyphen(this)">
                </div>
                <small>
                    <span id="phoneMsg"></span>
                </small>
            </div>
            <div class="mt-3">
                <label for="email">이메일</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="email" name="email">
                </div>
                <small>
                    <span id="emailMsg"></span>
                </small>
            </div>
            <div class="mt-3">
                <div class="input-group">
                    <input type="text" class="form-control" id="postcode" name="postcode" placeholder="우편번호">
                    <input type="button" class="btn btn-lg btn-outline-primary" onclick="daumAddressAPI()"
                           value="우편번호 찾기"><br>
                </div>
                <div class="mt-1">
                    <input type="text" class="form-control" id="homeAddress" name="homeAddress" placeholder="주소">
                </div>
                <div class="mt-1">
                    <input type="text" class="form-control" id="detailAddress" name="detailAddress" placeholder="상세주소">
                </div>
                <small>
                    <span id="addrMsg"></span>
                </small>
            </div>
            <div class="mt-3">
                <button type="button" id="signup-btn" class="w-100 btn btn-lg btn-primary">가입하기</button>
            </div>
        </form>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script rel="script" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script rel="script" src="/assets/project/member/js/address.js"></script>
<script rel="script" src="/assets/project/member/js/signup.js"></script>
</body>
</html>
