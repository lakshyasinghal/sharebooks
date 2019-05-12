package com.sharebooks.util.dateTime;

//import java.time.LocalDate;
import org.json.simple.JSONObject;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;


public class LocalDate implements JsonSerializable{
	private int date;
	private int month;
	private int year;
	
	
	public LocalDate(){
		this.date = java.time.LocalDate.now().getDayOfMonth();
		this.month = java.time.LocalDate.now().getMonthValue();
		this.year = java.time.LocalDate.now().getYear();
	}
	
	
	public LocalDate(int date , int month , int year){
		this.date = date;
		this.month = month;
		this.year = year;
	}
	
	
	public static LocalDate buildFromString(String dateStr) throws RuntimeException{
		try{
			if(dateStr==null || dateStr.trim().equals("")){
				return null;
			}
			String[] datetokens = dateStr.split("-");
			
			int year = Integer.parseInt(datetokens[0]);
			int month = Integer.parseInt(datetokens[1]);
			int date = Integer.parseInt(datetokens[2]);
			
			return new LocalDate(date , month , year);
		}
		catch(Exception ex){
			throw new RuntimeException("The DateTimeStr should be in the format 2018-10-03 21:32:19");
		}
	}
	
	
	public LocalDate(String dateStr){
		String[] dateTokens = null;
		dateTokens = dateStr.split("-");
		
		this.year = Integer.parseInt(dateTokens[0]);
		this.month = Integer.parseInt(dateTokens[1]);
		this.date = Integer.parseInt(dateTokens[2]);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		jo.put("date" , year + "-" + twoDigitFormat(month) + "-" + twoDigitFormat(date));
	}
	
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(year);
		builder.append("-");
		builder.append(twoDigitFormat(month));
		builder.append("-");
		builder.append(twoDigitFormat(date));
		return builder.toString();
	}

	
	private static String twoDigitFormat(Object obj){
		String result = null;
		result = obj.toString();
		if(result.length()==1){
			result = "0" + result;
		}
		return result;
	}

}
