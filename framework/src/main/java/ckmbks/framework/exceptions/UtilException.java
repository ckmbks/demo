package ckmbks.framework.exceptions;

import ckmbks.framework.util.StrUtil;

/**
 * 工具类异常
 * @author xiaoleilu
 */
public class UtilException extends RuntimeException{
	private static final long serialVersionUID = 8247610319171014183L;

	public UtilException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}
	
	public UtilException(String message) {
		super(message);
	}
	
	public UtilException(String messageTemplate, Object... params) {
		super(StrUtil.templateFormat(messageTemplate, params));
	}
	
	public UtilException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public UtilException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.templateFormat(messageTemplate, params), throwable);
	}
}
