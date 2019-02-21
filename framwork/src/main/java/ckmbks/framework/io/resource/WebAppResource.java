package ckmbks.framework.io.resource;

import java.io.File;

import ckmbks.framework.io.FileUtil;
import ckmbks.framework.io.resource.FileResource;

/**
 * Web root资源访问对象
 * 
 * @author looly
 * @since 4.1.11
 */
public class WebAppResource extends FileResource {

	/**
	 * 构造
	 * 
	 * @param path 相对于Web root的路径
	 */
	public WebAppResource(String path) {
		super(new File(FileUtil.getWebRoot(), path));
	}

}
