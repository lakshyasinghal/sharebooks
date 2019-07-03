package com.sharebooks.payment.entities;

import java.util.Map;

import com.sharebooks.serialization.json.JsonSerializable;

public interface PaymentRequest extends JsonSerializable {

	public abstract String longurl();

	public abstract Map<String, Object> map() throws Exception;
}
