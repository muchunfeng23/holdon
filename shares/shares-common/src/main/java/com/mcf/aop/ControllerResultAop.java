//package com.mcf.aop;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.servlet.ModelAndView;
//
//public class ControllerResultAop {
//	private static final Logger logger = Logger.getLogger(ControllerResultAop.class);
//	
//	@Autowired
//	private RetailPriceConfig retailPriceConfig;
//
//	//@Pointcut("execution(* com.sprucetec.*.*.controller..*.*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	@Pointcut("@within(org.springframework.stereotype.Controller) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	public void aspect(){}
//	
//	@Around("aspect()")
//	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
//		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//		HttpServletRequest request = sra.getRequest();
//		UserInfo userInfo = (UserInfo) request.getAttribute(UserInfo.KEY_USER_INFO);
//
//		Object data = joinPoint.proceed();
//		if(data instanceof ModelAndView){
//			ModelAndView mv = (ModelAndView)data;
//			mv.addObject("springProfiles", retailPriceConfig.getSpringProfiles());
//			mv.addObject("user", userInfo != null ? userInfo.getUser() : null);
//			return mv;
//		}
//		return data;
//	}
//}
