package com.facultyavailability.commons.constant;

public class UriConstants {

	public class User {

		public static final String NEW_USER = "/api/service/user/create";

		public static final String GET_USER_INFO = "/api/service/user/get";

		public static final String UPDATE_USER_INFO = "/api/service/user/update";
	}

	public class Configuration {

		public static final String SET_MAIL_CONFIGURATION = "/api/service/reviews/configurations/mail";

		public static final String GET_MAIL_CONFIGURATION = "/api/service/reviews/configurations/mail";

		public static final String GET_DEFAULT_MAIL_CONFIGURATION = "/api/service/reviews/configurations/mail/defaults";

		public static final String UPLOAD_EXCEL_FILE = "/api/service/reviews/upload/excel/file";

		public static final String UPLOAD_TEMPLATE_FILE = "/api/service/reviews/upload/template/file";
	}

	public class Faculty {
		
		public static final String FACULTY_ACTIVITY = "/api/service/faculty/availability/enabled";
		
		public static final String FACULTY_ACTIVITY_INFO = "/api/service/faculty/availability/info";
		
		public static final String FACULTY_AVAILABILITY_INFO_DELETE = "/api/service/faculty/availability/info/delete";
		
		public static final String FACULTY_LEAVE_INFO = "/api/service/faculty/availability/leave/info";
		
		public static final String FACULTY_LEAVE_INFO_FETCH = "/api/service/faculty/availability/leave/fetch";
		
		public static final String FACULTY_LEAVE_INFO_CANCEL = "/api/service/faculty/availability/leave/cancel";
		
		public static final String FACULTY_LEAVE_INFO_DELETE = "/api/service/faculty/availability/leave/delete";
	
	}

	public class Student {
		
		public static final String STUDENT_FACULTY_INFO = "/api/service/faculty/info";
	}

}
