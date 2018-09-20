package com.mcf.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import com.mcf.dto.ServiceResult;
import com.mcf.util.JsonUtil;
import com.mcf.util.ServiceResultUtil;

public class ServiceResultAop {

	private static final Logger logger = Logger.getLogger(ServiceResultAop.class);

	@Pointcut("execution(* com.sprucetec.retailprice.*.api.impl..*.*(..)) and @annotation(org.springframework.stereotype.Service)")
	public void aspect(){}
	
//	@AfterThrowing(pointcut="aspect()", throwing="ex")
	public ServiceResult<RuntimeException> afterThrow(JoinPoint joinPoint, Throwable ex){
		Object[] arguments =joinPoint.getArgs();
		String methodName=joinPoint.getSignature().getName();
		String className=joinPoint.getSignature().getDeclaringTypeName();

		String logError="方法："+className+"."+methodName+"；入参"+ JsonUtil.toJson(arguments)+";错误信息："+ex.getMessage();
		logger.error(logError,ex);

		return ServiceResultUtil.failure(ex);
	}
	
	@Around("aspect()")
	public ServiceResult<?> around(ProceedingJoinPoint joinPoint){
		try{
			Object data=joinPoint.proceed();
			ServiceResult<Object>  sr= new ServiceResult<>();
			if(data instanceof ServiceResult){
				sr.setSuccess(((ServiceResult<?>) (data)).getSuccess());
				sr.setMessage(((ServiceResult<?>) (data)).getMessage());
				sr.setBody(((ServiceResult<?>) (data)).getBody());
				sr.setErrorCode(((ServiceResult<?>)(data)).getErrorCode());
			}else {
				sr.setSuccess(true);
				sr.setBody(data);
				sr.setErrorCode(0);
			}
			
			return sr;
		}catch (Throwable ex){
			return afterThrow(joinPoint,ex);
		}

	}

}
