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
import org.springframework.web.bind.annotation.RestController;

import com.project.jumpstartbackend.dto.CampaignRegDto;
import com.project.jumpstartbackend.dto.DeliverReqDto;
import com.project.jumpstartbackend.dto.MenuRegDto;
import com.project.jumpstartbackend.dto.OrderReqDto;
import com.project.jumpstartbackend.dto.VolunteerRegDto;
import com.project.jumpstartbackend.entity.Campaign;
import com.project.jumpstartbackend.entity.Deliver;
import com.project.jumpstartbackend.entity.Menu;
import com.project.jumpstartbackend.entity.Order;
import com.project.jumpstartbackend.entity.Product;
import com.project.jumpstartbackend.entity.User;
import com.project.jumpstartbackend.entity.Volunteer;
import com.project.jumpstartbackend.repository.UserRepository;
import com.project.jumpstartbackend.service.MealService;
import com.project.jumpstartbackend.service.ProductService;
;

@RestController
@RequestMapping("/api/meal")
public class JumpstartController {
	 public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	@Autowired
    private MealService mealService;
	@Autowired
    private UserRepository userRepository;
	@Autowired
	ProductService productService;


//Campaign
		@PostMapping(value = "/post-campaign")
		public ResponseEntity<String> postCampaign(@RequestBody CampaignRegDto dtoNew) {

				Date cur_time=new Date();
				Campaign cam = new Campaign();
				cam.setTitle(dtoNew.getTitle());
				cam.setDesc(dtoNew.getDesc());
				cam.setTimedate(cur_time);
				cam.setStatus(dtoNew.isStatus());
				mealService.SaveCampaign(cam);
				return new ResponseEntity<>("Post Campaign Successful! ",HttpStatus.OK);
			
		}
		@PostMapping(value = "/edit-campaign")
		public ResponseEntity<String> editCampaign(@RequestBody CampaignRegDto dtoNew) {
				mealService.EditCampaign(dtoNew);
				return new ResponseEntity<>("Edit Campaign Successful! ",HttpStatus.OK);
			
		}
		@GetMapping("/campaign-list")
		public List<Campaign> Campaignlist() {
			List<Campaign> result = mealService.listCampaignByStatus();
			return result;		
		}
		@GetMapping("/campaign/{camId}")
		public Campaign getCampaignById(@PathVariable("camId") Long camId) {
		   Campaign cam = mealService.get(camId);
		   return cam;
		  }		
//Volunteer
		@PostMapping(value = "/post-volunteer")
		public ResponseEntity<String> newVolunteer(@RequestBody VolunteerRegDto dtoNew) {
			Volunteer volcam = mealService.getVolunteerCheck(dtoNew.getEmail(), dtoNew.getCampaignid());
			if(volcam != null) {
				return new ResponseEntity<>("Volunteer Already register! ",HttpStatus.BAD_REQUEST);
			}else {
				Campaign caid = mealService.get(dtoNew.getCampaignid());
				Volunteer vol = new Volunteer();
				vol.setEmail(dtoNew.getEmail());
				vol.setName(dtoNew.getName());
				vol.setPhone(dtoNew.getPhone());
				vol.setCampaignId(caid);
				mealService.SaveVolunteer(vol);
				return new ResponseEntity<>("Volunteer register Successful! ",HttpStatus.OK);
			}
		}
		@GetMapping("/volunteer-list")
		public List<Volunteer> Volunteerlist() {
			List<Volunteer> result = mealService.listAllVolunteer();
			return result;		
		}
//Menu
		@PostMapping(value = "/post-menu")
		public ResponseEntity<String> newMenu(@RequestBody MenuRegDto dtoNew) {

				User paid = mealService.getUserId(dtoNew.getPartnerid());
				Menu men = new Menu();
				men.setName(dtoNew.getName());
				men.setDesc(dtoNew.getDesc());
				men.setPartnerid(paid);
				men.setStatus(dtoNew.isStatus());
				mealService.SaveMenu(men);
				return new ResponseEntity<>("Post Menu Successful! ",HttpStatus.OK);
			
		}
		@PostMapping(value = "/edit-menu")
		public ResponseEntity<String> editMenu(@RequestBody MenuRegDto dtoNew) {
				mealService.EditMenu(dtoNew);
				return new ResponseEntity<>("Edit Menu Successful! ",HttpStatus.OK);
			
		}
		@GetMapping("/menu-list")
		public List<Menu> Menulist() {
			List<Menu> result = mealService.listMenuStatus();
			return result;		
		}
		@GetMapping("/menu-partner/{pid}")
		public List<Menu> MenuPartnerlist(@PathVariable("pid") Long pid) {
			List<Menu> result = mealService.listMenuPartner(pid);
			return result;		
		}
		@GetMapping("/menu/{menId}")
		public Menu getMenuById(@PathVariable("menId") Long menId) {
		   Menu cam = mealService.getMenuId(menId);
		   return cam;
		}	
//Order
		@PostMapping(value = "/post-order")
		public ResponseEntity<String> newOrder(@RequestBody OrderReqDto dtoNew) {
				Date cur_time=new Date();
				User usid = userRepository.findById(dtoNew.getMemberid()).get();
				User paid = userRepository.findById(dtoNew.getStaffid()).get();
				Product meid = productService.getproductId(dtoNew.getProductid());
				Order ord = new Order();
				ord.setMemberid(usid);
				ord.setProductid(meid);
				ord.setStaffid(paid);
				ord.setDatetime(cur_time);
				ord.setStatus(dtoNew.isStatus());
				mealService.SaveOrder(ord);
				return new ResponseEntity<>("New Order Successfuly added! ",HttpStatus.OK);	
		}
		@PostMapping(value = "/edit-order")
		public ResponseEntity<String> editOrderStatus(@RequestBody OrderReqDto dtoNew) {
				mealService.EditOrderStatus(dtoNew);
				return new ResponseEntity<>("Edit Order Status Successful! ",HttpStatus.OK);
		}
		@GetMapping("/order-list/{pid}")
		public List<Order> Orderlist(@PathVariable("pid") Long pid) {
			List<Order> result = mealService.listOrderPartner(pid);
			return result;		
		}

		
		@GetMapping("/orderlist")
		public List<Order> Orderedlist () {
			List<Order> result = mealService.listOrderStatus();
			return result;		
		}
		
		
		@GetMapping("/deliver-list")
		public List<Order> Deliverlist() {
			List<Order> result = mealService.listOrderStatus();
			return result;		
		}
		@GetMapping("/order/{ordId}")
		public Order getOrderById(@PathVariable("ordId") Long ordId) {
		   Order cam = mealService.getOrderId(ordId);
		   return cam;
		  }	
//Delivery
		@PostMapping(value = "/post-delivery")
		public ResponseEntity<String> newDelivery(@RequestBody DeliverReqDto dtoNew) {
				User raid = userRepository.findById(dtoNew.getRiderid()).get();
				User meid = userRepository.findById(dtoNew.getMemberid()).get();
				Order orid = mealService.getOrderId(dtoNew.getOrderid());
				Date cur_time=new Date();
				Deliver del = new Deliver();
				del.setOrderid(orid);
				del.setRiderid(raid);
				del.setMemberid(meid);
				del.setDatetime(cur_time);
				del.setStatus(dtoNew.isStatus());
				del.setDelivered(dtoNew.isDelivered());
				mealService.SaveDelivery(del);
				return new ResponseEntity<>("New Delivery Successfuly added! ",HttpStatus.OK);
		}
		@PostMapping(value = "/edit-delivery")
		public ResponseEntity<String> editDelivery(@RequestBody DeliverReqDto dtoNew) {
				mealService.EditDeliveryStatus(dtoNew);
				return new ResponseEntity<>("Edit Delivery Status Successful! ",HttpStatus.OK);
			
		}
		@GetMapping("/delivery/{memId}")
		public List<Deliver> getDeliveryList(@PathVariable("memId") Long delId) {
		   List<Deliver> cam = mealService.listDeliverMember(delId);
		   return cam;
		 }	
		@GetMapping("/delivery-list/{rid}")
		public List<Deliver> getDeliveryListRider(@PathVariable("rid") Long rId) {
		   List<Deliver> cam = mealService.listDeliverRider(rId);
		   return cam;
		 }
}
