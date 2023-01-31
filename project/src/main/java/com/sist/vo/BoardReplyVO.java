package com.sist.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardReplyVO {

    private int brno;

    private String writer;

    private String content;

    private Date regDate;

}
