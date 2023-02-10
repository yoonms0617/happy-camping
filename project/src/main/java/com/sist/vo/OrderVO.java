package com.sist.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderVO {

    private int ono;

    private int price;

    private String code;

    private String status;

    private Date orderedAt;

    private OrderItemVO orderItemVO;

    private DeliveryVO deliveryVO;

}
