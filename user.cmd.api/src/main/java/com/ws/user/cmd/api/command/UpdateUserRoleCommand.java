package com.ws.user.cmd.api.command;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.ws.user.core.model.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRoleCommand {

	@TargetAggregateIdentifier
	private String id;
	
	private List<Role> roles;
}
