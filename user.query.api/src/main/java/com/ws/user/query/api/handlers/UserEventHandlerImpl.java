package com.ws.user.query.api.handlers;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.user.core.event.UserRegisteredEvent;
import com.ws.user.core.event.UserRemovedEvent;
import com.ws.user.core.event.UserRoleUpdatedEvent;
import com.ws.user.core.event.UserUpdatedEvent;
import com.ws.user.core.model.Account;
import com.ws.user.core.model.Users;
import com.ws.user.query.api.RequestInterceptor;
import com.ws.user.query.api.repository.UserRepository;

@Service
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserEventHandlerImpl.class);

	@Autowired
	private UserRepository userRepo;

	@EventHandler
	@Override
	public void on(UserRegisteredEvent event) {
		event.getUser().setActive(true);
		Users user = userRepo.save(event.getUser());
		log.info("User registred successfully, id="+user.getId());
	}

	@EventHandler
	@Override
	public void on(UserUpdatedEvent event) {
		event.getUser().setActive(true);
		Users user = userRepo.save(event.getUser());
		log.info("User updated successfully, id="+user.getId());

	}

	@EventHandler
	@Override
	public void on(UserRemovedEvent event) {
		Users user = userRepo.findById(event.getId()).get();
		user.setActive(false);
		userRepo.save(user);
		log.info("User deleted successfully, id="+event.getId());
	}

	@EventHandler
	@Override
	public void on(UserRoleUpdatedEvent event) {
		Users user = userRepo.findById(event.getUserId()).get();
		if (user != null) {
			if (user.getAccount() == null) {
				user.setAccount(new Account("", "", event.getRoles()));
			} else {
				user.getAccount().setRoles(event.getRoles());
			}
			Users _user = userRepo.save(user);
			log.info("User deleted successfully, id="+_user.getId());
		}

	}

}
