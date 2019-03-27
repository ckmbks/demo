package ckmbks.framework.swing;

import ckmbks.framework.io.FileUtil;
import org.junit.Ignore;
import org.junit.Test;

public class RobotUtilTest {

	@Test
	@Ignore
	public void captureScreenTest() {
		RobotUtil.captureScreen(FileUtil.file("e:/screen.jpg"));
	}
}
