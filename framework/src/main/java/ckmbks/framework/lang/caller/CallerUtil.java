package ckmbks.framework.lang.caller;

import ckmbks.framework.lang.caller.Caller;
import ckmbks.framework.lang.caller.SecurityManagerCaller;
import ckmbks.framework.lang.caller.StackTraceCaller;

/**
 * 调用者。可以通过此类的方法获取调用者、多级调用者以及判断是否被调用
 * 
 * @author Looly
 * @since 4.1.6
 */
public class CallerUtil {
	private static final ckmbks.framework.lang.caller.Caller INSTANCE;
	static {
		INSTANCE = tryCreateCaller();
	}

	/**
	 * 获得调用者
	 * 
	 * @return 调用者
	 */
	public static Class<?> getCaller() {
		return INSTANCE.getCaller();
	}

	/**
	 * 获得调用者的调用者
	 * 
	 * @return 调用者的调用者
	 */
	public static Class<?> getCallerCaller() {
		return INSTANCE.getCallerCaller();
	}

	/**
	 * 获得调用者，指定第几级调用者<br>
	 * 调用者层级关系：
	 * 
	 * <pre>
	 * 0 {@link CallerUtil}
	 * 1 调用{@link CallerUtil}中方法的类
	 * 2 调用者的调用者
	 * ...
	 * </pre>
	 * 
	 * @param depth 层级。0表示{@link CallerUtil}本身，1表示调用{@link CallerUtil}的类，2表示调用者的调用者，依次类推
	 * @return 第几级调用者
	 */
	public static Class<?> getCaller(int depth) {
		return INSTANCE.getCaller(depth);
	}

	/**
	 * 是否被指定类调用
	 * 
	 * @param clazz 调用者类
	 * @return 是否被调用
	 */
	public static boolean isCalledBy(Class<?> clazz) {
		return INSTANCE.isCalledBy(clazz);
	}

	/**
	 * 尝试创建{@link ckmbks.framework.lang.caller.Caller}实现
	 * 
	 * @return {@link ckmbks.framework.lang.caller.Caller}实现
	 */
	private static Caller tryCreateCaller() {
		try {
			return new SecurityManagerCaller();
		} catch (Throwable e) {
		}
		return new StackTraceCaller();
	}
	// ---------------------------------------------------------------------------------------------- static interface and class
}
