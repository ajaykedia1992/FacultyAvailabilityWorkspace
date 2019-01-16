package com.facultyavailability.service.util;

import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;
import com.facultyavailability.commons.dto.FacultyLeaveInformation;

public interface IFacultyService {

	void setFacultyAvailablility(FacultyAvailabilityInformation facultyAvailabilityInformation);

	FacultyAvailabilityInformation getFacultyActivity(String facultyId);

	void deleteFacultyActivity(String facultyId);

	void setFacultyLeaveInfo(FacultyLeaveInformation facultyLeaveInformation);

	FacultyLeaveInformation getFacultyLeaveInfo(String facultyId, String date);

	void cancelFacultyLeaveInfo(FacultyLeaveInformation facultyLeaveInformation, String date);

	boolean deleteFacultyLeaveInfo(String facultyId, String date);

}
