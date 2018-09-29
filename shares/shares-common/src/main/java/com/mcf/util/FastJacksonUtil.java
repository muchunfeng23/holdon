package com.mcf.util;

import com.alibaba.fastjson.JSON;
import com.mcf.beans.McqOnOffShelfChangePriceBeanForFastjson;

public class FastJacksonUtil {
	public static void main(String args[]) {
		String jsonString = "{\"type\":1,\"id\":\"58f87fe94522ed2d048b458c\",\"callback_id\":\"58f87fe94522ed2d048b45aa\",\"u_t\":1492784409}";
		McqOnOffShelfChangePriceBeanForFastjson aBean = JSON.parseObject(jsonString, McqOnOffShelfChangePriceBeanForFastjson.class);
		System.out.println(aBean.getCallbackId());
	}
}
