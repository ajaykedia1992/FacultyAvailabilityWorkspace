package com.facultyavailability.repository.dao;

import com.facultyavailability.commons.constant.ConfigurationType;
import com.facultyavailability.commons.dto.IConfiguration;

public interface IConfigurationDao {

	void updateConfigurationSettings(ConfigurationType configurationType, IConfiguration configuration);

	<T extends IConfiguration> T getConfigurationSettings(ConfigurationType configurationType);

	<T extends IConfiguration> T getDefaultConfigurations(ConfigurationType configurationType);

}
