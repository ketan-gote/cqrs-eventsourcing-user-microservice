package com.ws.user.query.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.user.core.model.Users;
import com.ws.user.query.api.dto.UserQueryResponse;
import com.ws.user.query.api.handlers.UserQueryHandler;

@RestController
@RequestMapping("/api/v1/user")
public class UserQueryController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserQueryController.class);

	@Autowired
	private UserQueryHandler userQuery;

	@GetMapping("/")
	public ResponseEntity<UserQueryResponse<List<Users>>> findAll() {
		List<Users> users = userQuery.findAll();
		if (users != null && users.size() > 0) {
			UserQueryResponse<List<Users>> userResponse = new UserQueryResponse<List<Users>>(true, "Users reterived successfully", users);
			return new ResponseEntity<UserQueryResponse<List<Users>>>(userResponse, HttpStatus.OK);
		} else {
			UserQueryResponse<List<Users>> userResponse = new UserQueryResponse<List<Users>>(false, "Unable to get users");
			return new ResponseEntity<UserQueryResponse<List<Users>>>(userResponse, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserQueryResponse<Users>> findById(@PathVariable("id") String id) {
		Users user = userQuery.findById(id);
		if (user != null) {
			UserQueryResponse<Users> userResponse = new UserQueryResponse<Users>(true, "User reterived successfully", user);
			return new ResponseEntity<UserQueryResponse<Users>>(userResponse, HttpStatus.OK);
		} else {
			UserQueryResponse<Users> userResponse = new UserQueryResponse<Users>(false, "Unable to get user");
			return new ResponseEntity<UserQueryResponse<Users>>(userResponse, HttpStatus.NOT_FOUND);
		}

	}
}
