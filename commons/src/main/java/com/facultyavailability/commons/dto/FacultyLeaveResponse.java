package com.facultyavailability.commons.dto;

import com.facultyavailability.commons.service.ServiceResponse;

public class FacultyLeaveResponse extends ServiceResponse{
	
	private static final long serialVersionUID = -8483866570050352141L;

	private FacultyLeaveInformation facultyLeaveInformation;

	public FacultyLeaveInformation getFacultyLeaveInformation() {
		return facultyLeaveInformation;
	}

	public void setFacultyLeaveInformation(FacultyLeaveInformation facultyLeaveInformation) {
		this.facultyLeaveInformation = facultyLeaveInformation;
	}
	
}
