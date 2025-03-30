package com.springmicroservicedemo.controller;

import com.springmicroservicedemo.entity.Users;
import com.springmicroservicedemo.service.UserService;
import com.springmicroservicedemo.vo.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public Users saveUser(@RequestBody Users user) {
		log.info("Inside saveUser of UserController");
		return userService.saveUser(user);
	}

	// Need to define fallback method because this method will call Department
	// service from service class

	@GetMapping("/{id}")	
	public ResponseTemplateVO getUserIdWithDepartment(@PathVariable("id") Long id) {
		log.info("Inside getUserIdWithDepartment of UserController");
		return userService.getUserIdWithDepartment(id);
	}	
	
}
