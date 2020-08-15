package com.mindBrowser.manageremployee.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindBrowser.manageremployee.entity.Manager;
import com.mindBrowser.manageremployee.jwt.JwtUtils;
import com.mindBrowser.manageremployee.payload.request.LoginRequest;
import com.mindBrowser.manageremployee.payload.request.SignupRequest;
import com.mindBrowser.manageremployee.payload.response.JwtResponse;
import com.mindBrowser.manageremployee.payload.response.MessageResponse;
import com.mindBrowser.manageremployee.repository.ManagerRepository;
import com.mindBrowser.manageremployee.service.StoreCurrentManager;
import com.mindBrowser.manageremployee.service.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mindbowser/auth")
public class ManagerAuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	ManagerRepository managerRepository;

	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

		//Sign In 
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
		
		Optional<Manager> manager ;
		
		if(managerRepository.existsById(userDetails.getId()))
		{
		 manager = managerRepository.findById( userDetails.getId());
		}
		else
		{	
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Manager Account Not Exist!"));
		}

			
		
		StoreCurrentManager.setManager(manager.get());
		
			
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 manager.get().getId(),
												 manager.get().getEmail(),
												 manager.get().getFirstname(),
												 manager.get().getLastname(),
												 manager.get().getAddress(),
												 manager.get().getDob(),
												 manager.get().getCompany()
												 ));
	}
	
	//Signup

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (managerRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Email is already in use!"));
		}

		
		// Create new user's account
		Manager manager = new Manager(signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getFirstname(), 
							 signUpRequest.getLastname(),
							 signUpRequest.getAddress(),
							 signUpRequest.getDob(),
							 signUpRequest.getCompany());
		
		managerRepository.save(manager);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
