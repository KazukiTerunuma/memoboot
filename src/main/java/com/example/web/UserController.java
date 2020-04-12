package com.example.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userService;

	@ModelAttribute
	UserRegistrationForm setUpForm() {
		return new UserRegistrationForm();
	}

	@GetMapping(path = "userRegistrationForm")
	String userRegistrationForm() {
		return "userRegistrationForm";
	}

	@PostMapping("/register")
	public String registerUser(@Validated UserRegistrationForm userRegistrationForm, BindingResult result) {
		if (result.hasErrors()) {
			return "/loginForm";
		}
		// USERテーブルにinsertする時の引数。
		User user = new User();
		BeanUtils.copyProperties(userRegistrationForm, user);

		userService.registUser(user);

		return "registrationResult";
	}
}
