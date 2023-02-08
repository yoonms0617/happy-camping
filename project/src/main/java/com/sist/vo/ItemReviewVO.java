package com.sist.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemReviewVO {

    private int rno;

    private String writer;

    private String content;

    private Date created;

    private Date updated;

    private String mid;

    private int hit;

    private String pwd;

    private String subject;

    private String dbday;

    private int num;

    private int ino;

    private ItemVO itemVO;

}