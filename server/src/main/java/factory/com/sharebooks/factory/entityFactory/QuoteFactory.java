package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.sharebooks.entities.coreEntities.Quote;
import com.sharebooks.entities.coreEntities.enums.QuoteStatus;
import com.sharebooks.entities.coreEntities.enums.QuoteType;
import com.sharebooks.entities.helperEntities.BuyInfo;
import com.sharebooks.entities.helperEntities.RentInfo;
import com.sharebooks.exception.NonFunctionalMethodException;
import com.sharebooks.util.JsonUtility;
import com.sharebooks.util.dateTime.LocalDateTime;

public class QuoteFactory implements EntityFactory<Quote> {
	private static QuoteFactory instance;

	private QuoteFactory() {
		// nothing goes here
	}

	public static QuoteFactory getInstance() throws Exception {
		try {
			if (instance == null) {
				synchronized (QuoteFactory.class) {
					if (instance == null) {
						instance = new QuoteFactory();
					}
				}
			}
			return instance;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Quote createFromHttpRequest(HttpServletRequest req) throws Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public Quote createFromResultSet(ResultSet rs) throws Exception {
		int id = rs.getInt("id");
		String uid = rs.getString("uid");
		String bookUid = rs.getString("bookUid");
		String userUid = rs.getString("userUid");
		QuoteStatus status = QuoteStatus.valueOf(rs.getInt("status"));
		QuoteType type = QuoteType.valueOf(rs.getInt("type"));
		RentInfo rentInfo = (RentInfo)JsonUtility.getDeserializedObjectFromJson(new RentInfo(), rs.getString("rentInfo"));
		BuyInfo buyInfo = (BuyInfo)JsonUtility.getDeserializedObjectFromJson(new BuyInfo(), rs.getString("buyInfo"));
		int totalAmount = rs.getInt("totalAmount");
		
		String creationTimeStr = (rs.getTimestamp("creationTime")).toString();
		LocalDateTime creationTime = LocalDateTime.buildFromString(creationTimeStr);
		String lastModificationTimeStr = (rs.getTimestamp("lastModificationTime")).toString();
		LocalDateTime lastModificationTime = LocalDateTime.buildFromString(lastModificationTimeStr);
		
		Quote quote = new Quote(id, uid, bookUid, userUid, status, type, rentInfo, buyInfo, totalAmount, creationTime, 
				lastModificationTime);
		return quote;
	}

	@Override
	public Quote createFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		Object obj = new JSONParser().parse(json);
		JSONObject jo = (JSONObject) obj;
		Quote quote = new Quote();
		quote.deserializeFromJson(jo);

		return quote;
	}

	@Override
	public List<Quote> getListFromJson(String json) throws Exception {
		throw new NonFunctionalMethodException();
	}
	
}
