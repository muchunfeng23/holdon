package com.mcf.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseJsonUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ResponseJsonUtil.class);

	public static void responseJson(HttpServletResponse response, Object object) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, object);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
