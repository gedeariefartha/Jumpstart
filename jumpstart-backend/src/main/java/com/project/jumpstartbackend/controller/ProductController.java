package com.project.jumpstartbackend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.project.jumpstartbackend.dto.PaymentRegDto;
import com.project.jumpstartbackend.dto.ProductRegDto;
import com.project.jumpstartbackend.entity.Order;
import com.project.jumpstartbackend.entity.Payment;
import com.project.jumpstartbackend.entity.Product;
import com.project.jumpstartbackend.entity.User;
import com.project.jumpstartbackend.repository.UserRepository;
import com.project.jumpstartbackend.service.MealService;
import com.project.jumpstartbackend.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	
	@Autowired
	ProductService productService;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private MealService mealService;
	
	
	
	@PostMapping(value = "/post-payment")
	public ResponseEntity<String> newPayment(@RequestBody PaymentRegDto dtoNew) {
			Date cur_time=new Date();
			User usid = userRepository.findById(dtoNew.getMemberid()).get();
			User paid = userRepository.findById(dtoNew.getStaffid()).get();
			Order orid = mealService.getOrderId(dtoNew.getOrderid());
			Payment py = new Payment ();
			py.setMemberid(usid);
			py.setOrderid(orid);
			py.setStaffid(paid);
			py.setDatetime(cur_time);
			py.setStatus(dtoNew.isStatus());
			productService.AddPayment(py);
			return new ResponseEntity<>("New Order Successfuly added! ",HttpStatus.OK);	
	}
	
	 @GetMapping(value = "/deleteproduct/{idtodelete}")
	    public String deleteProduct(@PathVariable("idtodelete") Long id) {
		 productService.deleteProduct(id);
	    	return "Data Deleted";
	    }
	
	
	    @GetMapping(value = "/searchname")
	    public List<Product> productname(@RequestParam("name") String productname){
			return  productService.searchByProductName(productname);
	    }
	 
	@PostMapping(value = "/post-product")
	public ResponseEntity<String> newMenu(@RequestBody ProductRegDto dtoNew) {

			User paid =  productService.getUserId(dtoNew.getStaffid());
			Product men = new Product();
			men.setName(dtoNew.getName());
			men.setDesc(dtoNew.getDesc());
			men.setStaffid(paid);
			men.setStatus(dtoNew.isStatus());
			men.setPrize(dtoNew.getPrize());
			 productService.SaveProduct(men);
			return new ResponseEntity<>("Post product Successful! ",HttpStatus.OK);
		
	}
	@PostMapping(value = "/edit-product")
	public ResponseEntity<String> EditProduct(@RequestBody  ProductRegDto dtoNew) {
		 productService.EditProduct(dtoNew);
			return new ResponseEntity<>("Edit Menu Successful! ",HttpStatus.OK);
		
	}
	@GetMapping("/product-list")
	public List<Product> Productlist() {
		List<Product> result =  productService.listProductStatus();
		return result;		
	}
	@GetMapping("/menu-staff/{ps}")
	public List<Product> ProductStafflist(@PathVariable("pid") Long ps) {
		List<Product> result =  productService.listProductStaff(ps);
		return result;		
	}
	@GetMapping("/product/{prodId}")
	public Product getProductById(@PathVariable("prodId") Long prodId) {
		Product product = productService.getproductId(prodId);
	   return product;
	}	

}
