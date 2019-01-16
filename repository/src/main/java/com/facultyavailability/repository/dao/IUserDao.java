package com.facultyavailability.repository.dao;

import com.facultyavailability.commons.dto.NewUser;
import com.facultyavailability.commons.dto.UserInformation;

public interface IUserDao {

	void setNewUser(NewUser newUser);

	boolean hasUser(String userId);

	UserInformation getUserInfo(String userId);

	boolean validUser(String userId);

}
