package com.manudev90.userapi.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
	
	private static final String DATE_FORMAT = "MMM d, yyyy HH:mm a";

	public static String convertDateToString(Date date) {
		
		if (date == null) {
			return "";
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		Instant instant = date.toInstant();
		LocalDateTime ldt = instant
				  .atZone(ZoneId.systemDefault())
				  .toLocalDateTime();
		return ldt.format(formatter);
	}

}
