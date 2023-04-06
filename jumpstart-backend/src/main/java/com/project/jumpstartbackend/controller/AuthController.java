package com.project.jumpstartbackend.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.jumpstartbackend.dto.ApiResponse;
import com.project.jumpstartbackend.dto.AuthResponse;
import com.project.jumpstartbackend.dto.LoginRequest;
import com.project.jumpstartbackend.dto.SignUpRequest;
import com.project.jumpstartbackend.entity.AuthProvider;
import com.project.jumpstartbackend.entity.Role;
import com.project.jumpstartbackend.entity.User;
import com.project.jumpstartbackend.repository.RoleRepository;
import com.project.jumpstartbackend.repository.UserRepository;
import com.project.jumpstartbackend.security.JwtGenerator;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtGenerator jwtGenerator;
    
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@Valid @RequestBody SignUpRequest registerDto) throws Exception {

        if (userRepository.existsByEmail(registerDto.getEmail())) {
          return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setAddress(registerDto.getAddress());
        user.setPhone(registerDto.getPhone());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setProvider(AuthProvider.local);
        user.setRole(registerDto.getRole());

        User newUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("api/users/me")
            .buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(location)
            .body(new ApiResponse(true, "User registered successfully"));

      }
    @GetMapping("/role-list")
	public List<Role> Rolelist() {
		List<Role> result = roleRepository.findAll();
		
		return result;		
	}
    @GetMapping("/{role}")
	public Role roleDetail(@PathVariable("role") long rid) {
		Role detail = roleRepository.findById(rid).get();
		return detail;		
	}
}

