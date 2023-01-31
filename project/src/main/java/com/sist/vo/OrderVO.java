package com.sist.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO {

    private int ono;

    private int price;

    private String code;

    private String status;

    private Date orderedAt;

}
