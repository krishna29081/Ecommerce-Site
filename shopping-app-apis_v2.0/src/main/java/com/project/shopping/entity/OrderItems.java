package com.project.shopping.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItems {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int orderItemId;

@OneToOne(cascade = CascadeType.MERGE)
@JoinColumn(name = "productId")
private Products product;

private double productCartQuantity;
private double totalProductPrice;

@ManyToOne(cascade = CascadeType.PERSIST)
private Orders order;

	
}
