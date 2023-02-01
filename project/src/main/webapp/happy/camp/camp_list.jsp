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
<%@include file="/happy/fragments/header.jsp" %>
<body>
<div class="container" style="max-width: 1100px;">
    <div class="mt-5">
        <div>
            <h1>캠핑장 목록</h1>
        </div>
    </div>
    <div class="mb-1">
        <c:forEach var="camp" items="${page.items}">
            <div class="col">
                <div class="row g-0 border rounded overflow-hidden flex-md-row mb-2 shadow-sm h-md-250 position-relative">
                    <div class="col-auto d-none d-lg-block">
                        <a href="camp_detail.do?cno=${camp.cno}" class="text-decoration-none">
                            <img src="${camp.image}" width="300" height="250">
                        </a>
                    </div>
                    <div class="col p-4 d-flex flex-column position-static">
                        <div class="mb-2">
                            <strong class="d-inline-block me-3 text-danger">리뷰수 0</strong>
                            <strong class="d-inline-block text-dark">조회수 ${camp.hit}</strong>
                        </div>
                        <div class="mt-5">
                            <p>
                                <a href="camp_detail.do?cno=${camp.cno}" class="text-underline-hover text-dark">
                                    <span class="mb-4 h2">${camp.name}</span>
                                </a>
                            </p>
                            <div class="row">
                                <div class="col-auto me-3">
                                    <c:if test="${camp.address != null}">
                                        <div class="fs-6">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pin-map" viewBox="0 0 16 16">
                                                <path fill-rule="evenodd" d="M3.1 11.2a.5.5 0 0 1 .4-.2H6a.5.5 0 0 1 0 1H3.75L1.5 15h13l-2.25-3H10a.5.5 0 0 1 0-1h2.5a.5.5 0 0 1 .4.2l3 4a.5.5 0 0 1-.4.8H.5a.5.5 0 0 1-.4-.8l3-4z"/>
                                                <path fill-rule="evenodd" d="M8 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zM4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999z"/>
                                            </svg>
                                            <span>${camp.address}</span>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="col-auto">
                                    <c:if test="${camp.tel != null}">
                                        <div class="fs-6">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-telephone" viewBox="0 0 16 16">
                                                <path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/>
                                            </svg>
                                            <span>${camp.tel}</span>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="mt-5">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <a class="page-link <c:if test="${page.first || page.curPage - 10 <= 0}">disabled invisible</c:if>"
                           href="camp_list.do?page=${page.curPage - 10}">&laquo;</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link <c:if test="${page.first}">disabled invisible</c:if>"
                           href="camp_list.do?page=${page.curPage - 1}">◀︎</a>
                    </li>
                    <c:forEach var="pageNumber" begin="${page.startPage}" end="${page.endPage}">
                        <li class="page-item <c:if test="${pageNumber == page.curPage}">active</c:if>">
                            <a class="page-link" href="camp_list.do?page=${pageNumber}">${pageNumber}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link <c:if test="${page.last}">disabled invisible</c:if>"
                           href="camp_list.do?page=${page.curPage + 1}">▶︎</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link <c:if test="${page.last || (page.curPage + 10) > page.totalPage}">disabled invisible</c:if>"
                           href="camp_list.do?page=${page.curPage + 10}">&raquo;</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
</body>
</html>
