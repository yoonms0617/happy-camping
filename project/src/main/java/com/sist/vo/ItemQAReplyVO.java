package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
public class ItemQAReplyVO {

    private int qarno;

    private String writer;

    private String content;

    private Date regdate;

}
