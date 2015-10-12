package com.qiantai_business.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiantai_business.dao.ImageDao;
import com.qiantai_business.po.ImagePo;
import com.qiantai_business.service.ImageService;

@Service("imageService")
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageDao imageDao;

	public void addImageInfo(ImagePo image) {
		imageDao.addImageInfo(image);
	}

	public List<ImagePo> getAllImageInfo() {
		return imageDao.getAllImageInfo();
	}

	public List<ImagePo> getImageInfoByType(String image_type) {
		return imageDao.getImageInfoByType(image_type);
	}

	public ImagePo getImageById(int image_id) {
		return imageDao.getImageById(image_id);
	}

	public int getAllNumsByType(String image_type) {
		return imageDao.getAllNumsByType(image_type);
	}

	public List<ImagePo> getImageInfoByTypeAndPage(String image_type,Integer row,Integer count) {
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		hashmap.put("image_type", image_type);
		hashmap.put("row", row);
		hashmap.put("count", count);
		
		return imageDao.getImageInfoByTypeAndPage(hashmap);
	}

	public int getAllImageCount() {
		return imageDao.getAllImageCount();
	}

	public List<ImagePo> getAllImageInfoByPage(Integer row,Integer count) {
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		hashmap.put("row", row);
		hashmap.put("count", count);
		return imageDao.getAllImageInfoByPage(hashmap);
	}

}
