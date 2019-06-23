package com.sharebooks.sms.entities;

import com.sharebooks.entities.entity.CoreEntity;
import com.sharebooks.serialization.json.JsonSerializable;

public abstract class SMSRequest extends CoreEntity implements JsonSerializable {

	private String receipientName;
	private String receipientNumber;
	private String Message;

}
