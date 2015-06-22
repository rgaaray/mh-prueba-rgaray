package com.mh.prueba.rgaray.web.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AppUtil {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AppUtil.class);
	
	public static final SimpleDateFormat dateFormatAsDD = new SimpleDateFormat("dd");
	public static final SimpleDateFormat dateFormatAs_mm_ss_SSS = new SimpleDateFormat("mm:ss.SSS");
	public static final SimpleDateFormat dateFormatAsYYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat dateFormatAsYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat dateFormatAsYYYYMM = new SimpleDateFormat("yyyyMM");
	public static final SimpleDateFormat dateFormatAsDD_MM_YYYY___HH_MM_SS_A = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
	public static final SimpleDateFormat dateFormatAsDD_MM_YYYY = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat dateFormatAsDD_MM_YYYY___HH_MM = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static final DecimalFormat decimalFormatNNN_NN0_00 = new DecimalFormat("###,##0.00");
	public static final DecimalFormat decimalFormatNN0_00 = new DecimalFormat("##0.00");
	public static final DecimalFormat decimalFormatNN0 = new DecimalFormat("##0");
	
	public static String getDateAsDD_MM_YYYY(Date date) {
		String formattedDate = StringUtils.EMPTY;
		formattedDate = dateFormatAsDD_MM_YYYY.format(date);
		return formattedDate;
	}
	
	public static Date getDateFromDD_MM_YYYY(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			LOGGER.error(e.getMessage());
		}
		return date;
	}
	
	public static String getDateAsDD_MM_YYYY_From_YYYYMMDD(String stringDateAsYYYYMMDD) {
		String formattedDate = StringUtils.EMPTY;
		try {
			if (StringUtils.isNotBlank(stringDateAsYYYYMMDD)) formattedDate = getDateAsDD_MM_YYYY(dateFormatAsYYYYMMDD.parse(stringDateAsYYYYMMDD));
		}
		catch (ParseException e) {
			LOGGER.error("Error convirtiendo fecha a formato dd/MM/yyyy: " + e.getMessage(), e);
		}
		return formattedDate;
	}
	
	public static String getDateAsDDMMYYYY_From_YYYYMMDD(String stringDateAsYYYYMMDD) {
		String formattedDate = StringUtils.EMPTY;
		try {
			if (StringUtils.isNotBlank(stringDateAsYYYYMMDD)) formattedDate = getDateAsDD_MM_YYYY(dateFormatAsYYYYMMDD.parse(stringDateAsYYYYMMDD));
		}
		catch (ParseException e) {
			LOGGER.error("Error convirtiendo fecha a formato dd/MM/yyyy: " + e.getMessage(), e);
		}
		return formattedDate;
	}

	public static String getDateAsDD_MM_YYYY_From_YYYYMMDDHHMMSS(String stringDateAsYYYYMMDDHHMMSS) {
		String formattedDate = StringUtils.EMPTY;
		try {
			if (StringUtils.isNotBlank(stringDateAsYYYYMMDDHHMMSS)) formattedDate = getDateAsDD_MM_YYYY(dateFormatAsYYYYMMDDHHMMSS.parse(stringDateAsYYYYMMDDHHMMSS));
		}
		catch (ParseException e) {
			LOGGER.error("Error convirtiendo fecha a formato dd/MM/yyyy: " + e.getMessage(), e);
		}
		return formattedDate;
	}

	public static String getDateAsYYYYMMDD_From_DD_MM_YYYY(String stringDateAsDD_MM_YYYY) {
		String formattedDate = StringUtils.EMPTY;
		try {
			if (StringUtils.isNotBlank(stringDateAsDD_MM_YYYY)) formattedDate = dateFormatAsYYYYMMDD.format(dateFormatAsDD_MM_YYYY.parse(stringDateAsDD_MM_YYYY));
		}
		catch (ParseException e) {
			LOGGER.error("Error convirtiendo fecha a formato dd/MM/yyyy: " + e.getMessage(), e);
		}
		return formattedDate;
	}
	
	public static LinkedHashMap<String, Integer> getDateDifferences(Date startDate, Date endDate) {
		LinkedHashMap<String, Integer> hashmap = new LinkedHashMap<String, Integer>();
		DateTime startDateTime = new DateTime(startDate);
		DateTime endDateTime = new DateTime(endDate);
		hashmap.put("years", Years.yearsBetween(startDateTime, endDateTime).getYears());
		hashmap.put("months", Months.monthsBetween(startDateTime, endDateTime).getMonths());
		hashmap.put("weeks", Weeks.weeksBetween(startDateTime, endDateTime).getWeeks());
		hashmap.put("days", Days.daysBetween(startDateTime, endDateTime).getDays());
		return hashmap;
	}

	public static String getDateDifferenceInDaysHoursMinutesAndSecondsAsFormattedString(Date startDate, Date endDate) {
		String difference = StringUtils.EMPTY;
		Period period = new Period(startDate.getTime(), endDate.getTime(), PeriodType.dayTime());
		PeriodFormatter periodFormatter = new PeriodFormatterBuilder()
			.printZeroIfSupported()
			.appendDays()
			.appendSuffix(" D\u00CDA", " D\u00CDAS")
			.appendSeparator(", ")
			.appendHours()
			.appendSuffix(" HORA", " HORAS")
			.appendSeparator(", ")
			.appendMinutes()
			.appendSuffix(" MINUTO", " MINUTOS")
			.appendSeparator(" Y ")
			.appendSeconds()
			.appendSuffix(" SEGUNDO", " SEGUNDOS")
			.toFormatter();
		difference = periodFormatter.print(period);
		return difference;
	}
	
	public static User getUserFromAcegi() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof User) return (User) obj;
		else return null;
	}
	
	public static String getSecUserUsernameFromAcegi() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof User) return ((User) obj).getUsername();
		else return "";
	}
	
	public static String getBase64Encode(String theString) {
		return new String(Base64.encodeBase64(Base64.encodeBase64(theString.getBytes())));
	}

	public static String getBase64Decode(String theString) {
		return new String(Base64.decodeBase64(Base64.decodeBase64(theString.getBytes())));
	}

}
