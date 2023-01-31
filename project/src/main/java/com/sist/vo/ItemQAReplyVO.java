package com.sist.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ItemQAReplyVO {

    private int qarno;

    private String writer;

    private String content;

    private Date regdate;

}
