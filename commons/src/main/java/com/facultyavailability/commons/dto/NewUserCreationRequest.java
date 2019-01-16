package com.facultyavailability.commons.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.facultyavailability.commons.service.ServiceRequest;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class NewUserCreationRequest extends ServiceRequest {

	private static final long serialVersionUID = -2989803757796159883L;
	
	@ApiModelProperty
	@NotNull
	@Valid
	private NewUser newUser;

	public NewUser getNewUser() {
		return newUser;
	}

	public void setNewUser(NewUser newUser) {
		this.newUser = newUser;
	}
	
}
