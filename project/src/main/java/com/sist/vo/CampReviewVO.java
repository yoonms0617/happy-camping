package com.sist.vo;

import java.util.Date;

public class CampReviewVO {

    private int clno;

    private String writer;

    private String content;

    private Date regDate;

    private String cno;

    private String mid;

    public int getClno() {
        return clno;
    }

    public void setClno(int clno) {
        this.clno = clno;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

}
