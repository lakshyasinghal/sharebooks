package com.sharebooks.entities.coreEntities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.mysql.jdbc.JDBC4ClientInfoProvider;
import com.sharebooks.entities.coreEntities.enums.QuoteStatus;
import com.sharebooks.entities.coreEntities.enums.QuoteType;
import com.sharebooks.entities.entity.CoreEntity;
import com.sharebooks.entities.helperEntities.BuyInfo;
import com.sharebooks.entities.helperEntities.RentInfo;
import com.sharebooks.exception.JsonDeserializationException;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonDeserializable;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.JsonUtility;
import com.sharebooks.util.dateTime.LocalDateTime;

public class Quote extends CoreEntity implements JsonSerializable, JsonDeserializable {

	private String bookUid;
	private String userUid;
	private QuoteStatus status;
	private QuoteType type;
	private RentInfo rentInfo;
	private BuyInfo buyInfo;
	private int totalAmount;

	public Quote() {
		// nothing goes here
	}

	public Quote(int id, String uid, String bookUid, String userUid, QuoteStatus status, QuoteType type,
			RentInfo rentInfo, BuyInfo buyInfo, int totalAmount, LocalDateTime creationTime,
			LocalDateTime lastModificationTime) {
		super(id, uid, creationTime, lastModificationTime);
		this.bookUid = bookUid;
		this.userUid = userUid;
		if (status == null) {
			this.status = QuoteStatus.INITIALIZED;
		} else {
			this.status = status;
		}
		this.type = type;
		this.rentInfo = rentInfo;
		this.buyInfo = buyInfo;
		this.totalAmount = totalAmount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		try {
			super.serializeAsJson(jo);
			jo.put("bookUid", bookUid);
			jo.put("userUid", userUid);
			//jo.put("status", JsonUtility.getJSONObjFromObj(status));
			jo.put("status", status.id());
			jo.put("type", type.id());
			jo.put("rentInfo", JsonUtility.getJSONObjFromObj(rentInfo));
			jo.put("buyInfo", JsonUtility.getJSONObjFromObj(buyInfo));
			jo.put("totalAmount", totalAmount);
		} catch (Exception ex) {
			throw new JsonSerializationException(ex.getMessage());
		}
	}

	@Override
	public void deserializeFromJson(JSONObject jo) throws JsonDeserializationException,Exception {
		super.deserializeFromJson(jo);
		bookUid = (String) jo.get("bookUid");
		userUid = (String) jo.get("userUid");
		status = jo.get("status") != null ? QuoteStatus.valueOf((int)(long) jo.get("status")) : null;
		type = jo.get("type") != null ? QuoteType.valueOf((int) (long) jo.get("type")) : null;
		rentInfo = (RentInfo)JsonUtility.getDeserializedObjectFromJsonObject(new RentInfo(), (JSONObject) jo.get("rentInfo"));
		buyInfo = (BuyInfo)JsonUtility.getDeserializedObjectFromJsonObject(new BuyInfo(), (JSONObject) jo.get("buyInfo"));
		totalAmount = jo.get("totalAmount") != null ? (int) (long) jo.get("totalAmount") : -1;
	}

	public Map<String, Object> map() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> superMap = super.map();
		map.putAll(superMap);

		map.put("bookUid", bookUid);
		map.put("userUid", userUid);
		map.put("status", status.id());
		map.put("type", type.id());
		map.put("rentInfo", JsonUtility.getJSONFromObj(rentInfo));
		map.put("buyInfo", JsonUtility.getJSONFromObj(buyInfo));
		map.put("totalAmount", totalAmount);
		return map;
	}

	public String bookUid() {
		return bookUid;
	}

	public String userUid() {
		return userUid;
	}

	public QuoteStatus status() {
		return status;
	}

	public QuoteType type() {
		return type;
	}

	public RentInfo rentInfo() {
		return rentInfo;
	}

	public BuyInfo buyInfo() {
		return buyInfo;
	}

	public int totalAmount() {
		return totalAmount;
	}
}
