package com.ws.user.query.api.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.ws.user.core.model.Users;
import com.ws.user.query.api.repository.UserRepository;

@Service
@RequestScope
public class UserQueryHandlerImpl implements UserQueryHandler {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Users> findAll() {
		return userRepo.findAll();
	}

	@Override
	public Users findById(String id) {
		return userRepo.findById(id).get();
	}

}
