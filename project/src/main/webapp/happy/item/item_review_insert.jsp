<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><!-------------------- 1 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--------------------  2-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  <!-------------------- 3 -->  


 
<%@include file="/happy/fragments/head.jsp" %>    <!--------------------4  -->
<%@include file="/happy/fragments/header.jsp" %> <!-------------------- 5 -->

<link rel="stylesheet" href="http://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css"><!--------------------  6-->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script><!-------------------- 7 -->


<body>
	<div class="wrapper row3" style="margin-left: 50px">
  <main class="container clear"> 
    <h2 class="sectiontitle">리뷰 작성</h2>
	<div style="height: 20px"></div>
	<form method="post" action=/happy/item/item_review_insert_ok.do">

	
	<table class="table">
	  <tr>
	    <th width=15% class="text-right">이름</th>
	    <td width=80%>
	    	<input type=text name=name size=20 class="input-sm">
	    </td>
	  </tr>
	  
	  <tr>
	    <th width=15% class="text-right">제목</th>
	    <td width=80%>
	    	<input type=text name=subject size=60 class="input-sm">
	    </td>
	  </tr>
	  
	  <tr>
	    <th width=15% class="text-right">내용</th>
	    <td width=80%>
	    	<textarea rows="10" cols="60" name="content"></textarea>
	    </td>
	  </tr>
	  
	   <tr>
	    <th width=15% class="text-right">비밀번호</th>
	    <td width=80%>
	    	<input type=password name=pwd size=15 class="input-sm">
	    </td>
	  </tr>
	  
	  <tr>
	    <td colspan="2" class="text-center">
	      <input type=submit value="글쓰기" class="btn btn-sm btn-danger">
	      <input type=button value="취소" class="btn btn-sm btn-danger" onclick = "javascript:history.back()">
	    </td>
	  </tr>
	
	</table>
	</form>
  </main>
 </div>


 <%@include file="/happy/fragments/footer.jsp" %> <!-------------------- 1 -->
<%@include file="/happy/fragments/common-script.jsp" %> <!-------------------- 2 -->


<script src="assets/project/item/vendors/jquery/jquery-3.2.1.min.js"></script>
  <script src="assets/project/item/vendors/bootstrap/bootstrap.bundle.min.js"></script>
  <script src="assets/project/item/vendors/skrollr.min.js"></script>
  <script src="assets/project/item/vendors/owl-carousel/owl.carousel.min.js"></script>
  <script src="assets/project/item/vendors/nice-select/jquery.nice-select.min.js"></script>
  <script src="assets/project/item/vendors/jquery.ajaxchimp.min.js"></script>
  <script src="assets/project/item/vendors/mail-script.js"></script>
  <script src="assets/project/item/js/main.js"></script>


<script src="https://code.jquery.com/jquery-3.6.0.js"></script> <!--------------------3 -->
  
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script> <!-------------------- 4-->




    
    
</body>
</html>