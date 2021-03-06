package ckmbks.framework.convert.impl;

import java.nio.charset.Charset;

import ckmbks.framework.convert.AbstractConverter;
import ckmbks.framework.util.CharsetUtil;

/**
 * 编码对象转换器
 * @author Looly
 *
 */
public class CharsetConverter extends AbstractConverter<Charset>{

	@Override
	protected Charset convertInternal(Object value) {
		return CharsetUtil.charset(convertToStr(value));
	}

}
