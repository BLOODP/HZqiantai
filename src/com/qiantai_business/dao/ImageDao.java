package com.qiantai_business.dao;

import java.util.HashMap;
import java.util.List;

import com.qiantai_business.po.ImagePo;

public interface ImageDao {
	
	public void addImageInfo(ImagePo image);
	
	public List<ImagePo> getAllImageInfo();
	
	public List<ImagePo> getImageInfoByType(String image_type);
	
	public ImagePo getImageById(int image_id);
	
	public int getAllNumsByType(String image_type);
	
	public List<ImagePo> getImageInfoByTypeAndPage(HashMap<String, Object> hashmap);

	public int getAllImageCount();
	
	public List<ImagePo> getAllImageInfoByPage(HashMap<String, Integer> hashmap);
	
}
