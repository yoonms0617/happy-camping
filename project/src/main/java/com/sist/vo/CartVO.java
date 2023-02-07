package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartVO {
	
	private int cno;
	private int price;
	private int quantity;
	private int ino;
	private String image;
	private String name;
	private String mid;
	private ItemVO ivo = new ItemVO();
}
