package com.sist.util;

import java.util.List;

import lombok.Getter;

@Getter
public class Pagination {

    private List<?> items;

    private int curPage = 0;

    private int startPage = 0;

    private int endPage = 0;

    private int allCampCnt;

    private int totalPage = 0;

    private boolean isFirst = false;

    private boolean isLast = false;

    private boolean hasPrev = true;

    private boolean hasNext = true;

    public Pagination(List<?> items, int curPage, int allCampCnt) {
        this.items = items;
        this.curPage = curPage;
        this.allCampCnt = allCampCnt;
        setTotalPage(allCampCnt);
        makeBlock(curPage);
        setFirst();
        setLast();
        setHasPrev();
        setHasNext();
    }

    private void makeBlock(int curPage) {
        int rowSize = 10;
        int temp = (int) Math.floor((curPage - 1) / rowSize);
        startPage = (rowSize * temp) + 1;
        endPage = startPage + (rowSize - 1);
        if (totalPage <= endPage) {
            endPage = totalPage;
        }
    }

    private void setTotalPage(int allCampCnt) {
        int rowSize = 10;
        if (allCampCnt % rowSize != 0) {
            totalPage = allCampCnt / rowSize + 1;
        } else {
            totalPage = allCampCnt / rowSize;
        }
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
