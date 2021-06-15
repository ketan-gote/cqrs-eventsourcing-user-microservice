package com.ws.user.query.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class UserQueryResponse<T> extends BaseReponse {

	private T data;

	public UserQueryResponse(boolean success, String message, T data) {
		super(message, success);
		this.data = data;
	}
	
	public UserQueryResponse(boolean success, String message) {
		super(message, success);
	}
}
