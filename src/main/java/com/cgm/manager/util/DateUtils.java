package com.cgm.manager.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	//public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String DATE_TIME_FORMAT = "dd-MM-yyyy'T'HH:mm:ss'Z'";
	
	public static Date stringToDate(String dateStr) {
		try {
			return new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return new Date();
		} 
	}
	
	public static String dateToString(Date date){
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(date);
	}
	
	public static Date stringToDateTime(String dateStr) {
		try {
			return new SimpleDateFormat(DATE_TIME_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return new Date();
		} 
	}
	
	public static String dateTimeToString(Date date){
		DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		return dateFormat.format(date);
	}
}
