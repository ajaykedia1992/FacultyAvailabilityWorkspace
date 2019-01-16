package com.facultyavailability.repository.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;
import com.facultyavailability.repository.dao.CassandraClient;
import com.facultyavailability.repository.dao.IStudentDao;

@Repository("studentCassandraDao")
public class StudentCassandraDao implements IStudentDao {

	private static final String FACULTY_COLUMNFAMILY = "faculty_info";
	private static final String FACULTY_KEYSPACE = "faculty_platform";
	private static final String FACULTY_LEAVE_COLUMNFAMILY = "faculty_leave_info";

	@Autowired
	private CassandraClient cassandraClient;

	@Override
	public List<FacultyAvailabilityInformation> getFacultyAvailablilityInfo(String facultyId, String courseCode,
			String branchName) {
		Select select = QueryBuilder.select().all().from(FACULTY_KEYSPACE, FACULTY_COLUMNFAMILY);
		if (facultyId != null && courseCode != null && branchName != null) {
			select.where().and(QueryBuilder.eq("faculty_id", facultyId)).and(QueryBuilder.eq("course_code", courseCode))
					.and(QueryBuilder.eq("branch_name", branchName));
			select.allowFiltering();
		} else if (facultyId != null && courseCode != null) {
			select.where().and(QueryBuilder.eq("faculty_id", facultyId))
					.and(QueryBuilder.eq("course_code", courseCode));
		} else if (facultyId != null && branchName != null) {
			select.where().and(QueryBuilder.eq("faculty_id", facultyId))
					.and(QueryBuilder.eq("branch_name", branchName));
		} else if (courseCode != null && branchName != null) {
			select.where().and(QueryBuilder.eq("branch_name", branchName))
					.and(QueryBuilder.eq("course_code", courseCode));
			select.allowFiltering();
		} else if (facultyId != null && courseCode == null && branchName == null) {
			select.where().and(QueryBuilder.eq("faculty_id", facultyId));
		} else if (facultyId == null && courseCode != null && branchName == null) {
			select.where().and(QueryBuilder.eq("course_code", courseCode));
		} else if (facultyId == null && courseCode == null && branchName != null) {
			select.where().and(QueryBuilder.eq("branch_name", branchName));
		}
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		List<FacultyAvailabilityInformation> facultyAvailabilityInformations = null;
		if (iterator.hasNext()) {
			facultyAvailabilityInformations = new ArrayList<>();
			while(iterator.hasNext()) {
				FacultyAvailabilityInformation facultyAvailabilityInformation = new FacultyAvailabilityInformation();
				Row row = iterator.next();
				facultyAvailabilityInformation.setFacultyId(row.getString("faculty_id"));
				facultyAvailabilityInformation.setAvailability(row.getBool("availability"));
				facultyAvailabilityInformation.setBranchName(row.getString("branch_name"));
				facultyAvailabilityInformation.setCourseCode(row.getString("course_code"));
				facultyAvailabilityInformation.setNonFaculty(row.getBool("nonFaculty"));
				facultyAvailabilityInformations.add(facultyAvailabilityInformation);
			}
		}
		return facultyAvailabilityInformations;
	}
}
