package com.facultyavailability.commons.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class FacultyAvailabilityInformation {

	@ApiModelProperty(required = true)
	@NotNull
	@NotEmpty
	private String facultyId;

	@ApiModelProperty(required = true)
	private boolean availability;

	@ApiModelProperty(required = true)
	private String courseCode;
	
	@ApiModelProperty(required = true)
	private String branchName;

	@ApiModelProperty(required = true, value = "TA/RA/GCA/GRADER")
	private boolean nonFaculty;

	public String getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public boolean getNonFaculty() {
		return nonFaculty;
	}

	public void setNonFaculty(boolean nonFaculty) {
		this.nonFaculty = nonFaculty;
	}
	
	
}
