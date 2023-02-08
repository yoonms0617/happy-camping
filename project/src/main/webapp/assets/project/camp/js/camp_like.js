$(function () {
    isLike();
})

function isLike() {
    var cno = getCnoFromQueryParameter();
    $.ajax({
        type: 'get',
        url: '/isCampLike.do?cno=' + cno,
        dataType: 'text',
        success: function (flag) {
            console.log(flag);
            if (flag === 'N') {
                drawLikeButton();
            } else {
                drawUnLikeButton();
            }
        }
    })
}

function drawLikeButton() {
    var mid = $('#loginMid').val();
    $('#like-btn').empty();
    var html = '<button type="button" class="btn btn-lg btn-outline-primary" style="width: 400px;" id="camp-like-btn" onclick="campLike()">';
    html = html + '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">\n' +
        '  <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>\n' +
        '</svg>'
    html = html + '</button>';
    $('#like-btn').append(html);
}

function drawUnLikeButton() {
    $('#like-btn').empty();
    var html = '<button type="button" class="btn btn-lg btn-outline-danger" style="width: 400px;" id="camp-unlike-btn" onclick="campUnlike()">';
    html = html + '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">\n' +
        '  <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>\n' +
        '</svg>'
    html = html + '</button>';
    $('#like-btn').append(html);
}

function campLike() {
    var mid = $('#loginMid').val();
    if (mid === null || mid === undefined) {
        alert("로그인 후 이용해 주세요.")
        return false;
    }
    var cno = getCnoFromQueryParameter();
    $.ajax({
        type: 'post',
        url: '/campLike.do',
        data: {
            cno: cno
        },
        success: function () {
            alert("좋아요 목록에 추가되었습니다.");
            drawUnLikeButton();
        }
    });
}

function campUnlike() {
    var cno = getCnoFromQueryParameter();
    $.ajax({
        type: 'post',
        url: '/campUnLike.do',
        data: {
            cno: cno
        },
        success: function () {
            drawLikeButton();
        }
    })
}

function getCnoFromQueryParameter() {
    let params = new URLSearchParams(location.search);
    return params.get('cno');
}