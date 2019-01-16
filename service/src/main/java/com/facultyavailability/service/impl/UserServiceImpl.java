package com.facultyavailability.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.facultyavailability.commons.dto.NewUser;
import com.facultyavailability.commons.dto.UserInformation;
import com.facultyavailability.repository.dao.IUserDao;
import com.facultyavailability.service.util.IUserService;

@Service
public class UserServiceImpl implements IUserService {

//	private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource(name = "userDaoImpl")
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
