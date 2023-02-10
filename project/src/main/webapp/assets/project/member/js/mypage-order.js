$(function () {
    showOrderList(1);
});

function showOrderList(curPage) {
    $.ajax({
        type: 'get',
        url: '/order/list.do?page=' + curPage,
        dataType: 'json',
        success: function (result) {
            drawOrderList(result);
        }
    });
}

function drawOrderList(result) {
    $('#order-item-list').empty();
    var items = result.items;
    console.log(items);
    var html = '';
    if (items.length === 0) {
        html = '<div class="text-center mb-3 mt-3"><h2>주문한 상품이 없습니다.</h2></div>'
    } else {
        $.each(items, function (index) {
            html = html + '<div class="border p-3 mb-3"> ' +
                '<div class="d-flex justify-content-between align-items-center mb-3"> ' +
                '<div> ' +
                '<span class="fs-4">' + items[index].orderedAt + ' 주문</span> ' +
                '</div> ' +
                '<div> ' +
                '<a class="text-decoration-none" href="/order/detail.do?ono=' + items[index].ono + '">주문 상세보기</a> ' +
                '</div> ' +
                '</div> ' +
                '<hr> ' +
                '<div class="p-1"> ' +
                '<div class="d-flex justify-content-between"> ' +
                '<div class="d-flex justify-content-start align-items-center"> ' +
                '<div class="me-4"> ' +
                '<img src=' + items[index].orderItemVO.image + ' width="100px;"> ' +
                '</div> ' +
                '<div> ' +
                '<a class="text-decoration-none text-dark" href=/item/item_detail.do?ino=' + items[index].orderItemVO.ino + '> ' +
                '<h4 class="m-0 text-truncate" style="max-width: 650px;">' + items[index].orderItemVO.name + '</h4> ' +
                '</a> ' +
                '<div class="mt-1"> ' +
                '<span class="text-muted">' + new Intl.NumberFormat().format(items[index].orderItemVO.price) + '원 • ' + items[index].orderItemVO.quantity + '</span> ' +
                '</div> ' +
                '</div> ' +
                '</div> ' +
                '<div class="d-flex justify-content-end align-items-center"> ' +
                '<div> ' +
                '<button type="button" class="btn btn-outline-danger fs-6" onclick="orderItemListDelete(' + items[index].ono + ')">주문내역 삭제</button> ' +
                '</div> ' +
                '</div> ' +
                '</div> ' +
                '</div> ' +
                '</div>'
        });
        drawOrderPageButton(result);
    }
    $('#order-item-list').append(html);
}

function drawOrderPageButton(result) {
    $('#order-item-page-buttons').empty();
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
        html = html + '<a class="page-link" id="page-btn-' + i + '" role="button" data-value="' + i + '" onclick="showOrderList(' + i + ')">' + i + '</a>'
        html = html + '</li>'
    }
    $('#order-item-page-buttons').append(html);
}

function orderItemListDelete(ono) {
    $.ajax({
        type: 'post',
        url: '/order/list/delete.do?ono=' + ono,
        success: function () {
            showOrderList(1);
        }
    })
}

