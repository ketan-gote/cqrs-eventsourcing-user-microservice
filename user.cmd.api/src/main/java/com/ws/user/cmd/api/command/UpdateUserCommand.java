package com.ws.user.cmd.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.ws.user.core.model.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserCommand {

	@TargetAggregateIdentifier
	private String id;
	
	private Users user;
}
