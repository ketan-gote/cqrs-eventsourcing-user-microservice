package com.ws.user.cmd.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class UserCommandReponse<T> extends BaseReponse {

	private T data;

	public UserCommandReponse(T data, String message, boolean success) {
		super(message, success);
		this.data = data;
	}

	public UserCommandReponse(String message, boolean success) {
		super(message, success);
	}

}
