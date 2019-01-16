package com.facultyavailability.service.util;

import com.facultyavailability.commons.dto.NewUser;
import com.facultyavailability.commons.dto.UserInformation;

public interface IUserService {

	public void setNewUser(NewUser newUser);

	public boolean hasUser(String userId);

	public UserInformation getUserInfo(String userId);

	public boolean validUser(String userId);
	
}
