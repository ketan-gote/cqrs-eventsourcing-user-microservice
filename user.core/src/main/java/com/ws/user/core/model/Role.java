package com.ws.user.core.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	
	READ, WRITE;

	@Override
	public String getAuthority() {
		return name();
	}

}
