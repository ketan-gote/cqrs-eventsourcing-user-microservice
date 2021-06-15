package com.ws.user.cmd.api.aggregates;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ws.user.cmd.api.RequestInterceptor;
import com.ws.user.cmd.api.command.RegisterUserCommand;
import com.ws.user.cmd.api.command.RemoveUserCommand;
import com.ws.user.cmd.api.command.UpdateUserCommand;
import com.ws.user.cmd.api.command.UpdateUserRoleCommand;
import com.ws.user.core.event.UserRegisteredEvent;
import com.ws.user.core.event.UserRemovedEvent;
import com.ws.user.core.event.UserRoleUpdatedEvent;
import com.ws.user.core.event.UserUpdatedEvent;
import com.ws.user.core.model.Account;
import com.ws.user.core.model.Users;

@Aggregate
public class UserAggregate {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserAggregate.class);

	@AggregateIdentifier
	private String id;
	
	private Users user;
	
	public UserAggregate() {
		
	}
	
	@CommandHandler
	public UserAggregate(RegisterUserCommand command) {
		log.debug("RegisterUser Command received, id"+command.getId());
		command.getUser().setId(command.getId());
		
		UserRegisteredEvent event = UserRegisteredEvent.builder()
						   .id(command.getId())
						   .user(command.getUser())
						   .build();
			
		AggregateLifecycle.apply(event);
	}
	
	@EventSourcingHandler
	public void on(UserRegisteredEvent event) {
		log.debug("UserRegistered Event received, id"+event.getId());

		this.id = event.getId();
		this.user = event.getUser();
	}
	
	@CommandHandler
	public void handle(UpdateUserCommand command) {
		log.debug("UpdateUser Command received, id="+command.getId());

		command.getUser().setId(command.getId());

		UserUpdatedEvent event = UserUpdatedEvent.builder()
				   .id(UUID.randomUUID().toString())
				   .user(command.getUser())
				   .build();
	
		AggregateLifecycle.apply(event);
	}	
	
	@EventSourcingHandler
	public void on(UserUpdatedEvent event) {
		log.debug("UserUpdated Event received, id="+event.getUser().getId());
		
		this.user = event.getUser();
	}
	
	@CommandHandler
	public void handle(RemoveUserCommand command) {
		log.debug("RemoveUser Command received, id="+command.getId());

		UserRemovedEvent event = new UserRemovedEvent(command.getId());
		AggregateLifecycle.apply(event);
	}

	
	@EventSourcingHandler
	public void on(UserRemovedEvent event) {
		log.debug("UserRemoved Event received, id="+event.getId());

		AggregateLifecycle.markDeleted();
	}
	
	
	@CommandHandler
	public void handle(UpdateUserRoleCommand command) {
		log.debug("UpdateUserRole Command received, id="+command.getId());

		UserRoleUpdatedEvent event = UserRoleUpdatedEvent.builder()
				.id(UUID.randomUUID().toString())
				.userId(command.getId())
				.roles(command.getRoles())
				.build();
	
		AggregateLifecycle.apply(event);
	}	
	
	@EventSourcingHandler
	public void on(UserRoleUpdatedEvent event) {
		log.debug("UserRoleUpdated Event received, id="+event.getId());

		if(this.user.getAccount()!=null) {
			this.user.getAccount().setRoles(event.getRoles());
		} else {
			this.user.setAccount(new Account("","", event.getRoles()));
		}
		
	}
	
	
	
	
}
