package com.qiantai_business.utl;

import java.io.File;

public class FileHandler {
	
	
	/**
	 * 创建文件夹
	 * @param path
	 * @return
	 */
	public File createFile(String path){
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		
		return file;
	}
	
	/**
	 * 创建临时文件夹
	 * @param path
	 * @return
	 */
	public File createTempFile(String path){
		
		System.out.println("path          "+path);
//		int index = path.lastIndexOf("\\");
//		String subPath = path.substring(0, index);
//		String tempPath = subPath+"\\temp";
		String tempPath = path+"\\temp";
		return new File(tempPath);
	}
	
	public void deleteFile(File file){
		
		if(file.exists()){
			
			if(file.isFile()){
				file.delete();
			}else if(file.isDirectory()){
				File[] files = file.listFiles();
				for (File item : files) {
					deleteFile(item);
				}
				file.delete();
			}
			
		}else{
			
		}
		
	}

}












