package com.facultyavailability.repository.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;
import com.facultyavailability.repository.dao.IStudentDao;

@Repository("studentDaoImpl")
public class StudentDaoImpl implements IStudentDao {

	@Resource(name = "studentCassandraDao")
	private IStudentDao studentDao;
	
	@Override
	public List<FacultyAvailabilityInformation> getFacultyAvailablilityInfo(String facultyId, String courseCode,
			String branchName) {
		return studentDao.getFacultyAvailablilityInfo(facultyId, courseCode, branchName);
	}

}
