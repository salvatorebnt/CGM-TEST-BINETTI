package com.cgm.manager.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Util class dedicated to Date<->String operations
 * @author salvatore.binetti
 *
 */
public class DateUtils {
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	public static final String DATE_TIME_FORMAT = "dd-MM-yyyy'T'HH:mm:ss'Z'";
	
	/**
	 * Convert String to Date in dd-MM-yyyy format
	 * @param dateStr: String string date in dd-MM-yyyy format
	 * @return date: Date 
	 * @author salvatore.binetti
	 */
	public static Date stringToDate(String dateStr) {
		try {
			return new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return new Date();
		} 
	}
	
	/**
	 * Convert Date to String in dd-MM-yyyy format
	 * @param date: Date
	 * @return date: String string date in dd-MM-yyyy format 
	 * @author salvatore.binetti
	 */
	public static String dateToString(Date date){
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(date);
	}
	
	/**
	 * Convert String to Date in dd-MM-yyyy'T'HH:mm:ss'Z' format
	 * @param dateStr: String string date in dd-MM-yyyy'T'HH:mm:ss'Z' format
	 * @return date: Date 
	 * @author salvatore.binetti
	 */
	public static Date stringToDateTime(String dateStr) {
		try {
			return new SimpleDateFormat(DATE_TIME_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return new Date();
		} 
	}
	
	/**
	 * Convert Date to String in dd-MM-yyyy'T'HH:mm:ss'Z' format
	 * @param date: Date
	 * @return date: String string date in dd-MM-yyyy'T'HH:mm:ss'Z' format 
	 * @author salvatore.binetti
	 */
	public static String dateTimeToString(Date date){
		DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		return dateFormat.format(date);
	}
}
