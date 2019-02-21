package ckmbks.framework.template;

import ckmbks.framework.lang.Dict;
import ckmbks.framework.template.TemplateConfig.ResourceMode;
import ckmbks.framework.template.engine.freemarker.FreemarkerEngine;
import org.junit.Assert;
import org.junit.Test;

/**
 * 模板引擎单元测试
 * 
 * @author looly
 *
 */
public class TemplateUtilTest {

	@Test
	public void createEngineTest() {
		// 默认模板引擎，此处为Beetl
		TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());
		Template template = engine.getTemplate("hello,${name}");
		String result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result);
	}
	@Test
	public void freemarkerEngineTest() {
		TemplateEngine engine = new FreemarkerEngine(new TemplateConfig("templates", ResourceMode.CLASSPATH));
		Template template = engine.getTemplate("freemarker_test.ftl");
		String result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result);
		
		engine = new FreemarkerEngine(new TemplateConfig("templates", ResourceMode.STRING));
		template = engine.getTemplate("hello,${name}");
		result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result);
	}

}
