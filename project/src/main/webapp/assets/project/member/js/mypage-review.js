$(function () {
    showCampReviewList(1);
    showItemReviewList(1);
});

function showCampReviewList(curPage) {
    $.ajax({
        type: 'get',
        url: '/campReviewList.do?page=' + curPage,
        dataType: 'json',
        success: function (result) {
            drawCampReview(result);
        }
    });
}

function showItemReviewList(curPage) {
    $.ajax({
        type: 'get',
        url: '/itemReviewList.do?page=' + curPage,
        dataType: 'json',
        success: function (result) {
            drawItemReview(result);
        }
    });
}

function drawCampReview(result) {
    $('#camp-review-list').empty();
    var items = result.items;
    var mid = $('#loginMid').val();
    var html = '';
    if (items.length === 0) {
        html = '<div class="text-center mb-3 mt-3"><h2>등록된 후기가 없습니다.</h2></div>'
    } else {
        $.each(items, function (index) {
            html = html + '<li class="list-group-item">'
            html = html + '<div class="mb-3">'
            html = html + '<div class="mb-1 d-flex align-items-center">'
            html = html + '<img class="me-2" src="' + items[index].campVO.image + '" width="100px;">'
            html = html + '<a class="text-decoration-none" href="/camp/detail.do?cno=' + items[index].cno + '">'
            html = html + '<span>' + items[index].campVO.name + '(' + items[index].campVO.address + ')</span>'
            html = html + '</a>'
            html = html + '</div>'
            html = html + '<span><small class="text-secondary" style="font-size: 0.7em">' + mid + ' ' + items[index].regDate + '</small></span>'
            html = html + '<p type="text" class="form-control-plaintext small" id="review-content-' + index + '" style="white-space: pre;">' + items[index].content + '</p>'
            html = html + '<div id="update-modify-buttons-' + index + '" class="mt-1 d-flex justify-content-end"></div>'
            html = html + '<hr class="m-0">'
            html = html + '</div>'
            html = html + '</li>'
        });
        drawCampPageButton(result);
    }
    $('#camp-review-list').append(html);
}

function drawCampPageButton(result) {
    $('#camp-page-buttons').empty();
    var html = '';
    var start = result.startPage;
    var end = result.endPage;
    var curPage = result.curPage;
    for (var i = start; i <= end; i++) {
        html = html + '<li class="page-item'
        if (i === curPage) {
            html = html + ' active">'
        } else {
            html = html + '">'
        }
        html = html + '<a class="page-link" id="page-btn-' + i + '" role="button" data-value="' + i + '" onclick="showCampReviewList(' + i + ')">' + i + '</a>'
        html = html + '</li>'
    }
    $('#camp-page-buttons').append(html);
}

function drawItemReview(result) {
    $('#item-review-list').empty();
    var items = result.items;
    var mid = $('#loginMid').val();
    var html = '';
    if (items.length === 0) {
        html = '<div class="text-center mb-3 mt-3"><h2>등록된 후기가 없습니다.</h2></div>'
    } else {
        $.each(items, function (index) {
            html = html + '<li class="list-group-item">'
            html = html + '<div class="mb-3">'
            html = html + '<div class="mb-1 d-flex align-items-center">'
            html = html + '<img class="me-2" src="' + items[index].itemVO.image + '" width="100px;">'
            html = html + '<a class="text-decoration-none" href="/item/item_detail.do?ino=' + items[index].itemVO.ino + '">'
            html = html + '<span>' + items[index].itemVO.name + '</span>'
            html = html + '</a>'
            html = html + '</div>'
            html = html + '<span><small class="text-secondary" style="font-size: 0.7em">' + mid + ' ' + items[index].UPDATED + '</small></span>'
            html = html + '<p type="text" class="form-control-plaintext small" id="review-content-' + index + '" style="white-space: pre;">' + items[index].content + '</p>'
            html = html + '<div id="update-modify-buttons-' + index + '" class="mt-1 d-flex justify-content-end"></div>'
            html = html + '<hr class="m-0">'
            html = html + '</div>'
            html = html + '</li>'
        });
        drawItemPageButton(result);
    }
    $('#item-review-list').append(html);
}

function drawItemPageButton(result) {
    $('#item-page-buttons').empty();
    var html = '';
    var start = result.startPage;
    var end = result.endPage;
    var curPage = result.curPage;
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
    $('#item-page-buttons').append(html);
}
