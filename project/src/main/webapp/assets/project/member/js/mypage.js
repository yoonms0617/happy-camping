$(function () {
    $('#pwd-change-btn').click(function () {
        changePassword();
    });

    $('#addr-change-btn').click(function () {
        changeAddress();
    });

    $('#account-delete-btn').click(function () {
        deleteAccount();
    })
});

// 비밀번호 변경
function changePassword() {
    var oldPassword = $('#oldPwd').val();
    var newPassword1 = $('#newPwd').val();
    var newPassword2 = $('#newPwdCheck').val();
    var oldOMsg = $('#oldPwd-msg');
    var newOMsg1 = $('#newPwd1-msg');
    var newOMsg2 = $('#newPwd2-msg');
    if (oldPassword === '') {
        showErrorMsg(oldOMsg, '현재 사용 중인 비밀번호를 입력해 주세요.');
        return false;
    }
    hideMsg(oldOMsg)
    if (newPassword1 === '') {
        showErrorMsg(newOMsg1, '변경하려는 비밀번호를 입력해 주세요.');
        return false;
    }
    var regexp = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,16}$/;
    if (!regexp.test(newPassword1)) {
        showErrorMsg(newOMsg1, '8~16자 영문 대소문자, 숫자, 특수문자를 사용하세요.');
        return false;
    }
    hideMsg(newOMsg1)
    if (newPassword2 === '') {
        showErrorMsg(newOMsg2, '변경하려는 비밀번호를 다시 입력해 주세요.');
        return false;
    }
    hideMsg(newOMsg2)
    if (newPassword1 !== newPassword2) {
        showErrorMsg(newOMsg2, '비밀번호가 일치하지 않습니다.');
        return false;
    }
    hideMsg(newOMsg2)
    $.ajax({
        type: 'post',
        url: '/changePassword.do',
        data: {
            oldPassword: oldPassword,
            newPassword: newPassword1
        },
        success: function (result) {
            if (result === 'NN') {
                alert("현재 사용 중인 비밀번호와 일치하지 않습니다.");
                return false;
            }
            if (result === 'NNN') {
                alert("변경하려는 비밀번호가 사용 중인 비밀번호와 같습니다.");
                return false;
            }
            if (result === 'YY') {
                alert("비밀번호가 변경되었습니다. 다시 로그인해 주세요.");
                location.href = '/member/logout.do';
            }
        }
    });
}

function deleteAccount() {
    var isDelete = confirm('정말로 탈퇴하시겠습니까?');
    if (isDelete) {
        $.ajax({
            type: 'post',
            url: '/out.do',
            success: function () {
                alert("탈퇴되었습니다.");
                location.href = '/member/logout.do';
            }
        })
    }
}

// 주소 변경
function changeAddress() {
    var postCode = $('#postcode').val();
    var homeAddress = $('#homeAddress').val();
    var detailAddress = $('#detailAddress').val();
    $.ajax({
        type: 'post',
        url: '/changeAddress.do',
        data: {
            postCode: postCode,
            homeAddress: homeAddress,
            detailAddress: detailAddress
        },
        success: function () {
            alert("주소가 변경되었습니다.")
            location.reload();
        }
    })
}

function showErrorMsg(oMsg, message) {
    oMsg.attr('class', 'text-danger');
    oMsg.html(message);
    oMsg.show();
}

function hideMsg(oMsg) {
    oMsg.hide();
}