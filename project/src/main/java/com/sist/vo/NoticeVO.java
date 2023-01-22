package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class NoticeVO {

    private int nno;

    private String writer;

    private String title;

    private String content;

    private Date regDate;

    private int hit;
}
