package com.mcf.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	
	private final static String HTTP_HEAD_REQUEST_TYPE_KEY = "X-Requested-With";
	private final static String HTTP_HEAD_AJAX_VALUE = "XMLHttpRequest";
	
	public static boolean isAjax(HttpServletRequest request){
		String requestType = request.getHeader(HTTP_HEAD_REQUEST_TYPE_KEY);
		return HTTP_HEAD_AJAX_VALUE.equals(requestType);
	}

}
