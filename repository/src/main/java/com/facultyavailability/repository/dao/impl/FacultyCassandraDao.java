package com.facultyavailability.repository.dao.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.querybuilder.Update.Assignments;
import com.facultyavailability.commons.dto.FacultyAvailabilityInformation;
import com.facultyavailability.commons.dto.FacultyLeaveInformation;
import com.facultyavailability.commons.dto.TimeSheet;
import com.facultyavailability.commons.json.JsonCodec;
import com.facultyavailability.repository.dao.CassandraClient;
import com.facultyavailability.repository.dao.IFacultyDao;

@Repository("facultyCassandraDao")
public class FacultyCassandraDao implements IFacultyDao {

	private static final String FACULTY_COLUMNFAMILY = "faculty_info";
	private static final String FACULTY_KEYSPACE = "faculty_platform";
	private static final String FACULTY_LEAVE_COLUMNFAMILY = "faculty_leave_info";

	@Autowired
	private CassandraClient cassandraClient;

	@Override
	public void setFacultyAvailablility(FacultyAvailabilityInformation facultyAvailabilityInformation) {
		UUID object_id = UUID.randomUUID();
		Update update = QueryBuilder.update(FACULTY_KEYSPACE, FACULTY_COLUMNFAMILY);
		update.where().and(QueryBuilder.eq("faculty_id", facultyAvailabilityInformation.getFacultyId()));
		Assignments assignments = update.with();
		assignments.and(QueryBuilder.set("availability", facultyAvailabilityInformation.getAvailability()));
		assignments.and(QueryBuilder.set("object_id", object_id));
		assignments.and(QueryBuilder.set("nonFaculty", facultyAvailabilityInformation.getNonFaculty()));
		assignments.and(QueryBuilder.set("branch_name", facultyAvailabilityInformation.getBranchName()));
		assignments.and(QueryBuilder.set("course_code", facultyAvailabilityInformation.getCourseCode()));
		cassandraClient.getSession().execute(update);
	}

	@Override
	public FacultyAvailabilityInformation getFacultyActivity(String facultyId) {
		Select select = QueryBuilder.select().all().from(FACULTY_KEYSPACE, FACULTY_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("faculty_id", facultyId));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			Row row = iterator.next();
			FacultyAvailabilityInformation facultyAvailabilityInformation = new FacultyAvailabilityInformation();
			facultyAvailabilityInformation.setAvailability(row.getBool("availability"));
			facultyAvailabilityInformation.setBranchName(row.getString("branch_name"));
			facultyAvailabilityInformation.setCourseCode(row.getString("course_code"));
			facultyAvailabilityInformation.setFacultyId(facultyId);
			facultyAvailabilityInformation.setNonFaculty(row.getBool("nonFaculty"));
			return facultyAvailabilityInformation;
		}
		return null;
	}

	@Override
	public void deleteFacultyActivity(String facultyId) {
		String query = "delete from " + FACULTY_KEYSPACE + "." + FACULTY_COLUMNFAMILY + " where faculty_id = '"
				+ facultyId + "'";
		cassandraClient.getSession().execute(query);
	}

	@Override
	public void setFacultyLeaveInfo(FacultyLeaveInformation facultyLeaveInformation) {
		String uuid = facultyLeaveInformation.getFacultyId() + facultyLeaveInformation.getDate();
		UUID objectId = UUID.nameUUIDFromBytes(uuid.getBytes());
		Update update = QueryBuilder.update(FACULTY_KEYSPACE, FACULTY_LEAVE_COLUMNFAMILY);
		update.where().and(QueryBuilder.eq("object_id", objectId));
		Assignments assignments = update.with();
		assignments.and(QueryBuilder.set("faculty_id", facultyLeaveInformation.getFacultyId()));
		assignments.and(QueryBuilder.set("leave_appointment",
				JsonCodec.getInstance().toJson(facultyLeaveInformation.getLeaveAppointment())));
		cassandraClient.getSession().execute(update);

	}

	@Override
	public FacultyLeaveInformation getFacultyLeaveInfo(String facultyId, String date) {
		Select select = QueryBuilder.select().all().from(FACULTY_KEYSPACE, FACULTY_LEAVE_COLUMNFAMILY);
		String uuid = facultyId + date;
		UUID objectId = UUID.nameUUIDFromBytes(uuid.getBytes());
		select.where().and(QueryBuilder.eq("object_id", objectId));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			Row row = iterator.next();
			FacultyLeaveInformation facultyLeaveInformation = new FacultyLeaveInformation();
			String jsonString = row.getString("leave_appointment");
			ObjectMapper mapper = new ObjectMapper();
			try {
				List<TimeSheet> leaveAppointment = mapper.readValue(jsonString, new TypeReference<List<TimeSheet>>() {
				});
				facultyLeaveInformation.setLeaveAppointment(leaveAppointment);
				facultyLeaveInformation.setFacultyId(facultyId);
				facultyLeaveInformation.setDate(date);
				return facultyLeaveInformation;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void cancelFacultyLeaveInfo(FacultyLeaveInformation facultyLeaveInformation, String date) {
		String facultyId = facultyLeaveInformation.getFacultyId();
		FacultyLeaveInformation defaultInfo = getFacultyLeaveInfo(facultyId, date);
		List<TimeSheet> defaultTimesheet = defaultInfo.getLeaveAppointment();
		List<TimeSheet> cancelTimesheet = facultyLeaveInformation.getLeaveAppointment();
		for (TimeSheet t : cancelTimesheet) {
			for (Iterator<TimeSheet> it = defaultTimesheet.iterator(); it.hasNext();) {

				TimeSheet timesheet = it.next();
				if (t.getEndDate().equalsIgnoreCase(timesheet.getEndDate())
						&& (t.getEndTime().equalsIgnoreCase(timesheet.getEndTime())
								&& (t.getStartDate().equalsIgnoreCase(timesheet.getStartDate())
										&& (t.getStartTime().equalsIgnoreCase(timesheet.getStartTime()))))) {
					it.remove();
				}
			}
		}
		facultyLeaveInformation.setLeaveAppointment(defaultTimesheet);
		setFacultyLeaveInfo(facultyLeaveInformation);
	}

	@Override
	public boolean deleteFacultyLeaveInfo(String facultyId, String date) {

		String uuid = facultyId + date;
		UUID objectId = UUID.nameUUIDFromBytes(uuid.getBytes());
		boolean status = checkFacultyLeaveInfo(objectId);
		if(status) {
			String query = "delete from " + FACULTY_KEYSPACE + "." + FACULTY_LEAVE_COLUMNFAMILY + " where object_id = "
					+ objectId;
			cassandraClient.getSession().execute(query);
			return status;
		}
		return status;
	}

	private boolean checkFacultyLeaveInfo(UUID objectId) {
		Select select = QueryBuilder.select().all().from(FACULTY_KEYSPACE, FACULTY_LEAVE_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("object_id", objectId));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
		
	}
}
