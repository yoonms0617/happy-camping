<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/happy/fragments/head.jsp" %>
<body>
<%@include file="/happy/fragments/header.jsp" %>
<div class="container">
    <div class="mt-5">
        <div class="mb-3">
            <span class="h1">
                공지사항
            </span>
        </div>
        <table class="table table-bordered">
            <tbody>
            <tr>
                <th width="20%" class="p-3 bg-light">제목</th>
                <td width="80%" class="p-3">${notice.title}</td>
            </tr>
            <tr>
                <th width="20%" class="p-3 bg-light">작성자</th>
                <td width="80%" class="p-3">${notice.writer}</td>
            </tr>
            <tr>
                <th width="20%" class="p-3 bg-light">작성일</th>
                <td width="80%" class="p-3">${notice.regDate}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="p-2 m-auto">
                        ${notice.content}
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="mt-2 d-flex justify-content-start">
            <div class="me-2">
                <a role="button" class="btn btn-secondary" href="/notice/list.do">목록</a>
            </div>
            <c:if test="${sessionScope.role == '관리자'}">
                <div class="me-2">
                    <a role="button" class="btn btn-primary" href="/notice/update.do?no=${notice.nno}">수정</a>
                </div>
                <div>
                    <c:set var="no" value="${notice.nno}"/>
                    <button role="button" class="btn btn-danger" id="notice-delete-btn">삭제</button>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script>
    $(function () {
        $('#notice-delete-btn').click(function () {
            var isConfirm = confirm('공지사항을 삭제하시겠습니까?');
            var no = ${no};
            if (isConfirm) {
                location.href="/noticeDelete.do?no=" + no;
            }
        });
    });
</script>
</body>
</html>