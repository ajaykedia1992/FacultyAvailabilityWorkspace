package com.facultyavailability.commons.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class FacultyLeaveInformation {

	@ApiModelProperty(required = true)
	@NotNull
	@NotEmpty
	String facultyId;
	
	@ApiModelProperty(required = true)
	@NotNull
	@NotEmpty
	String date;
	
	@ApiModelProperty(required = true, value = "mm/dd/yyyy")
	List<TimeSheet> leaveAppointment;

	public String getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

	public List<TimeSheet> getLeaveAppointment() {
		return leaveAppointment;
	}

	public void setLeaveAppointment(List<TimeSheet> leaveAppointment) {
		this.leaveAppointment = leaveAppointment;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
