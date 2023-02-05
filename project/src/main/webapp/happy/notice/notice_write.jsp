<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/happy/fragments/head.jsp" %>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.css"/>
<body>
<%@include file="/happy/fragments/header.jsp" %>
<div class="container">
    <div class="mt-4">
        <form>
            <div class="input-group input-group-lg">
                <span class="input-group-text">제목</span>
                <input type="text" class="form-control" id="notice-title" placeholder="제목을 입력해 주세요.">
            </div>
            <div class="mt-1">
                <div id="content"></div>
            </div>
            <div class="mt-2 d-flex justify-content-end">
                <div class="me-2">
                    <button type="button" class="btn btn-outline-primary" id="notice-write-btn">작성</button>
                </div>
                <div>
                    <a role="button" class="btn btn-outline-secondary" href="/notice/list.do">목록으로</a>
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="/happy/fragments/footer.jsp" %>
<%@include file="/happy/fragments/common-script.jsp" %>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script src="https://uicdn.toast.com/editor/latest/i18n/ko-kr.min.js"></script>
<script>
    const Editor = toastui.Editor;
    const editor = new Editor({
        el: document.querySelector('#content'),
        language: 'ko-KR',
        initialEditType: 'wysiwyg',
        height: '500px',
        hideModeSwitch: true
    });
    $(function () {
        $('#notice-write-btn').click(function () {
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
            $.ajax({
                type: 'POST',
                url: '/noticeWrite.do',
                data: {
                    title: title,
                    content: content
                },
                success: function () {
                    location.href = '/notice/list.do'
                }
            });
        });
    })
</script>
</body>
</html>