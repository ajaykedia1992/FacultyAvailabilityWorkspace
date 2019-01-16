package com.facultyavailability.commons.dto;

import java.util.List;

import com.facultyavailability.commons.service.ServiceResponse;

public class StudentFacultyInfoResponse extends ServiceResponse
{
	private static final long serialVersionUID = 676915417344566370L;
 
	private List<FacultyAvailabilityInformation> facultyAvailabilityInformation ;

	public List<FacultyAvailabilityInformation> getFacultyAvailabilityInformation() {
		return facultyAvailabilityInformation;
	}

	public void setFacultyAvailabilityInformation(List<FacultyAvailabilityInformation> facultyAvailabilityInformation) {
		this.facultyAvailabilityInformation = facultyAvailabilityInformation;
	}
	
}
