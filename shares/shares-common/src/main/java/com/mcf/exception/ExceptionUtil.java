package com.mcf.exception;

public class ExceptionUtil {

	/**
	 * 转换错误，返回异常 获取异常码
	 * 
	 * @param ex
	 * @return
	 */
	public static BaseException parseException(Throwable ex) {
		if (isSelfDefinedException(ex)) {
			BaseException be = (BaseException) ex;
			return be;
		} else {
			return new SystemException(ex.getMessage());
		}

	}

	/**
	 * 根据错误码，转换错误，返回异常
	 * 
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static RuntimeException parseException(int errorCode, String message) {
		if (errorCode == ExceptionConstant.BUSINISS_ERROR_CODE) {
			return new BusinessException(message);
		} else if (errorCode == ExceptionConstant.CITY_CHANGED_CODE) {
			return new CurrentCityChangedException(message);
		}else if (errorCode == ExceptionConstant.INVALID_PARAM_ERROR_CODE) {
			return new InvalidParamException(message);
		} else if (errorCode == ExceptionConstant.NOT_RESPONSE_CODE) {
			return new NotResponseException(message);
		} else if (errorCode == ExceptionConstant.RESOURCE_EXIST_ERROR_CODE) {
			return new ResourceExistException(message);
		} else if (errorCode == ExceptionConstant.RESOURCE_NOT_EXIST_ERROR_CODE) {
			return new ResourceNotExistException(message);
		} else if (errorCode == ExceptionConstant.RPC_CODE) {
			return new RpcException(message);
		} else if (errorCode == ExceptionConstant.WEB_CODE) {
			return new WebException(message);
		}else if (errorCode == ExceptionConstant.WITHOUT_AUTHORITY_CODE) {
			return new WithoutAuthorityException(message);
		}else if (errorCode == ExceptionConstant.WITHOUT_LOGIN_CODE) {
			return new WithoutLoginException(message);
		}

		return new SystemException(message);
	}

	/**
	 * 判断是否是自定义异常
	 * 
	 * @param ex
	 * @return
	 */
	public static boolean isSelfDefinedException(Throwable ex) {
		try {
			// BaseException be = (BaseException) ex;
			if (ex instanceof BaseException) {
				return true;
			}
		} catch (Throwable throwable) {
			return false;
		}
		return false;
	}
}
