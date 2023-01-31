package com.sist.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

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
