package ckmbks.framework.template.engine;

import ckmbks.framework.util.StrUtil;
import ckmbks.framework.template.TemplateConfig;
import ckmbks.framework.template.TemplateEngine;
import ckmbks.framework.template.TemplateException;
import ckmbks.framework.template.engine.freemarker.FreemarkerEngine;

/**
 * 简单模板工厂，用于根据用户引入的模板引擎jar，自动创建对应的模板引擎对象
 * 
 * @author looly
 *
 */
public class TemplateFactory {
	/**
	 * 根据用户引入的模板引擎jar，自动创建对应的模板引擎对象
	 * 
	 * @param config 模板配置，包括编码、模板文件path等信息
     * @return {@link TemplateEngine}
	 */
	public static TemplateEngine create(TemplateConfig config) {
		final TemplateEngine engine = doCreate(config);
//		StaticLog.debug("Use [{}] Engine As Default.", StrUtil.removeSuffix(engine.getClass().getSimpleName(), "Engine"));
		return engine;
	}

	/**
	 * 根据用户引入的模板引擎jar，自动创建对应的模板引擎对象
	 * 
	 * @param config 模板配置，包括编码、模板文件path等信息
     * @return {@link TemplateEngine}
	 */
	private static TemplateEngine doCreate(TemplateConfig config) {
		try {
			return new FreemarkerEngine(config);
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		throw new TemplateException("No template found ! Please add one of template jar to your project !");
	}
}
