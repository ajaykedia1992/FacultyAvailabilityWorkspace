package com.facultyavailability.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.facultyavailability.commons.constant.ConfigurationType;
import com.facultyavailability.commons.dto.IConfiguration;
import com.facultyavailability.commons.dto.MailConfiguration;
import com.facultyavailability.commons.exceptions.ReviewException;
import com.facultyavailability.repository.dao.IConfigurationDao;
import com.facultyavailability.service.util.IConfigurationService;


@Service
public class ConfigurationServiceImpl implements IConfigurationService {
	
	@Resource(name = "configurationDaoImpl")
	private IConfigurationDao configurationDao;

	@Override
	public void setConfiguration(ConfigurationType configurationType, IConfiguration configuration)
			throws ReviewException {
		configurationDao.updateConfigurationSettings(configurationType, configuration);
		
	}

	@Override
	public <T extends IConfiguration> T getConfiguration(ConfigurationType configurationType) throws ReviewException {
		T configuration = configurationDao.getConfigurationSettings(configurationType);
		return configuration;
	}

	@Override
	public <T extends IConfiguration> T getDefaultConfiguration(ConfigurationType configurationType)
			throws ReviewException {
		T configuration = configurationDao.getDefaultConfigurations(configurationType);
		return configuration;
	}

	@Override
	public MailConfiguration getMailConfiguration() {
		ConfigurationType configurationType = ConfigurationType.MAIL_CONFIG;
		MailConfiguration configuration = configurationDao.getConfigurationSettings(configurationType);
		return configuration;
	}


}
