<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/happy/fragments/head.jsp" %>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.css"/>
<body>
<%@include file="/happy/fragments/header.jsp" %>
<div class="container">
    <div class="mt-1">
        <h1>공지사항 수정</h1>
        <div class="mt-4">
            <form>
                <c:set var="no" value="${notice.nno}"/>
                <c:set var="content" value="${notice.content}"/>
                <div class="input-group input-group-lg">
                    <span class="input-group-text">제목</span>
                    <input type="text" class="form-control" id="notice-title" value="${notice.title}">
                </div>
                <div class="mt-1">
                    <div id="content"></div>
                </div>
                <div class="mt-2 d-flex justify-content-end">
                    <div class="me-2">
                        <button type="button" class="btn btn-outline-primary" id="notice-update-btn">수정</button>
                    </div>
                    <div>
                        <a role="button" class="btn btn-outline-secondary"
                           href="/notice/detail.do?no=${notice.nno}">취소</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script src="https://uicdn.toast.com/editor/latest/i18n/ko-kr.min.js"></script>
<script>
    const Editor = toastui.Editor;
    const content = '${content}';
    const editor = new Editor({
        el: document.querySelector('#content'),
        language: 'ko-KR',
        initialEditType: 'wysiwyg',
        height: '500px',
        hideModeSwitch: true
    });
    editor.setHTML(content);
    $(function () {
        $('#notice-update-btn').click(function () {
            var title = $('#notice-title').val();
            if (title === '') {
                alert("제목을 입력해 주세요.")
                return false;
            }
            var content = editor.getHTML();
            if (content === '') {
                alert("내용을 입력해 주세요.");
                return false;
            }
            var no = ${no};
            $.ajax({
                type: 'POST',
                url: '/noticeUpdate.do?no=' + no,
                data: {
                    title: title,
                    content: content
                },
                success: function () {
                    location.href = '/notice/detail.do?no=' + no;
                }
            });
        });

    })
</script>
</body>
</html>