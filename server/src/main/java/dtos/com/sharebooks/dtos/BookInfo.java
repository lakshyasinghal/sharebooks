package com.sharebooks.dtos;

import org.json.simple.JSONObject;
import com.sharebooks.entities.coreEntities.enums.AvailableStatus;
import com.sharebooks.exception.JsonSerializationException;

public class BookInfo extends DTO{
	private String title;
	private String author;
	private String category;
	private String subcategory;
	private int pages;
	private String ownerUid;
	private String imgSrc;
	private AvailableStatus available;
	private AvailableStatus buy;
	private AvailableStatus rent;
	private int buyAmount;
	private int rentAmount;

	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
