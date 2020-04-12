package com.example.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegistrationForm {
	@NotNull
	@Size(min = 1, max = 127)
	private String username;
	@NotNull
	@Size(min = 1, max = 127)
	private String encodedPassword;

}
