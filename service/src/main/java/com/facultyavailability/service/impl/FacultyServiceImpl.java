package com.facultyavailability.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;
import com.facultyavailability.commons.dto.FacultyLeaveInformation;
import com.facultyavailability.repository.dao.IFacultyDao;
import com.facultyavailability.service.util.IFacultyService;

@Service
public class FacultyServiceImpl implements IFacultyService {

	@Resource(name = "facultyDaoImpl")
    private IFacultyDao facultyDao;
	
	@Override
	public void setFacultyAvailablility(FacultyAvailabilityInformation facultyAvailabilityInformation) {
		facultyDao.setFacultyAvailablility(facultyAvailabilityInformation);
		
	}

	@Override
	public FacultyAvailabilityInformation getFacultyActivity(String facultyId) {
		return facultyDao.getFacultyActivity(facultyId);
	}

	@Override
	public void deleteFacultyActivity(String facultyId) {
		facultyDao.deleteFacultyActivity(facultyId);
		
	}

	@Override
	public void setFacultyLeaveInfo(FacultyLeaveInformation facultyLeaveInformation) {
		facultyDao.setFacultyLeaveInfo(facultyLeaveInformation);
	}

	@Override
	public FacultyLeaveInformation getFacultyLeaveInfo(String facultyId, String date) {
		return facultyDao.getFacultyLeaveInfo(facultyId, date);
	}

	@Override
	public void cancelFacultyLeaveInfo(FacultyLeaveInformation facultyLeaveInformation, String date) {
		facultyDao.cancelFacultyLeaveInfo(facultyLeaveInformation,date);
		
	}

	@Override
	public boolean deleteFacultyLeaveInfo(String facultyId, String date) {
		return facultyDao.deleteFacultyLeaveInfo(facultyId, date);
		
	}

}
