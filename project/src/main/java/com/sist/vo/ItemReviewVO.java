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

    private Date createdAt;

    private Date updatedAt;

    private int hit;

}
