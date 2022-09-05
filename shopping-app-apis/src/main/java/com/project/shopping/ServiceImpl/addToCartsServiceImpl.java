package com.project.shopping.ServiceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopping.entity.Products;
import com.project.shopping.entity.User;
import com.project.shopping.entity.addToCart;
import com.project.shopping.exceptions.ResourceNotFoundException;
import com.project.shopping.payloads.UserDTO;
import com.project.shopping.payloads.addTocartDTO;
import com.project.shopping.repo.ProductRepo;
import com.project.shopping.repo.UserRepo;
import com.project.shopping.repo.cartRepo;
import com.project.shopping.service.addToCartService;

@Service
public class addToCartsServiceImpl implements addToCartService {
	
	Logger logger = LoggerFactory.getLogger(addToCartsServiceImpl.class);
	@Autowired
	private ProductRepo productrepo;
	
	@Autowired
	private cartRepo cartrepo;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	ModelMapper modelmapper;
	
	@Override
	public addTocartDTO addProduct(Integer productId, Integer quantity, Integer userId) {
		logger.info("\n\nstart of the service impl with addtocartvalue as :");
		Integer addedQuantity = quantity;
		Products product = productrepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","id", productId));
		User userID1 = userrepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Product","id", userId));
		addToCart findByUserAndProducts = cartrepo.findByUserAndProduct(userID1, product);
		
		if(findByUserAndProducts != null)
		{	logger.info("\n\ninside if");
			addedQuantity= findByUserAndProducts.getQuantity() + quantity;
			findByUserAndProducts.setQuantity(addedQuantity);
		}else {
			logger.info("\n\ninside else");
			 findByUserAndProducts = new addToCart();
			 findByUserAndProducts.setQuantity(quantity);
			 findByUserAndProducts.setUser(userID1);
			 findByUserAndProducts.setProduct(product);
		}
		addToCart save = cartrepo.save(findByUserAndProducts);
		
		logger.info("\n\nEnd of the service impl with addtocartvalue as : {}",save.toString());
		addTocartDTO map = modelmapper.map(save, addTocartDTO.class);
//		addTocartDTO map = DTOtoUser(save);
		logger.info("\n\nEnd of the service impl with addtocartvalue after mapping as : {}");
	
		return map;
		
	}

	@Override
	public void removeProduct(Integer productID, UserDTO user) {
		

	}

	@Override
	public List<addTocartDTO> findByCustomer(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	private addTocartDTO DTOtoUser(addToCart userDTO)
//	{	
//		addTocartDTO addtocart = new addTocartDTO();
//		addtocart.setCartId(userDTO.getCartId());
//		addtocart.setProduct(userDTO.getProduct());
//		logger.info("\n\nEnd of the service impl with addtocartvalue after mapping as : {}",addtocart.getProduct().getProductName());
//		addtocart.setQuantity(userDTO.getQuantity());
//		addtocart.setUser(userDTO.getUser());
//		return addtocart;
//	}

}
