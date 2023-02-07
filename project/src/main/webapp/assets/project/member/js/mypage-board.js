$(function () {
    showBoardList(1);
});

function showBoardList(curPage) {
    $.ajax({
        type: 'get',
        url: '/boardList.do?page=' + curPage,
        dataType: 'json',
        success: function (result) {
            drawBoardList(result);
        }
    });
}

function drawBoardList(result) {
    $('#board-list').empty();
    console.log(result);
    var items = result.items;
    var mid = $('#loginMid').val();
    var html = '';
    if (items.length === 0) {
        html = '<tr><td colspan="4"><h1>등록된 게시글이 없습니다.</h1></td></tr>'
    } else {
        $.each(items, function (index) {
            html = html + '<tr>';
            html = html + '<th>' + items[index].bno + '</th>';
            html = html + '<td class="text-start">';
            html = html + '<a class="text-decoration-none" href="/board/detail.do?no=' + items[index].bno + '">' + items[index].title + '</a>';
            html = html + '</td>'
            html = html + '<td>' + mid + '</td>';
            html = html + '<td>' + items[index].regDate + '</td>';
            html = html + '</tr>';
        });
        drawBoardPageButton(result);
    }
    $('#board-list').append(html);
}

function drawBoardPageButton(result) {
    $('#board-page-buttons').empty();
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
    $('#board-page-buttons').append(html);
}