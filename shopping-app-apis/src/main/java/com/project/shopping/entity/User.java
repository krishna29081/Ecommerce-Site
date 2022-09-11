package com.project.shopping.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  private String name;
  private String useremailId;
  private String userPassword;
  private String userAddress;
  @JsonFormat(pattern="dd-MM-yyyy")
  private Date DOB;
  



@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Products> product = new ArrayList<>();
  
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<addToCart> addtocart = new ArrayList<>(); ;
}
