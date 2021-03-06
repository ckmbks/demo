package ckmbks.framework.img;

import ckmbks.framework.io.FileUtil;
import org.junit.Ignore;
import org.junit.Test;

public class ImgTest {
	
	@Test
	@Ignore
	public void cutTest1() {
		Img.from(FileUtil.file("e:/pic/face.jpg")).cut(0, 0, 200).write(FileUtil.file("e:/pic/face_radis.png"));
	}
	
	@Test
	@Ignore
	public void compressTest() {
		Img.from(FileUtil.file("e:/pic/1111.png")).setQuality(0.8).write(FileUtil.file("e:/pic/1111_target.jpg"));
	}
}
