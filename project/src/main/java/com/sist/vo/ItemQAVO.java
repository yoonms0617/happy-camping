package com.sist.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemQAVO {

    private int qano;

    private String title;

    private String content;

    private String type;

    private String lock;

    private String password;

    private Date regdate;

    private int hit;

}
