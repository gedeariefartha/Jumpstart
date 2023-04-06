package com.project.jumpstartbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.jumpstartbackend.dto.UserDto;
import com.project.jumpstartbackend.entity.User;
import com.project.jumpstartbackend.execption.ResourceNotFoundException;
import com.project.jumpstartbackend.repository.UserRepository;
import com.project.jumpstartbackend.service.MealService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MealService mealService;
    

    @GetMapping("/me")
    public UserDto getLoginUser(Authentication authentication) {
      User user = userRepository.findByEmail(authentication.getName()).get();

      if (user == null) {
        throw new ResourceNotFoundException("User", "Email", authentication.getName());
      }
      return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAddress(), user.getPhone(), user.getRole());// add more
    }
    @GetMapping("/member-list")
	public List<User> Memberlist() {
		List<User> result = mealService.listMember();
		return result;		
	}
    @GetMapping("/user-list")
	public List<User> Userlist() {
		List<User> result = mealService.listUser();
		return result;		
	}
    @GetMapping("/rider-list")
	public List<User> Riderlist() {
		List<User> result = mealService.listRider();
		return result;		
	}
    
}

