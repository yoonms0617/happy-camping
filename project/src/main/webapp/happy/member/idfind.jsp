<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/happy/fragments/head.jsp" flush="false"/>
<!--  uncaught referenceerror $ is not defined 오류로 인한 추가 -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!-- tabs관련 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<body>
<div class="container">
  <div class="m-auto" style="max-width: 500px">
    <div class="text-center mt-5">
      <a class="text-decoration-none" href="/main.do">
         <img src="/assets/images/main/logo/happy-logo-2.jpeg" width="400">
      </a>
      <h3>아이디 찾기</h3>
    </div>
    <div id="tabs">
	  <ul>
	    <li><a href="#tabs-1">전화번호로 찾기</a></li>
	    <li><a href="#tabs-2">이메일로 찾기</a></li> 
	  </ul>
	  
	  <div id="tabs-1" >
	    <form class="mt-3" id="idfind-form" method="post"> 
         <div class="form-floating mt-3">
             <input type="text" class="form-control" id="pname" name="pname" placeholder="이름">
             <label for="pname">이름</label>
          </div>
          <div class="form-floating mt-3">
             <input type="text" class="form-control" id="tel" name="tel" placeholder="전화번호">
             <label for="tel">전화번호</label>
          </div>
          <div class="mt-3">
                <font size="4">
                    <b><span id="err-msg1" class="text-danger"></span></b>
                    <b><span id="suc-msg1" class="text-success"></span></b>
                </font>
            </div>
            <div class="mt-3">
                 <button class="w-100 btn btn-lg btn-primary" id="idfind-btn1" type="button">검색</button>
            </div>
        </form> 
	  </div>
	  
	  	  <div id="tabs-2" >
	    <form class="mt-3" id="idfind-form" method="post"> 
         <div class="form-floating mt-3">
             <input type="text" class="form-control" id="ename" name="ename" placeholder="이름">
             <label for="ename">이름</label>
          </div>
          <div class="form-floating mt-3">
             <input type="text" class="form-control" id="email" name="email" placeholder="이메일">
             <label for="email">이메일</label>
          </div>
          <div class="mt-3">
                <font size="4">
                    <b><span id="err-msg2" class="text-danger"></span></b>
                    <b><span id="suc-msg2" class="text-success"></span></b>
                </font>
            </div>
            <div class="mt-3">
                 <button class="w-100 btn btn-lg btn-primary" id="idfind-btn2" type="button">검색</button>
            </div>
        </form> 
	  </div>
 </div>
<hr>
  <div class="text-center">
     <div class="mt-3">
         <a href="#" class="text-secondary text-decoration-underline">비밀번호 찾기</a> 
     </div>
  </div>
    </div>
</div>


<jsp:include page="/happy/fragments/footer.jsp" flush="false"/>
<jsp:include page="/happy/fragments/common-script.jsp" flush="false"/>
<!-- tabs관련 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script rel="script" src="/assets/project/member/js/idfind.js"></script>
</body>
</html>