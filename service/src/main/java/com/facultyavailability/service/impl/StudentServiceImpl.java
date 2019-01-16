package com.facultyavailability.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;
import com.facultyavailability.repository.dao.IStudentDao;
import com.facultyavailability.service.util.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Resource(name = "studentDaoImpl")
    private IStudentDao studentDao;
	
	@Override
	public List<FacultyAvailabilityInformation> getFacultyAvailablilityInfo(String facultyId, String courseCode,
			String branchName) {
		return studentDao.getFacultyAvailablilityInfo(facultyId, courseCode, branchName);
	}

}
