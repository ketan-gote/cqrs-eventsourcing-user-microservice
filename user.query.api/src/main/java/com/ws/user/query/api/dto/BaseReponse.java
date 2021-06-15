package com.ws.user.query.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseReponse {

	private String message;
	private boolean success;
	
	
}
