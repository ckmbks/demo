package ckmbks.framework.convert.impl;

import ckmbks.framework.convert.AbstractConverter;
import ckmbks.framework.util.BooleanUtil;

/**
 * 波尔转换器
 * @author Looly
 *
 */
public class BooleanConverter extends AbstractConverter<Boolean>{

	@Override
	protected Boolean convertInternal(Object value) {
		if(boolean.class == value.getClass()){
			return Boolean.valueOf((boolean)value);
		}
		String valueStr = convertToStr(value);
		return Boolean.valueOf(BooleanUtil.toBoolean(valueStr));
	}

}
