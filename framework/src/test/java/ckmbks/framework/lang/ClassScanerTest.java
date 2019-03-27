package ckmbks.framework.lang;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

public class ClassScanerTest {
	
	@Test
	@Ignore
	public void scanTest() {
		ClassScaner scaner = new ClassScaner("ckmbks.framework.util.StrUtil", null);
		Set<Class<?>> set = scaner.scan();
		for (Class<?> clazz : set) {
			Console.log(clazz.getName());
		}
	}
}
