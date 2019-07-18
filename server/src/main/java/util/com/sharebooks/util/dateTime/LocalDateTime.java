package com.sharebooks.util.dateTime;

import java.time.LocalDate;
import java.time.LocalTime;

import org.json.simple.JSONObject;

import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;

public class LocalDateTime implements JsonSerializable {
	private int date;
	private int month;
	private int year;
	private int hh;
	private int mm;
	private int ss;

	public LocalDateTime() {
		this.date = LocalDate.now().getDayOfMonth();
		this.month = LocalDate.now().getMonthValue();
		this.year = LocalDate.now().getYear();
		this.hh = LocalTime.now().getHour();
		this.mm = LocalTime.now().getMinute();
		this.ss = LocalTime.now().getSecond();
	}

	public LocalDateTime(int date, int month, int year, int hh, int mm, int ss) {
		this.date = date;
		this.month = month;
		this.year = year;
		this.hh = hh;
		this.mm = mm;
		this.ss = ss;
	}

	public static LocalDateTime buildFromString(String dateTimeStr) throws Exception {
		try {
			String[] dateStr = null;
			String[] timeStr = null;
			String[] dateTimetokens = dateTimeStr.split(" ");
			dateStr = dateTimetokens[0].split("-");
			timeStr = dateTimetokens[1].split(":");

			int year = Integer.parseInt(dateStr[0]);
			int month = Integer.parseInt(dateStr[1]);
			int date = Integer.parseInt(dateStr[2]);

			int hh = Integer.parseInt(timeStr[0]);
			int mm = Integer.parseInt(timeStr[1]);
			int ss = (int) Float.parseFloat(timeStr[2]);

			return new LocalDateTime(date, month, year, hh, mm, ss);
		} catch (Exception ex) {
			throw new Exception("The DateTimeStr should be in the format 2018-10-03 21:32:19");
		}
	}

	public static LocalDateTime plusMinutes(int minutes) {
		java.time.LocalDateTime futureDate = java.time.LocalDateTime.now().plusMinutes(5);
		return new LocalDateTime(futureDate.getDayOfMonth(), futureDate.getMonthValue(), futureDate.getYear(),
				futureDate.getHour(), futureDate.getMinute(), futureDate.getSecond());
	}

	public LocalDateTime(String dateTimeStr) {
		String[] dateStr = null;
		String[] timeStr = null;
		String[] dateTimetokens = dateTimeStr.split(" ");
		dateStr = dateTimetokens[0].split("-");
		timeStr = dateTimetokens[1].split(":");

		this.year = Integer.parseInt(dateStr[0]);
		this.month = Integer.parseInt(dateStr[1]);
		this.date = Integer.parseInt(dateStr[2]);

		this.hh = Integer.parseInt(timeStr[0]);
		this.mm = Integer.parseInt(timeStr[1]);
		this.ss = (int) Float.parseFloat(timeStr[2]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		jo.put("date", year + "-" + twoDigitFormat(month) + "-" + twoDigitFormat(date));
		jo.put("time", twoDigitFormat(hh) + ":" + twoDigitFormat(mm) + ":" + twoDigitFormat(ss));
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(year);
		builder.append("-");
		builder.append(twoDigitFormat(month));
		builder.append("-");
		builder.append(twoDigitFormat(date));
		builder.append(" ");
		builder.append(twoDigitFormat(hh));
		builder.append(":");
		builder.append(twoDigitFormat(mm));
		builder.append(":");
		builder.append(twoDigitFormat(ss));
		return builder.toString();
	}

	private static String twoDigitFormat(Object obj) {
		String result = null;
		result = obj.toString();
		if (result.length() == 1) {
			result = "0" + result;
		}
		return result;
	}

	public int date() {
		return date;
	}

	public int month() {
		return month;
	}

	public int year() {
		return year;
	}

	public int hour() {
		return hh;
	}

	public int min() {
		return mm;
	}

	public int sec() {
		return ss;
	}

}
