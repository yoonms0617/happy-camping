<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/happy/fragments/head.jsp" flush="false"/>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.css"/>
<body>
<jsp:include page="/happy/fragments/header.jsp" flush="false"/>
<div class="container">
    <div class="mt-4">
        <form>
            <div class="input-group input-group-lg">
                <span class="input-group-text">제목</span>
                <input type="text" class="form-control" id="board-title" placeholder="제목을 입력해 주세요.">
            </div>
            <div class="mt-1">
                <div id="content"></div>
            </div>
            <div class="mt-2 d-flex justify-content-end">
                <div class="me-2">
                    <button type="button" class="btn btn-outline-primary" id="board-write-btn">작성</button>
                </div>
                <div>
                    <a role="button" class="btn btn-outline-secondary" href="/board/list.do">목록으로</a>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/happy/fragments/footer.jsp" flush="false"/>
<jsp:include page="/happy/fragments/common-script.jsp" flush="false"/>
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
        $('#board-write-btn').click(function () {
            var title = $('#board-title').val();
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
                url: '/boardWrite.do',
                data: {
                    title: title,
                    content: content
                },
                success: function () {
                    location.href = '/board/list.do'
                }
            });
        });
    })
</script>
</body>
</html>