package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CampReviewVO {

    private int clno;

    private String writer;

    private String content;

    private Date regDate;

}
