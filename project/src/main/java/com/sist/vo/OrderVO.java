package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderVO {

    private int ono;

    private int price;

    private String code;

    private String status;

    private Date orderedAt;

}
