$(function () {
    showItemReviewList(1);
});

function showItemReviewList(curPage) {
    const ino = getIno();
    $.ajax({
        type: 'GET',
        url: '/item/review/list.do?ino=' + ino + '&page=' + curPage,
        dataType: 'json',
        success: function (result) {
            drawReviews(result);
        }
    });
}

function drawReviews(result) {
    $('#review-list').empty();
    var reviews = result.items;
    var html = '';
    var mid = $('#loginMid').val();
    if (reviews.length === 0) {
        html = '<div class="text-center mb-3 mt-3"><h2>등록된 후기가 없습니다.</h2></div>'
    } else {
        $.each(reviews, function (index) {
            html = html + '<li class="list-group-item">'
            html = html + '<div class="">'
            html = html + '<label For="review-content" class="me-3">' + reviews[index].writer + '</label><span>' + reviews[index].created + '</span>'
            html = html + '<input type="hidden" value="' + reviews[index].rno + '" id="review-rno-' + index + '">'
            html = html + '<input type="hidden" value="' + result.curPage + '" id="review-cur-' + index + '">'
            if (mid === reviews[index].writer) {
                html = html + '<span class="ms-3" id="buttons' + index + '">'
                html = html + '<a role="button" class="me-1 btn btn-sm btn-outline-secondary" id="review-update-btn" onclick="updateReview(' + index + ')">수정</a>'
                html = html + '<a role="button" class="btn btn-sm btn-outline-danger" id="review-delete-btn" onclick="deleteReview(' + index + ')">삭제</a>'
                html = html + '</span>'
            }
            html = html + '<p type="text" class="form-control-plaintext" id="review-content-' + index + '" style="white-space: pre">' + reviews[index].content + '</p>'
            html = html + '<div id="update-modify-buttons-' + index + '" class="mt-1 d-flex justify-content-end"></div>'
            html = html + '</div>'
            html = html + '</li>'
        });
    }
    $('#review-list').append(html);
}

function drawPageButton(result) {
    $('#page-buttons').empty();
    var html = '';
    var ino = getIno();
    var start = result.startPage;
    var end = result.endPage;
    var curPage = result.curPage;
    html
    for (var i = start; i <= end; i++) {
        html = html + '<li class="page-item'
        if (i === curPage) {
            html = html + ' active">'
        } else {
            html = html + '">'
        }
        html = html + '<a class="page-link" id="page-btn-' + i + '" role="button" data-value="' + i + '" onclick="showItemReviewList(' + i + ')">' + i + '</a>'
        html = html + '</li>'
    }
    html
    $('#page-buttons').append(html);
}

function writeReview(mid) {
    if (mid === '') {
        alert("로그인 후 작성하실 수 있습니다.")
        return false;
    }
    var ino = getIno();
    var content = $('#review-form').val();
    if (content === '') {
        alert("내용을 입력해주세요.");
        return false;
    }
    $.ajax({
        type: 'post',
        url: '/item/review/write.do',
        data: {
            mid: mid,
            content: content,
            ino: ino
        },
        success: function (result) {
            $('#review-form').val('');
            reloadReviewList();
        }
    });
}

function updateReview(index) {
    cancel();
    $('#buttons' + index).hide();
    var html = '<textarea id="review-update-form-' + index + '" class="mt-1 form-control" onfocus="resize(this)" onkeyup="resize(this)" onkeydown="resize(this)" style="width: 100%; min-height: 5rem; overflow-y: hidden; resize: none;"></textarea>'
    $('#review-content-' + index).contents().unwrap().wrap(html)
    $('#review-update-form-' + index).focus();
    $('#update-modify-buttons-' + index).append(
        '<button id="updateBtn' + index + '" class="me-2 btn btn btn-sm btn-primary" onclick="update(' + index + ')">등록</button>' +
        '<button id="deleteBtn' + index + '" class="btn btn btn-sm btn-secondary" onclick="cancel()">취소</button>'
    )
}

function update(index) {
    var content = $('#review-update-form-' + index).val();
    var rno = $('#review-rno-' + index).val();
    var temp = $('#review-cur-' + index).val();
    var curPage = $('#page-btn-' + temp).data('value');
    var ino = getIno();
    $.ajax({
        type: 'post',
        url: '/item/review/update.do',
        data: {
            rno: rno,
            content: content
        },
        success: function () {
            showItemReviewList(curPage);
        }
    });
}

function cancel() {
    let length = $('#review-list').find("li").length;
    for (var index = 0; index < length; index++) {
        var html = '<p type="text" class="form-control-plaintext" id="review-content-' + index + '" style="white-space: pre"></p>'
        $('#review-update-form-' + index).contents().unwrap().wrap(html)
        $('#update-modify-buttons-' + index).empty();
        $('#buttons' + index).show();
    }

}

function deleteReview(index) {
    var isDelete = confirm('댓글을 삭제하시겠습니까?');
    if (isDelete) {
        var rno = $('#review-rno-' + index).val();
        var temp = $('#review-cur-' + index).val();
        var curPage = $('#page-btn-' + temp).data('value');
        $.ajax({
            type: 'POST',
            url: '/item/review/delete.do',
            data: {
                rno: rno,
            },
            success: function () {
                showItemReviewList(curPage);
            }
        });
    }
}

function resize(obj) {
    obj.style.height = '1px';
    obj.style.height = (12 + obj.scrollHeight) + 'px';
}

function getIno() {
    let params = new URLSearchParams(location.search);
    return params.get('ino');
}

function reloadReviewList() {
    $('#review-list').load(location.href + '#review-list');
}