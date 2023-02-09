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

    private int bno;

    private String mid;

    private int group_id;

    private int group_step;

    private int group_tab;

    private int root;

    private int depth;

    private String dbday;
}
