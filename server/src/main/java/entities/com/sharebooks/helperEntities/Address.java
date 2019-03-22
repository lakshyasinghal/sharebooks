//package com.sharebooks.helperEntities;
//
//import org.json.simple.JSONObject;
//
//import com.sharebooks.entity.HelperEntity;
//import com.sharebooks.exception.JsonSerializationException;
//
//public class Address extends HelperEntity{
//	
//	private String houseNo;
//	private String street;
//	private String pincode;
//	private City city;
//	private State state;
//	
//	
//	public Address(){
//		//nothing
//	}
//	
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public String serializeAsJson() throws JsonSerializationException {
//		try{
//			JSONObject jo = new JSONObject();
//			jo.put("houseNo", houseNo);
//			jo.put("street", street);
//			jo.put("pincode", pincode);
//			jo.put("city", city.serializeAsJson());
//			jo.put("state", state.serializeAsJson());
//			
//			return jo.toString();
//		}
//		catch(Exception ex){
//			throw new JsonSerializationException(ex.getMessage());
//		}
//	}
//	
//	@Override
//	public void deserializeFromJson(JSONObject jo) {
//		try{
//			houseNo = (String)jo.get("houseNo");
//			street = (String)jo.get("street");
//			pincode = (String)jo.get("pincode");
//			city = new City();
//			city.deserializeFromJson((JSONObject)jo.get("city"));
//			state = new State();
//			state.deserializeFromJson((JSONObject)jo.get("state"));
//		}
//		catch(ClassCastException ex){
//			
//		}
//	}
//	
//	public String houseNo(){
//		return houseNo;
//	}
//	
//	public String street(){
//		return street;
//	}
//	
//	public String pincode(){
//		return pincode;
//	}
//	
//	public City city(){
//		return city;
//	}
//	
//	public State state(){
//		return state;
//	}
//}
