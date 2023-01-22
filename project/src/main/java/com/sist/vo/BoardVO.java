package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BoardVO {

    private int bno;

    private String writer;

    private String title;

    private String content;

    private Date regDate;

}
