package com.facultyavailability.repository.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;
import com.facultyavailability.commons.dto.FacultyLeaveInformation;
import com.facultyavailability.repository.dao.IFacultyDao;

@Repository("facultyDaoImpl")
public class FacultyDaoImpl implements IFacultyDao{

	@Resource(name = "facultyCassandraDao")
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
		facultyDao.cancelFacultyLeaveInfo(facultyLeaveInformation, date);
		
	}

	@Override
	public boolean deleteFacultyLeaveInfo(String facultyId, String date) {
		return facultyDao.deleteFacultyLeaveInfo(facultyId, date);
		
	}

}
