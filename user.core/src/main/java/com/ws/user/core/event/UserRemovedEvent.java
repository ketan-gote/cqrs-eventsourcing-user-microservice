package com.ws.user.core.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRemovedEvent {

	private String id;	
}
