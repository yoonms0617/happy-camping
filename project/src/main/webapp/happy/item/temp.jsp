<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@include file="/happy/fragments/head.jsp" %>   



<%@include file="/happy/fragments/header.jsp" %>
<link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/album/">
<link rel="stylesheet" href="/assets/boostrap/css/bootstrap.min.css">
<body>
  <c:forEach var="vo" items="${list }" varStatus="s">
	<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-md-4 g-4">
        <div class="col">
          <div class="card shadow-sm">
            <a href="#"><img src="${vo.image }" title="${vo.name }" width="100%" height="225"></a>
            <div class="card-body">
              <a href="#">${vo.name }</a>
              <fmt:formatNumber value="${vo.price}" pattern="#,###"/>원
            </div>
          </div>
        </div>
    </div>
    
  </c:forEach>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
</body>

