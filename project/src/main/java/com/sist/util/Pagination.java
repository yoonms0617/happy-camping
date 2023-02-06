package com.sist.util;

import java.util.List;

import lombok.Getter;

@Getter
public class Pagination {

    private List<?> items;

    private int curPage = 0;

    private int startPage = 0;

    private int endPage = 0;

    private int totalItemCnt;

    private int totalPage = 0;

    private boolean isFirst = false;

    private boolean isLast = false;

    private boolean hasPrev = true;

    private boolean hasNext = true;

    public Pagination(List<?> items, int curPage, int totalItemCnt) {
        this.items = items;
        this.curPage = curPage;
        this.totalItemCnt = totalItemCnt;
        setTotalPage(totalItemCnt);
        makeBlock(curPage);
        setFirst();
        setLast();
        setHasPrev();
        setHasNext();
    }

    private void makeBlock(int curPage) {
        int rowSize = 10;   // 숫자 선택 블록 개수
        int temp = (int) Math.floor((curPage - 1) / rowSize);
        startPage = (rowSize * temp) + 1;
        endPage = startPage + (rowSize - 1);
        if (totalPage <= endPage) {
            endPage = totalPage;
        }
    }

    private void setTotalPage(int totalItemCnt) {
        int rowSize = 10;   // 페이지당 보여줄 개수
        this.totalPage = (int) Math.ceil(totalItemCnt / rowSize) + 1;
    }

    private void setFirst() {
        if (curPage == 1) {
            isFirst = true;
        }
    }

    private void setLast() {
        if (curPage == totalPage) {
            isLast = true;
        }
    }

    private void setHasPrev() {
        if (isFirst) {
            hasPrev = false;
        }
    }

    private void setHasNext() {
        if (isLast) {
            hasNext = false;
        }
    }

}
