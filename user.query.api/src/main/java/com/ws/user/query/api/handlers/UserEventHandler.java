package com.ws.user.query.api.handlers;

import com.ws.user.core.event.UserRegisteredEvent;
import com.ws.user.core.event.UserRemovedEvent;
import com.ws.user.core.event.UserRoleUpdatedEvent;
import com.ws.user.core.event.UserUpdatedEvent;

public interface UserEventHandler {

	public void on(UserRegisteredEvent event);
	
	public void on(UserUpdatedEvent event);
	
	public void on(UserRemovedEvent event);
	
	public void on(UserRoleUpdatedEvent event);
}
