package com.ws.user.query.api.handlers;

import java.util.List;

import com.ws.user.core.model.Users;

public interface UserQueryHandler {

	public List<Users> findAll();
	
	public Users findById(String id);
}
