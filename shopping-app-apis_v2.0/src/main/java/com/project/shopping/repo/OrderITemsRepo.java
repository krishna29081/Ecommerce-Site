package com.project.shopping.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopping.entity.OrderItems;

public interface OrderITemsRepo extends JpaRepository<OrderItems, Integer> {

}
