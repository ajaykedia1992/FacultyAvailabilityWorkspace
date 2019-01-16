package com.facultyavailability.service.util;

import com.facultyavailability.commons.constant.ConfigurationType;
import com.facultyavailability.commons.dto.IConfiguration;
import com.facultyavailability.commons.dto.MailConfiguration;
import com.facultyavailability.commons.exceptions.ReviewException;

/**
 * Created by ajay on 4/2/16.
 */

public interface IConfigurationService {
    void setConfiguration(ConfigurationType configurationType,
			IConfiguration configuration) throws ReviewException;

	<T extends IConfiguration> T getConfiguration(
			ConfigurationType configurationType) throws ReviewException;

	<T extends IConfiguration> T getDefaultConfiguration(
			ConfigurationType configurationType) throws ReviewException;
	
	MailConfiguration  getMailConfiguration();

}
