package com.mcf.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mcf.annotation.AvoidDuplicateSubmit;

public class AvoidDuplicateSubmitInterceptor extends HandlerInterceptorAdapter{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AvoidDuplicateSubmitInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		AvoidDuplicateSubmit annotation = method
				.getAnnotation(AvoidDuplicateSubmit.class);
		if (annotation != null) {
			if (isRepeatSubmit(request)) {
				LOGGER.warn("Don't duplicate submit [url:"
						+ request.getServletPath() + "]");
				return false;
			}
			request.getSession(false).removeAttribute("token");
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		AvoidDuplicateSubmit annotation = method
				.getAnnotation(AvoidDuplicateSubmit.class);
		if (annotation != null) {
			request.getSession(false).setAttribute("token",
					RandomUtils.nextInt(1, 10000));
		}
		super.afterCompletion(request, response, handler, ex);
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute(
				"token");
		if (StringUtils.isBlank(serverToken)) {
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (StringUtils.isBlank(clinetToken)) {
			return false;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}

}
