package com.facultyavailability.commons.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.facultyavailability.commons.service.ServiceRequest;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class FacultyAvailabilityRequest extends ServiceRequest{

	private static final long serialVersionUID = 2224695316026591102L;
	
	@ApiModelProperty
	@NotNull
	@Valid
	private FacultyAvailabilityInformation facultyAvailabilityInformation;

	public FacultyAvailabilityInformation getFacultyAvailabilityInformation() {
		return facultyAvailabilityInformation;
	}

	public void setFacultyAvailabilityInformation(FacultyAvailabilityInformation facultyAvailabilityInformation) {
		this.facultyAvailabilityInformation = facultyAvailabilityInformation;
	}

}
