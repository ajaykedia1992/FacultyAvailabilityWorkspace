package com.facultyavailability.repository.dao;

import java.util.List;

import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;

public interface IStudentDao {

	List<FacultyAvailabilityInformation> getFacultyAvailablilityInfo(String facultyId, String courseCode,
			String branchName);

}
