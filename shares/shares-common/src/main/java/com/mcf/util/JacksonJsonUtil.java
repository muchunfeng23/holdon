package com.mcf.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcf.beans.McqOnOffShelfChangePriceBeanForJackson;

public class JacksonJsonUtil {
	public static void parseJsonToObject() throws JsonParseException, JsonMappingException, IOException {
		String jsonString = "{\"type\":1,\"id\":\"58f87fe94522ed2d048b458c\",\"callback_id\":\"58f87fe94522ed2d048b45aa\",\"u_t\":1492784409}";
		ObjectMapper mapper = new ObjectMapper();
		McqOnOffShelfChangePriceBeanForJackson aBean = mapper.readValue(jsonString, McqOnOffShelfChangePriceBeanForJackson.class);
        System.out.println(aBean.getCallbackId());
	}
	
	public static void main(String args[]) {
		
	}
}
