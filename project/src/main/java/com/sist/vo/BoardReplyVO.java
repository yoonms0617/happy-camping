package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BoardReplyVO {

    private int brno;

    private String writer;

    private String content;

    private Date regDate;

}
