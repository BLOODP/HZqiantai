package com.qiantai_business.service;

import java.util.List;

import com.qiantai_business.po.ImagePo;

public interface ImageService {
	
	public void addImageInfo(ImagePo image);
	
	public List<ImagePo> getAllImageInfo();
	
	public List<ImagePo> getImageInfoByType(String image_type);
	
	public ImagePo getImageById(int image_id);
	
	public int getAllNumsByType(String image_type);
	
	public List<ImagePo> getImageInfoByTypeAndPage(String image_type,Integer row,Integer count);
	
	public int getAllImageCount();
	
	public List<ImagePo> getAllImageInfoByPage(Integer row,Integer count);

}
