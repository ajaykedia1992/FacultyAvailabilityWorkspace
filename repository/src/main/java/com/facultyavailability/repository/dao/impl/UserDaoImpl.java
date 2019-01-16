package com.facultyavailability.repository.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.facultyavailability.commons.dto.NewUser;
import com.facultyavailability.commons.dto.UserInformation;
import com.facultyavailability.repository.dao.IUserDao;

@Repository("userDaoImpl")
public class UserDaoImpl implements IUserDao {

	@Resource(name = "userCassandraDao")
	private IUserDao userDao;
	
	@Override
	public void setNewUser(NewUser newUser) {
		
		userDao.setNewUser(newUser);
		
	}

	@Override
	public boolean hasUser(String userId) {
		return userDao.hasUser(userId);
	}

	@Override
	public UserInformation getUserInfo(String userId) {
		return userDao.getUserInfo(userId);
	}

	@Override
	public boolean validUser(String userId) {
		return userDao.validUser(userId);
	}

}
