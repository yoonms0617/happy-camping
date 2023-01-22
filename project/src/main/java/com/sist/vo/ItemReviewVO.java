package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ItemReviewVO {

    private int rno;

    private String writer;

    private String content;

    private Date createdAt;

    private Date updatedAt;

    private int hit;

}
