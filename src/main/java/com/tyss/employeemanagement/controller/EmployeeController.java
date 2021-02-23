package com.tyss.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.employeemanagement.dto.AuthRequest;
import com.tyss.employeemanagement.dto.ExpereincePersonalDetails;
import com.tyss.employeemanagement.dto.FresherPersonalDetails;
import com.tyss.employeemanagement.service.ExperienceDetailsService;
import com.tyss.employeemanagement.service.FresherDetailsService;
import com.tyss.employeemanagement.service.JwtUtil;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	@Autowired
	ExperienceDetailsService experienceDetailsService;

	@Autowired
	FresherDetailsService fresherDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/experiencedetails")
	public ResponseEntity<Void> ExperienceEmployeeDetails(
			@RequestBody ExpereincePersonalDetails experiencePersonalDetails) {
		experiencePersonalDetails = experienceDetailsService.experienceDetails(experiencePersonalDetails);
		if(experiencePersonalDetails.getPersonalDetailsId()!=0) {
			return ResponseEntity.accepted().build();
		}return ResponseEntity.notFound().build();

		
	}

	@PostMapping("/fresherdetails")
	public ResponseEntity<Void> ExperienceEmployeeDetails(@RequestBody FresherPersonalDetails fresherPersonalDetails) {
		fresherPersonalDetails = fresherDetailsService.fresherDetails(fresherPersonalDetails);
		if(fresherPersonalDetails.getPersonalDetailsId()!=0) {
			System.out.println("persisted");
			return ResponseEntity.accepted().build();
		}
		System.out.println("not persisted");
		return ResponseEntity.notFound().build();
		
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}
}
