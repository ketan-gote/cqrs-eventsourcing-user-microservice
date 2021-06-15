package com.ws.user.core.event;

import com.ws.user.core.model.Users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdatedEvent {

	private String id;
	
	private Users user;
	
	
}
