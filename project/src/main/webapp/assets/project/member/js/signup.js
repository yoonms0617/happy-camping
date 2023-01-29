var nameFlag = false;
var userIdFlag = false;
var userIdCheckFlag = false;
var passwordFlag = false;
var emailFlag = false;
var birthFlag = false;
var phoneFlag = false;
var genderFlag = false;
var addressFlag = false;
var submitFlag = false;

$(function () {
    $('#name').blur(function () {
        nameFlag = false;
        checkName();
    });

    $('#userId').blur(function () {
        userIdFlag = false;
        checkUserId();
    });

    $('#check-duplicate-userId').click(function () {
        if (!checkUserId()) {
            return false;
        }
        var userId = $('#userId').val();
        var oMsg = $('#userIdMsg');
        if (userId === '') {
            showErrorMsg(oMsg, '아이디를 입력해 주세요.');
            return false;
        }
        $.ajax({
            type: 'GET',
            url: '/check/userid.do?userId=' + userId,
            dataType: 'text',
            success: function (result) {
                if (result === 'true') {
                    showErrorMsg(oMsg, '사용 중인 아이디입니다.');
                    return false;
                } else {
                    showSuccessMsg(oMsg, '사용 가능한 아이디입니다.');
                    userIdCheckFlag = true;
                }
            }
        });
    });

    $('#password1').blur(function () {
        passwordFlag = false;
        checkPassword1();
    });

    $('#password2').blur(function () {
        passwordFlag = false;
        checkPassword2();
    });

    $('#sex').change(function () {
        genderFlag = false;
        checkGender();
    });

    $('#yy').blur(function () {
        birthFlag = false;
        checkBirth();
    });

    $('#mm').change(function () {
        birthFlag = false;
        checkBirth();
    });

    $('#dd').blur(function () {
        birthFlag = false;
        checkBirth();
    });

    $('#email').blur(function () {
        emailFlag = false;
        checkEmail();
    });

    $('#phone').blur(function () {
        phoneFlag = false;
        checkPhone();
    });

    $('#postcode').blur(function () {
        addressFlag = false;
        checkAddress();
    });

    $('#homeAddress').blur(function () {
        addressFlag = false;
        checkAddress();
    });

    $('#signup-btn').click(function () {
        checkInputValue();
        if (submitFlag) {
            $('#signup-form').submit();
        }
    });
});

function checkName() {
    if (nameFlag) {
        return true;
    }
    var regexp = /[^가-힣a-zA-Z0-9]/gi;
    var name = $('#name').val();
    var oMsg = $('#nameMsg');
    if (name === '') {
        showErrorMsg(oMsg, '필수 정보입니다.');
        return false;
    }
    if (name !== '' && regexp.test(name)) {
        showErrorMsg(oMsg, '한글과 영문 대소문자를 사용하세요. (특수기호, 공백 사용 불가)');
        return false;
    }
    showSuccessMsg(oMsg, '멋진 이름이네요!');
    nameFlag = true;
    return true;
}

function checkUserId() {
    if (userIdFlag) {
        return true;
    }
    var userId = $('#userId').val();
    var oMsg = $('#userIdMsg');
    var regexp = /^[a-z0-9][a-z0-9_\-]{4,19}$/
    if (userId === '') {
        showErrorMsg(oMsg, '필수 정보입니다.');
        return false;
    }
    if (!regexp.test(userId)) {
        showErrorMsg(oMsg, '5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.');
        return false;
    }
    userIdFlag = true;
    hideMsg(oMsg);
    return true;
}

function checkPassword1() {
    var password1 = $('#password1').val();
    var oMsg = $('#pwd1Msg');
    var regexp = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,16}$/;
    if (password1 === '') {
        showErrorMsg(oMsg, '필수 정보입니다.');
        return false;
    }
    if (!regexp.test(password1)) {
        showErrorMsg(oMsg, '8~16자 영문 대소문자, 숫자, 특수문자를 사용하세요.');
        return false;
    }
    showSuccessMsg(oMsg, '사용 가능한 비밀번호입니다.');
    return true;
}

function checkPassword2() {
    if (passwordFlag) {
        return true;
    }
    var password1 = $('#password1').val();
    var password2 = $('#password2').val();
    var oMsg = $('#pwd2Msg');
    if (password2 === '') {
        showErrorMsg(oMsg, '필수 정보입니다.');
        return false;
    }
    if (password1 !== password2) {
        showErrorMsg(oMsg, '비밀번호가 일치하지 않습니다.')
        return false;
    }
    showSuccessMsg(oMsg, '비밀번호가 일치합니다.');
    passwordFlag = true;
    return true;
}

function checkGender() {
    if (genderFlag) {
        return true;
    }
    var gender = $('#sex').val();
    var oMsg = $('#genderMsg');
    if (gender === '' || gender === '성별') {
        showErrorMsg(oMsg, '필수 정보입니다.');
        return false;
    }
    genderFlag = true;
    hideMsg(oMsg);
    return true;
}

function checkBirth() {
    if (birthFlag) {
        return true;
    }
    var birth;
    var yy = $('#yy').val();
    var mm = $('#mm').val();
    var dd = $('#dd').val();
    var oMsg = $('#birthMsg');
    if (yy === '' && mm === '' && dd === '') {
        showErrorMsg(oMsg, '생년월일을 입력해 주세요.');
        return false;
    }
    if (yy === '') {
        showErrorMsg(oMsg, '태어난 년도 4자리를 입력해 주세요.');
        return false;
    }
    if (mm === '' || mm === '월') {
        showErrorMsg(oMsg, '태어난 월을 선택해 주세요.');
        return false;
    }
    if (dd === '') {
        showErrorMsg(oMsg, '태어난 일(날짜)를 입력해 주세요.');
        return false;
    }
    if (dd.length === 1) {
        dd = '0' + dd;
    }
    birth = yy + mm + dd;
    if (!validateBirth(birth)) {
        showErrorMsg(oMsg, '생년월일을 다시 확인해 주세요.');
        return false;
    }
    var age = calcAge(birth); // 만 나이 계산
    if (age < 0) {
        showErrorMsg(oMsg, '생년월일을 다시 확인해 주세요.');
        return false;
    } else if (age >= 100) {
        showErrorMsg(oMsg, '생년월일을 다시 확인해 주세요.');
        return false;
    }
    $('#birth').val(birth);
    $('#age').val(age);
    birthFlag = true;
    hideMsg(oMsg);
    return true;
}

function checkPhone() {
    if (phoneFlag) {
        return true;
    }
    var phone = $('#phone').val();
    var oMsg = $('#phoneMsg');
    if (phone === '') {
        showErrorMsg(oMsg, '필수 정보입니다.');
        return false;
    }
    phoneFlag = true;
    hideMsg(oMsg);
    return true;
}

function checkEmail() {
    if (emailFlag) {
        return true;
    }
    var email = $('#email').val();
    var oMsg = $('#emailMsg');
    var regexp = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (email === '') {
        showErrorMsg(oMsg, '필수 정보입니다.');
        return false;
    }
    if (!regexp.test(email)) {
        showErrorMsg(oMsg, '이메일 주소를 다시 확인해 주세요.');
        return false;
    }
    $.ajax({
        type: 'GET',
        url: '/check/email.do?email=' + email,
        dataType: 'text',
        success: function (result) {
            if (result === 'true') {
                showErrorMsg(oMsg, '사용 중인 이메일입니다.');
                return false;
            } else {
                showSuccessMsg(oMsg, '사용 가능한 이메일입니다.');
                emailFlag = true;
            }
        }
    });
    return true;
}

function checkAddress() {
    if (addressFlag) {
        return true;
    }
    var postCode = $('#postcode').val();
    var homeAddr = $('#homeAddress').val();
    var oMsg = $('#addrMsg');
    if (postCode === '') {
        showErrorMsg(oMsg, '필수 정보입니다.');
        return false;
    }
    if (homeAddr === '') {
        showErrorMsg(oMsg, '필수 정보입니다.');
        return false;
    }
    addressFlag = true;
    hideMsg(oMsg);
    return true;
}

function checkInputValue() {
    checkName();
    checkUserId();
    checkPassword1();
    checkPassword2();
    checkGender();
    checkBirth();
    checkPhone();
    checkEmail();
    checkAddress();
    if (nameFlag &&
        userIdFlag &&
        passwordFlag &&
        genderFlag &&
        birthFlag &&
        phoneFlag &&
        emailFlag &&
        addressFlag) {
        if (!userIdCheckFlag) {
            alert('아이디 중복 확인을 해주세요.');
            return false;
        } else {
            submitFlag = true;
        }
    }
}

function validateBirth(birth) {
    if (birth.length !== 8) {
        return false
    }
    var year = Number(birth.substring(0, 4));
    var month = Number(birth.substring(4, 6));
    var day = Number(birth.substring(6, 8));
    if (month < 1 || month > 12) {
        return false;
    }
    var maxDayForMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var maxDay = maxDayForMonth[month - 1];
    if (month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)) {
        maxDay = 29;
    }
    if (day <= 0 || day > maxDay) {
        return false;
    }
    return true;
}

function calcAge(birth) {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month < 10) {
        month = '0' + month;
    }
    if (day < 10) {
        day = '0' + day;
    }
    var monthDay = month + '' + day;
    birth = birth.replace('-', '').replace('-', '');
    var birthDay = birth.substring(0, 4);
    var birthMonth = birth.substring(4, 8);
    return monthDay < birthMonth ? year - birthDay - 1 : year - birthDay;
}

function showErrorMsg(oMsg, message) {
    oMsg.attr('class', 'text-danger');
    oMsg.html(message);
    oMsg.show();
}

function showSuccessMsg(oMsg, message) {
    oMsg.attr('class', 'text-success')
    oMsg.html(message);
    oMsg.show();
}

function hideMsg(oMsg) {
    oMsg.hide();
}
