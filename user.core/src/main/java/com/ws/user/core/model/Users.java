package com.ws.user.core.model;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document(collection =  "users")
public class Users {
	
	@Id
	private String id;

    @NotEmpty(message = "First name is mandatory")
	private String firstName;
	
    @NotEmpty(message = "Last name is mandatory")
	private String lastName;
	
    @NotEmpty(message = "Email is mandatory")
	private String email;
	
    @NotEmpty(message = "Gender is mandatory")
	private String gender;
	
    @NotEmpty(message = "DOB is mandatory")
	private Date dob;
	
    @NotNull(message = "Account details not provided")
    @Valid
	private Account account;
    
    private boolean active = true;
}
