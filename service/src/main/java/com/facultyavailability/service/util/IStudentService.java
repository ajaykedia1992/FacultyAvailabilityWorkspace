package com.facultyavailability.service.util;

import java.util.List;

import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;

public interface IStudentService {

	List<FacultyAvailabilityInformation> getFacultyAvailablilityInfo(String facultyId, String courseCode,
			String branchName);

}
