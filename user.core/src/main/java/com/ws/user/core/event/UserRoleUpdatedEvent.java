package com.ws.user.core.event;

import java.util.List;

import com.ws.user.core.model.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleUpdatedEvent {

	private String id;
	
	private String userId;
	
	private List<Role> roles;
}
