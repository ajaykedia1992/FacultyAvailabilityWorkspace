package com.facultyavailability.commons.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.facultyavailability.commons.service.ServiceRequest;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class FacultyLeaveRequest extends ServiceRequest {

	private static final long serialVersionUID = 4798985349982949278L;
	
	@ApiModelProperty
	@NotNull
	@Valid
	private FacultyLeaveInformation facultyLeaveInformation;

	public FacultyLeaveInformation getFacultyLeaveInformation() {
		return facultyLeaveInformation;
	}

	public void setFacultyLeaveInformation(FacultyLeaveInformation facultyLeaveInformation) {
		this.facultyLeaveInformation = facultyLeaveInformation;
	}
	
}
