package com.project.shopping.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class Products {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer productId;
 
 private String productName;
 private Integer productPrice;
 private Integer productQuantity;
 private String productDescription;
 
 
 private String productImageName;
 
 



@ManyToOne
@JoinColumn(name = "category_id")
private Categories categories;
 
@ManyToOne
 private User user;
 
@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
 private List<addToCart> addtocart = new ArrayList<>(); ;
 
}
