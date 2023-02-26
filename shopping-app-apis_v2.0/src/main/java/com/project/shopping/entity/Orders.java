package com.project.shopping.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private String orderStatus;
	private Date orderDelivered;
	private double orderAmt;
	private String billingAdrress;
	private Integer cartTotalQuantity;
	private Date orderCreated;
	@OneToOne
	private User user;
	
//	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER )
//	@JoinTable(name="Order_Product",
//	joinColumns=@JoinColumn(name="Orders",referencedColumnName="orderId"),
//	inverseJoinColumns =@JoinColumn(name="Products",referencedColumnName ="productId"))
//	private List<Products> products = new ArrayList<>();
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItems> orderitem = new ArrayList<>();
	
	
}
											