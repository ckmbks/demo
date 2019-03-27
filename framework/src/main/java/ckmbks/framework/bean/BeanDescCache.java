package ckmbks.framework.bean;

import ckmbks.framework.bean.BeanDesc;
import ckmbks.framework.lang.SimpleCache;

/**
 * Bean属性缓存<br>
 * 缓存用于防止多次反射造成的性能问题
 * @author Looly
 *
 */
public enum BeanDescCache {
	INSTANCE;
	
	private SimpleCache<Class<?>, ckmbks.framework.bean.BeanDesc> bdCache = new SimpleCache<>();
	
	/**
	 * 获得属性名和{@link ckmbks.framework.bean.BeanDesc}Map映射
	 * @param beanClass Bean的类
	 * @return 属性名和{@link BeanDesc}映射
	 */
	public ckmbks.framework.bean.BeanDesc getBeanDesc(Class<?> beanClass){
		return bdCache.get(beanClass);
	}
	
	/**
	 * 加入缓存
	 * @param beanClass Bean的类
	 * @param BeanDesc 属性名和{@link ckmbks.framework.bean.BeanDesc}映射
	 */
	public void putBeanDesc(Class<?> beanClass, BeanDesc BeanDesc){
		bdCache.put(beanClass, BeanDesc);
	}
}
