package com.project.shopping.service;

import java.util.List;

import com.project.shopping.payloads.UserDTO;
import com.project.shopping.payloads.addTocartDTO;

public interface addToCartService {

		addTocartDTO addProduct(Integer productId, Integer quantity,Integer userId);
		void removeProduct(Integer productID, UserDTO user);
		List<addTocartDTO> findByCustomer(Integer customerId);
		
}
