package com.ws.user.cmd.api.command;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.ws.user.core.model.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterUserCommand {

	@TargetAggregateIdentifier
	private String id;

	@Valid
	@NotNull(message = "User details not provided")
	private Users user;
}
