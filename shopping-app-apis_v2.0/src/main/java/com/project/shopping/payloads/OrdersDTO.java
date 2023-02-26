package com.project.shopping.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class OrdersDTO {
private int orderId;
	
	private String orderStatus;
	private Date orderDelivered;
	private double orderAmt;
	private String billingAdrress;
	private UserDTO user;
	
	
}
