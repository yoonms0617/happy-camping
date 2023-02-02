<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/happy/fragments/head.jsp" %>
<body>
<div class="container">
    <div class="m-auto" style="max-width: 500px">
        <div class="text-center mt-5">
            <a class="text-decoration-none" href="/main.do">
                <img src="/assets/images/main/logo/happy-logo-2.jpeg" width="400">
            </a>
        </div>
        <form class="mt-3" id="login-form" method="post"> <!-- 로그인 -->
            <div class="form-floating">
                <input type="text" class="form-control" id="userid" name="userid" placeholder="아이디">
                <label for="userid">아이디</label>
            </div>
            <div class="form-floating mt-3">
                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호">
                <label for="password">비밀번호</label>
            </div>
            <div class="mt-3">
                <font size="4">
                    <span id="err-msg" class="text-danger"></span>
                </font>
            </div>
            <div class="mt-3">
                <button class="w-100 btn btn-lg btn-primary" id="login-btn" type="button">로그인</button>
            </div>
        </form>
        <hr>
        <div class="text-center">
            <span>아직 회원이 아니신가요?</span>
            <a href="/member/signup.do" class="text-decoration-none">
                회원가입 GO
            </a>
            <div class="mt-3">
                <a href="#" class="text-secondary text-decoration-underline">비밀번호 찾기</a>
            </div>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script rel="script" src="/assets/project/member/js/login.js"></script>
</body>
</html>