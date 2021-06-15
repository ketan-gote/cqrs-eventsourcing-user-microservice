package com.ws.user.core.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Account {

	@Size(min = 3, message = "User Name must have alteast 2 characters")
	private String username;

	@Size(min = 5, message = "Password must have alteast 5 characters")
	private String password;

	@NotNull(message = "Please specify aleast one role")
	private List<Role> roles;
}
