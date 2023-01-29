var submitFlag = false;

$(function () {
    focusUserId();
    $('#login-btn').click(function () {
        const userid = $('#userid').val();
        const password = $('#password').val();
        if (userid === '') {
            $('#err-msg').text('아이디를 입력해 주세요.');
            focusUserId();
            return false;
        }
        if (password === '') {
            $('#err-msg').text('비밀번호를 입력해 주세요.');
            focusPassword();
            return false;
        }
        checkUserIdAndPassword(userid, password);
        if (submitFlag) {
            $('#login-form').submit();
        }
    });
});

function focusUserId() {
    $('#userid').focus();
}

function focusPassword() {
    $('#password').focus();
}

function checkUserIdAndPassword(userid, password) {
    if (userid !== '' && password !== '') {
        submitFlag = true;
    }
}