package com.facultyavailability.commons.dto;

import com.facultyavailability.commons.service.ServiceResponse;

public class FacultyAvailabilityResponse extends ServiceResponse{

	private static final long serialVersionUID = 6467206991756647537L;
	
	private FacultyAvailabilityInformation facultyAvailabilityInformation;

	public FacultyAvailabilityInformation getFacultyAvailabilityInformation() {
		return facultyAvailabilityInformation;
	}

	public void setFacultyAvailabilityInformation(FacultyAvailabilityInformation facultyAvailabilityInformation) {
		this.facultyAvailabilityInformation = facultyAvailabilityInformation;
	}

}
