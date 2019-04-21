package com.sharebooks.util;

import java.util.List;
import com.sharebooks.entity.Entity;
import com.sharebooks.entity.HelperEntity;
import com.sharebooks.exception.JsonSerializationException;

public class JsonUtils {
	
	
	public static String getSerializedList(List<? extends Object> list) throws JsonSerializationException{
		StringBuilder serList = new StringBuilder();
		if(list==null || list.size()==0){
			return "[]";
		}
		serList.append("[");
		int i=0;
		Object obj = null;
		for(int len=list.size() ; i<len ; i++){
			obj = list.get(i);
			if(obj instanceof Entity){
				Entity entity = (Entity)obj;
				serList.append(entity.serializeAsJson());
				if(i<len-1){
					serList.append(",");
				}
			}
			if(obj instanceof HelperEntity){
				HelperEntity entity = (HelperEntity)obj;
				serList.append(entity.serializeAsJson());
				if(i<len-1){
					serList.append(",");
				}
			}
		}
		serList.append("]");
		return serList.toString();
	}

}
