package com.facultyavailability.repository.dao.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.querybuilder.Update.Assignments;
import com.facultyavailability.commons.constant.ConfigurationType;
import com.facultyavailability.commons.dto.IConfiguration;
import com.facultyavailability.commons.json.JsonCodec;
import com.facultyavailability.repository.dao.CassandraClient;
import com.facultyavailability.repository.dao.IConfigurationDao;

@Repository("configurationCassandraDao")
public class ConfigurationCasssandraDao implements IConfigurationDao {

	private static final String CONFIGURATION_COLUMNFAMILY = "configurations";
	private static final String FACULTY_KEYSPACE = "faculty_platform";
	@Autowired
	private CassandraClient cassandraClient;

	@Override
	public void updateConfigurationSettings(ConfigurationType configurationType, IConfiguration configuration) {
		Update update = QueryBuilder.update(FACULTY_KEYSPACE, CONFIGURATION_COLUMNFAMILY);
		update.where().and(QueryBuilder.eq("configuration_type", configurationType.getConfigType()));
		Assignments assignments = update.with();
		assignments.and(QueryBuilder.set("configuration", JsonCodec.getInstance().toJson(configuration)));
		cassandraClient.getSession().execute(update);

	}

	@Override
	public <T extends IConfiguration> T getConfigurationSettings(ConfigurationType configurationType) {
		Select select = QueryBuilder.select().all().from(FACULTY_KEYSPACE, CONFIGURATION_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("configuration_type", configurationType.getConfigType()));

		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		// List<Row> rows = CassandraUtils.extractRowsIfExists(result);
		String objectJSON = "";
		if (iterator.hasNext()) {
			objectJSON = iterator.next().getString("configuration");
			@SuppressWarnings("unchecked")
			T configuration = (T) JsonCodec.getInstance().fromJson(objectJSON, configurationType.getConfigClass());
			return configuration;
		}
		// System.out.println(objectJSON);
		return null;
	}

	@Override
	public <T extends IConfiguration> T getDefaultConfigurations(ConfigurationType configurationType) {
		Select select = QueryBuilder.select().all().from(FACULTY_KEYSPACE, CONFIGURATION_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("configuration_type", configurationType.getConfigType()));

		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		// List<Row> rows = CassandraUtils.extractRowsIfExists(result);
		String objectJSON = "";
		if (iterator.hasNext()) {
			objectJSON = iterator.next().getString("default_configuration");
			@SuppressWarnings("unchecked")
			T configuration = (T) JsonCodec.getInstance().fromJson(objectJSON, configurationType.getConfigClass());
			return configuration;
		}
		return null;
	}

}
