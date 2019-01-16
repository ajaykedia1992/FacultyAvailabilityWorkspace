package com.facultyavailability.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facultyavailability.commons.constant.UriConstants.Faculty;
import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;
import com.facultyavailability.commons.dto.FacultyAvailabilityRequest;
import com.facultyavailability.commons.dto.FacultyAvailabilityResponse;
import com.facultyavailability.commons.dto.FacultyLeaveInformation;
import com.facultyavailability.commons.dto.FacultyLeaveRequest;
import com.facultyavailability.commons.dto.FacultyLeaveResponse;
import com.facultyavailability.commons.service.ServiceResponse;
import com.facultyavailability.commons.util.Protocol;
import com.facultyavailability.service.util.IFacultyService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;

@RestController
@Api(value = "faculty", description = "Faculty information")
public class FacultyController {

	@Autowired
	private IFacultyService facultyService;

	@ApiOperation(value = "faculty activity")
	@ApiResponse(code = 200, message = "Successfully faculty activity has been updated", response = ServiceResponse.class)
	@RequestMapping(value = Faculty.FACULTY_ACTIVITY, method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse> setFacultyAvailablility(
			@Valid @RequestBody FacultyAvailabilityRequest request) {
		ServiceResponse response = new ServiceResponse();
		facultyService.setFacultyAvailablility(request.getFacultyAvailabilityInformation());
		response.setMessage("successfully faculty activity has been updated");
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "faculty activity fetch")
	@ApiResponse(code = 200, message = "Successfully faculty activity has been fetched", response = FacultyAvailabilityResponse.class)
	@RequestMapping(value = Faculty.FACULTY_ACTIVITY_INFO, method = RequestMethod.GET)
	public ResponseEntity<FacultyAvailabilityResponse> setFacultyAvailablility(
			@RequestParam(value = "faculty_id", required = true) String facultyId) {
		FacultyAvailabilityResponse response = new FacultyAvailabilityResponse();
		FacultyAvailabilityInformation facultyAvailabilityInformation = facultyService.getFacultyActivity(facultyId);
		if (facultyAvailabilityInformation == null) {
			response.setMessage("No Such record found");
		} else {
			response.setMessage("successfully faculty activity has been fetched");
		}
		response.setFacultyAvailabilityInformation(facultyAvailabilityInformation);
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<FacultyAvailabilityResponse>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "faculty activity deleted")
	@ApiResponse(code = 200, message = "Successfully faculty activity has been deleted", response = ServiceResponse.class)
	@RequestMapping(value = Faculty.FACULTY_AVAILABILITY_INFO_DELETE, method = RequestMethod.DELETE)
	public ResponseEntity<ServiceResponse> deleteFacultyAvailablility(
			@RequestParam(value = "faculty_id", required = true) String facultyId) {
		ServiceResponse response = new ServiceResponse();
		facultyService.deleteFacultyActivity(facultyId);
		response.setMessage("successfully faculty activity has been fetched");
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "faculty leave info")
	@ApiResponse(code = 200, message = "Successfully faculty leave info has been updated", response = ServiceResponse.class)
	@RequestMapping(value = Faculty.FACULTY_LEAVE_INFO, method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse> setFacultyLeaveInfo(@Valid @RequestBody FacultyLeaveRequest request) {
		ServiceResponse response = new ServiceResponse();
		facultyService.setFacultyLeaveInfo(request.getFacultyLeaveInformation());
		response.setMessage("successfully faculty leave info has been updated");
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "faculty leave info fetch")
	@ApiResponse(code = 200, message = "Successfully faculty leave info has been fetched", response = FacultyLeaveResponse.class)
	@RequestMapping(value = Faculty.FACULTY_LEAVE_INFO_FETCH, method = RequestMethod.GET)
	public ResponseEntity<FacultyLeaveResponse> getFacultyLeaveInfo(
			@RequestParam(value = "faculty_id", required = true) String facultyId,
			@RequestParam(value = "date", required = true, defaultValue = "mm/dd/yyyy") String date) {
		FacultyLeaveResponse response = new FacultyLeaveResponse();
		FacultyLeaveInformation facultyLeaveInformation = facultyService.getFacultyLeaveInfo(facultyId, date);
		if (facultyLeaveInformation == null) {
			response.setMessage("No Such record found");
		} else {
			response.setMessage("successfully faculty leave info has been fetched");
		}
		response.setFacultyLeaveInformation(facultyLeaveInformation);
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<FacultyLeaveResponse>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "faculty leave info cancelled")
	@ApiResponse(code = 200, message = "Successfully faculty leave info has been cancelled", response = ServiceResponse.class)
	@RequestMapping(value = Faculty.FACULTY_LEAVE_INFO_CANCEL, method = RequestMethod.DELETE)
	public ResponseEntity<ServiceResponse> cancelFacultyLeaveInfo(@Valid @RequestBody FacultyLeaveRequest request,
			@RequestParam(value = "date", required = true, defaultValue = "mm/dd/yyyy") String date) {
		ServiceResponse response = new ServiceResponse();
		facultyService.cancelFacultyLeaveInfo(request.getFacultyLeaveInformation(), date);
		response.setMessage("successfully faculty leave info has been cancelled");
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "faculty leave info delete entirely")
	@ApiResponse(code = 200, message = "Successfully faculty leave info has been deleted entirely", response = ServiceResponse.class)
	@RequestMapping(value = Faculty.FACULTY_LEAVE_INFO_DELETE, method = RequestMethod.DELETE)
	public ResponseEntity<ServiceResponse> deleteFacultyLeaveInfo(
			@RequestParam(value = "faculty_id", required = true) String facultyId,
			@RequestParam(value = "date", required = true, defaultValue = "mm/dd/yyyy") String date) {
		ServiceResponse response = new ServiceResponse();
		boolean status = facultyService.deleteFacultyLeaveInfo(facultyId, date);
		if (status) {
			response.setMessage("successfully faculty leave info has been deleted entirely");
		}else {
			response.setMessage("no data is available for the search criteria");
		}
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}
}
