package com.facultyavailability.commons.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		Date today = Calendar.getInstance().getTime();
		System.out.println(today);
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String DateToStr = format.format(today);
		System.out.println(DateToStr);
	}
}
