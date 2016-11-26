package com.poka.app.quartz;

import java.io.File;

/**
 * 执行日志定时清理任务类
 * 
 * @author lb
 * 
 */
public class DoDeleteLogsJob {

	public void work() {
		String path = this.getClass().getResource("/").getPath().replace("classes", "logs");
		File logFile = new File(path);
		deleteFile(logFile);
	}
	
	private void deleteFile(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}
		}
	}
}
