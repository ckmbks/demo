package ckmbks.framework.io;

import ckmbks.framework.lang.Console;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

/**
 * 文件类型判断单元测试
 * @author Looly
 *
 */
public class FileTypeUtilTest {
	
	@Test
	public void fileTypeUtilTest() {
		File file = FileUtil.file("test.xml");
		String type = FileTypeUtil.getType(file);
		Assert.assertEquals("xml", type);
		
//		FileTypeUtil.putFileType("ffd8ffe000104a464946", "new_jpg");
//		String newType = FileTypeUtil.getType(file);
//		Assert.assertEquals("new_jpg", newType);
	}
	
	@Test
	@Ignore
	public void emptyTest() {
		File file = FileUtil.file("d:/empty.txt");
		String type = FileTypeUtil.getType(file);
		Console.log(type);
	}
}
