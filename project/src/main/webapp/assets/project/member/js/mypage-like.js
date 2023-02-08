$(function () {
    showCampLikeList(1)
    showItemLikeList(1);
});

function showCampLikeList(curPage) {
    $.ajax({
        type: 'get',
        url: '/campLikeList.do?page=' + curPage,
        dataType: 'json',
        success: function (result) {
            drawCampLikeList(result);
        }
    });
}

function showItemLikeList(curPage) {
    $.ajax({
        type: 'get',
        url: '/itemLikeList.do?page=' + curPage,
        dataType: 'json',
        success: function (result) {
            drawItemLikeList(result);
        }
    });
}

function drawCampLikeList(result) {
    $('#camp-like-list').empty();
    var mid = $('#loginMid').val();
    var items = result.items;
    var html = '';
    if (items.length === 0) {
        html = '<div class="mt-3">'
        html = html + '<div class="text-center">'
        html = html + '<h3>좋아요한 캠핑장이 없습니다.</h3>'
        html = html + '</div>'
        html = html + '</div>'
    } else {
        $.each(items, function (index) {
            html = html + '<div class="mb-3"> ' +
                '<div class="d-flex justify-content-between"> ' +
                '<div class="d-flex justify-content-start align-items-center"> ' +
                '<div class="me-4"> ' +
                '<img src=' + items[index].image + ' width="130px;"> ' +
                '</div> ' +
                '<div> ' +
                '<a class="text-decoration-none text-dark" href=/camp/detail.do?cno=' + items[index].cno + '> ' +
                '<h4 class="m-0">'+ items[index].name +'</h4> ' +
                '</a> ' +
                '</div> ' +
                '</div> ' +
                '<div class="d-flex justify-content-end align-items-center">' +
                '<div> ' +
                '<button type="button" class="btn btn-outline-danger" onclick="campUnLike('+ items[index].cno +')">삭제</button> ' +
                '</div> ' +
                '</div> ' +
                '</div> ' +
                '</div><hr/>'
        });
        drawCampLikePageButton(result);
    }
    $('#camp-like-list').append(html);
}

function drawItemLikeList(result) {
    var mid = $('#loginMid').val();
    var items = result.items;
    console.log(items);
    var html = '';
    $('#item-like-list').empty(html);
    if (items.length === 0) {
        html = '<div class="mt-3">'
        html = html + '<div class="text-center">'
        html = html + '<h3>좋아요한 상품이 없습니다.</h3>'
        html = html + '</div>'
        html = html + '</div>'
    } else {
        $.each(items, function (index) {
            html = html + '<div class="mb-3"> ' +
                '<div class="d-flex justify-content-between"> ' +
                '<div class="d-flex justify-content-start align-items-center"> ' +
                '<div class="me-4"> ' +
                '<img src=' + items[index].image + ' width="130px;"> ' +
                '</div> ' +
                '<div> ' +
                '<a class="text-decoration-none text-dark" href=/item/item_detail.do?ino=' + items[index].ino + '> ' +
                '<h4 class="m-0">'+ items[index].name +'</h4> ' +
                '</a> ' +
                '<div class="mt-1"><spa>' + new Intl.NumberFormat().format(items[index].price) + '원</spa></div>' +
                '</div> ' +
                '</div> ' +
                '<div class="d-flex justify-content-end align-items-center">' +
                '<div> ' +
                '<button type="button" class="btn btn-outline-danger" onclick="itemUnLike('+ items[index].ino +')">삭제</button> ' +
                '</div> ' +
                '</div> ' +
                '</div> ' +
                '</div><hr/>'
        });
        drawItemLikePageButton(result);
    }
    $('#item-like-list').append(html);
}


function drawCampLikePageButton(result) {
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
        html = html + '<a class="page-link" id="page-btn-' + i + '" role="button" data-value="' + i + '" onclick="showCampLikeList(' + i + ')">' + i + '</a>'
        html = html + '</li>'
    }
    $('#camp-page-buttons').append(html);
}

function drawItemLikePageButton(result) {
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
        html = html + '<a class="page-link" id="page-btn-' + i + '" role="button" data-value="' + i + '" onclick="showItemLikeList(' + i + ')">' + i + '</a>'
        html = html + '</li>'
    }
    $('#item-page-buttons').append(html);
}

function campUnLike(cno) {
    $.ajax({
        type: 'post',
        url: '/campUnLike.do',
        data: {
            cno: cno
        },
        success: function () {
            showCampLikeList(1);
        }
    })
}

function itemUnLike(ino) {
    $.ajax({
        type: 'post',
        url: '/itemUnLike.do',
        data: {
            ino: ino
        },
        success: function () {
            showItemLikeList(1);
        }
    })
}
