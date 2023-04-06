package com.project.jumpstartbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jumpstartbackend.dto.ProductRegDto;
import com.project.jumpstartbackend.entity.Order;
import com.project.jumpstartbackend.entity.Payment;
import com.project.jumpstartbackend.entity.Product;
import com.project.jumpstartbackend.entity.User;
import com.project.jumpstartbackend.repository.PaymentRepository;
import com.project.jumpstartbackend.repository.ProductRepository;
import com.project.jumpstartbackend.repository.UserRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PaymentRepository paymentRepository;
	
	
	public Payment AddPayment(Payment py) {
		return paymentRepository.save(py);
	}
	
	
	public User getUserId(Long id) {
		return userRepository.findById(id).get();
	}
	
	public Product SaveProduct(Product mdto) {

	    return productRepository.save(mdto);
	}
	
	
	public void EditProduct(ProductRegDto dto) {
		Product edit = getproductId(dto.getId());
		edit.setId(dto.getId());
		edit.setName(dto.getName());
		edit.setDesc(dto.getDesc());
		edit.setStatus(dto.isStatus());
		edit.setPrize(dto.getPrize());
		 productRepository.save(edit);
	}
	public Product getproductId(Long id) {
		return  productRepository.findById(id).get();
	}
	
	public List<Product> listAllProduct() {
		List<Product> list =  productRepository.findAll();
		return list;
	}
	
	public List<Product> listProductStatus() {
		List<Product> list =  productRepository.findByStatusTrue();
		return list;
	}
	
	public List<Product> listProductStaff(Long ps) {
		List<Product> list =  productRepository.findByStaff(ps);
		return list;
	}
	

	public List<Product> searchByProductName(String pname) {
		// TODO Auto-generated method stub
		return productRepository.seachByProductName(pname);
	}

	
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}
}
