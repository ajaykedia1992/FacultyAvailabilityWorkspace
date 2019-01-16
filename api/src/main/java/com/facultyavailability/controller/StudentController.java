package com.facultyavailability.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facultyavailability.commons.constant.UriConstants.Student;
import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;
import com.facultyavailability.commons.dto.StudentFacultyInfoResponse;
import com.facultyavailability.commons.util.Protocol;
import com.facultyavailability.service.util.IStudentService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;

@RestController
@Api(value = "student", description = "Student information")
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@ApiOperation(value = "fetch faculty availability info for a student")
	@ApiResponse(code = 200, message = "Successfully faculty information has been fetched", response = StudentFacultyInfoResponse.class)
	@RequestMapping(value = Student.STUDENT_FACULTY_INFO, method = RequestMethod.GET)
	public ResponseEntity<StudentFacultyInfoResponse> getFacultyAvailablilityInfo(@RequestParam(value = "faculty_id", required = false) String facultyId,
			@RequestParam(value = "course_code", required = false) String courseCode,
			@RequestParam(value = "branch_name", required = false) String branchName){
		StudentFacultyInfoResponse response = new StudentFacultyInfoResponse();
		List<FacultyAvailabilityInformation> facultyAvailabilityInformation = studentService.getFacultyAvailablilityInfo(facultyId, courseCode, branchName);
		if(facultyAvailabilityInformation != null) {
			
			response.setMessage("successfully information has been fetched");
		}
		else {
			response.setMessage("No data available for the search criteria");
		}
		response.setCode(HttpStatus.OK.toString());
		response.setFacultyAvailabilityInformation(facultyAvailabilityInformation);
   		response.setProtocol(Protocol.PROTOCOL_JSON);
   		return new ResponseEntity<StudentFacultyInfoResponse>(response, HttpStatus.OK);
	}
	
}
