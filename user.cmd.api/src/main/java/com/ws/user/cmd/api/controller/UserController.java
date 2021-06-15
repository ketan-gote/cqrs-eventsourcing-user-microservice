package com.ws.user.cmd.api.controller;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.user.cmd.api.command.RegisterUserCommand;
import com.ws.user.cmd.api.command.RemoveUserCommand;
import com.ws.user.cmd.api.command.UpdateUserCommand;
import com.ws.user.cmd.api.command.UpdateUserRoleCommand;
import com.ws.user.cmd.api.dto.UserCommandReponse;
import com.ws.user.core.model.Role;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

	@Autowired
	private CommandGateway commandGateway;

	@PostMapping("/")
	public ResponseEntity<UserCommandReponse<String>> registerUser(@RequestBody RegisterUserCommand command) {
		
		command.setId(UUID.randomUUID().toString());

		try {

			commandGateway.sendAndWait(command);
			String successMsg = "User registered successfully";
			log.info(successMsg + " Id=" + command.getId());
			return new ResponseEntity<UserCommandReponse<String>>(new UserCommandReponse<String>(command.getId(), successMsg, true), HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = "Error while registering user";
			log.error(errorMsg + " Id=" + command.getId() + ". Exception=" + e.getMessage());
			return new ResponseEntity<UserCommandReponse<String>>(new UserCommandReponse<String>(command.getId(), errorMsg, false), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserCommandReponse<String>> updateUser(@PathVariable("id") String id,
			@RequestBody UpdateUserCommand command) {
		command.setId(id);

		try {

			commandGateway.sendAndWait(command);
			String successMsg = "User updated successfully";
			log.info(successMsg + " Id=" + command.getId());
			return new ResponseEntity<UserCommandReponse<String>>(new UserCommandReponse<String>(successMsg, true), HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = "Error while registering user";
			log.error(errorMsg + " Id=" + command.getId() + ". Exception=" + e.getMessage());
			return new ResponseEntity<UserCommandReponse<String>>(new UserCommandReponse<String>(errorMsg, false), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserCommandReponse<String>> deleteUser(@PathVariable("id") String id) {

		try {
			RemoveUserCommand command = new RemoveUserCommand(id);
			commandGateway.sendAndWait(command);
			String successMsg = "User Removed successfully";
			log.info(successMsg + " Id=" + id);
			return new ResponseEntity<UserCommandReponse<String>>(new UserCommandReponse<String>(successMsg, true), HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = "Error while removing user";
			log.error(errorMsg + " Id=" + id + ". Exception=" + e.getMessage());
			return new ResponseEntity<UserCommandReponse<String>>(new UserCommandReponse<String>(errorMsg, false), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserCommandReponse<String>> updateUserRoles(@PathVariable("id") String id,
			@RequestBody List<Role> roles) {

		try {
			UpdateUserRoleCommand command = UpdateUserRoleCommand.builder().id(id).roles(roles).build();
			commandGateway.sendAndWait(command);
			String successMsg = "User updated successfully";
			log.info(successMsg + " Id=" + command.getId());
			return new ResponseEntity<UserCommandReponse<String>>(new UserCommandReponse<String>(successMsg, true), HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = "Error while registering user";
			log.error(errorMsg + " Id=" + id + ". Exception=" + e.getMessage());
			return new ResponseEntity<UserCommandReponse<String>>(new UserCommandReponse<String>(errorMsg, false), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
