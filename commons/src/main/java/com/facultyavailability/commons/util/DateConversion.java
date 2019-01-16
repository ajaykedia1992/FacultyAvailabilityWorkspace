package com.facultyavailability.commons.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversion {

	public String convertDateFormat(Date date) {
		//Date today = Calendar.getInstance().getTime();
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return format.format(date);
	}
}
