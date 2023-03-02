package com.project.shopping.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopping.entity.OrderItems;
import com.project.shopping.entity.Orders;
import com.project.shopping.entity.Products;
import com.project.shopping.entity.User;
import com.project.shopping.exceptions.ResourceNotFoundException;
import com.project.shopping.payloads.OrderRequest;
import com.project.shopping.payloads.ProductDTO;
import com.project.shopping.repo.OrderITemsRepo;
import com.project.shopping.repo.OrderRepo;
import com.project.shopping.repo.UserRepo;
import com.project.shopping.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class orderServiceImpl implements OrderService {

	@Autowired
	UserRepo userrepo;

	@Autowired
	ModelMapper modelmapper;
	
	@Autowired
	OrderRepo orderrepo; 

	@Autowired
	OrderITemsRepo orderItemsrepo;
	@Override
	public Orders orderCreate(OrderRequest request) {
		String userId = request.getEmail();
		User userID1 = userrepo.findByUseremailId(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Orders order = new Orders();
		order.setUser(userID1);
//		order.setOrderAmt(request.getTotalAmount());
		order.setCartTotalQuantity(request.getCartTotalquantity());
		order.setBillingAdrress(request.getAddress());
		List<ProductDTO> cartItems = request.getCartItems()
				.stream()
				.map(cartItem -> modelmapper.map(cartItem, ProductDTO.class))
				.collect(Collectors.toList());
		
		
		
		
		List<Integer> perItemQuantity = request.getPeritemquantity()
				.stream()
				.collect(Collectors.toList());
		log.info(cartItems.toString());
		
		
		
		
		List<OrderItems> orderitem =  cartItems.stream().map((cartItem)-> {
				OrderItems orderItem = new OrderItems();
				orderItem.setProduct(modelmapper.map(cartItem, Products.class));
//				log.info(orderItem.getProduct().toString());
				orderItem.setOrder(order);
				return orderItem;

		 }).collect(Collectors.toList());
		
//		List<OrderItems> orderitem1 =  perItemQuantity.stream().map((cartItem)-> {
//			OrderItems orderItem = new OrderItems();
//			order
//			return orderItem;
//
//	 }).collect(Collectors.toList());
		Integer Totalamount=0;
		order.setOrderCreated(new Date());
		for(int i=0;i<orderitem.size();i++)
		{
			Totalamount=Totalamount+ (perItemQuantity.get(i)*(orderitem.get(i).getProduct().getProductPrice()));
			log.info("\n\n Totalamount"+Totalamount);
		}
		
		order.setOrderAmt(Totalamount);
		orderrepo.save(order);
		for(int i=0; i<orderitem.size();i++)
		{	orderitem.get(i).setProductCartQuantity(perItemQuantity.get(i));
			orderItemsrepo.save(orderitem.get(i));
		}
		
		
//		
		
//		
//		log.info("\n\n   "+cartItems);
//		log.info("\n\n   "+cartItems.size());
////		List<Products> p1= new ArrayList<>();
//		log.info("\n\n  CartItem "+ cartItems.get(0).toString());
//		Products product = DTOtoproduct(cartItems.get(0));
//		
//		log.info("\n\n  Product "+ product.toString());
//		
////		o2.setProduct(product);
////		log.info("\n\n orderItem"+  o2.toString());
//		for(int i=0; i < cartItems.size();i++)
//		{	
////			o1.get(i).setProduct(modelmapper.map(cartItems.get(i),Products.class));
////			p1.add(modelmapper.map(cartItems.get(i),Products.class));
//			order.getProducts().add(DTOtoproduct(cartItems.get(i)));
//
//		}
		//roles
//				Role roleOfuser = rolerepo.findById(AppConstants.NORMAL_USER).get();
//				
//				user.getRoles().add(roleOfuser);
//				User newUser = userrepo.save(user);
		
//		log.info("\n\n  Order "+ order.toString());
//		o1 = cartItems.stream().map(null)
//		order.getOrderItem()
//		log.info( "\n\n      Request  "+request.toString());
		
		
		return order;
	}
	
//	private Products DTOtoproduct(ProductDTO productDTO)
//	{
//		Products product= new Products();
//		
//		product.setProductId(productDTO.getProductId());
//		product.setImgUrl(productDTO.getImgUrl());
//		product.setInStock(productDTO.getInStock());
//		product.setProductCartQuantity(productDTO.getProductCartQuantity());
//		product.setProductDescription(productDTO.getProductDescription());
//		product.setProductImageName(productDTO.getProductImageName());
//		product.setProductPrice(productDTO.getProductPrice());
//		product.setProductQuantity(productDTO.getProductQuantity());
//		product.setSize(productDTO.getSize());
//		product.setProductName(productDTO.getProductName());
//		
//		user.setId(userDTO.getId());
//		user.setDOB(userDTO.getDOB());
//		user.setName(userDTO.getName());
//		user.setUserAddress(userDTO.getUserAddress());
//		user.setUseremailId(userDTO.getUseremailId());
//		user.setUserPassword(userDTO.getUserPassword());
//		return product;
//	}

}
