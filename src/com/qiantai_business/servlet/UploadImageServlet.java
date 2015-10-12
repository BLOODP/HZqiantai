package com.qiantai_business.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qiantai_business.po.ImagePo;
import com.qiantai_business.service.ImageService;
import com.qiantai_business.utl.FileHandler;

public class UploadImageServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageService imageService;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
		
		JSONObject jsonObj = new JSONObject();
		
		String image_name=null;
		String image_type=null;
		String image_description=null;
		String image_path=null;
		//设置允许上传的图片格式
		String[] allowString = {"PNG","jpeg","jpg","png","JPEG","JPEG2000","SVG"};
		List<String> allowType = Arrays.asList(allowString);
		 //图片存储路径
		 String savedPath = this.getServletContext().getRealPath("/WEB-INF/image/");
		 
		 FileHandler fileHandler = new FileHandler();
		 //设置缓存目录
		 File tempFile = fileHandler.createTempFile(savedPath);
		 tempFile.mkdirs();
		 
		 
		 try {
			 DiskFileItemFactory factory = new DiskFileItemFactory();
			 
			 factory.setSizeThreshold(4*1024);
			 factory.setRepository(tempFile);
			 
			 ServletFileUpload upload = new ServletFileUpload(factory);
			 upload.setHeaderEncoding("utf-8");
			 List<FileItem> items = upload.parseRequest(request);
			 
			 Iterator<FileItem> it = items.iterator();
			 FileItem item;
			 while(it.hasNext()){
				 item = it.next();
				 if(item.isFormField()){
					 if(item.getFieldName().equals("image_type")){
						 image_type = item.getString("utf-8");
					 }else if(item.getFieldName().equals("image_description")){
						 image_description = item.getString("utf-8");
					 }else if(item.getFieldName().equals("image_name")){
						 image_name = item.getString("utf-8");
					 }
				 }
				 
			 }
			 System.out.println("image_type::"+image_type);
			 System.out.println("image_description::::"+image_description);
			 savedPath = this.getServletContext().getRealPath("/WEB-INF/image/")+"\\"+image_type;
			 
			 fileHandler.createFile(savedPath);
			 Iterator<FileItem> it1 = items.iterator();
			 while(it1.hasNext()){
				 item = it1.next();
				 if(!item.isFormField()){
					 String filePath = item.getName();
					 int index = filePath.lastIndexOf("\\");
					 image_name = filePath.substring(index+1,filePath.length());
					 image_path = savedPath+"\\"+image_name;
					 
					 int indexofpoint = filePath.lastIndexOf(".");
					 String filetype = filePath.substring(indexofpoint+1, filePath.length());
					 System.out.println(filetype);
					 
					 if(allowType.contains(filetype)){
						 
//						 System.out.println("image_name::"+image_name);
//						 System.out.println("image_path::"+image_path);
						 processUploadedField(item,image_path);
						 
						 ImagePo image = new ImagePo();
						 image.setImage_description(image_description);
						 image.setImage_name(image_name);
						 image.setImage_path(image_path);
						 image.setImage_type(image_type);
						 image.setImage_submitdate(new Date());
						 imageService.addImageInfo(image);
						 fileHandler.deleteFile(tempFile);
						 jsonObj.put("status", 1);
						 jsonObj.put("reason", "上传成功");
						 
					 }else{
						 jsonObj.put("status", 0);
						 jsonObj.put("reason", "上传失败，不合法的图片格式");
					 }
					 
				 }
			 }
			 
			 
		} catch (FileUploadException e) {
			jsonObj.put("status", 0);
			jsonObj.put("reason", "上传失败");
			e.printStackTrace();
		}catch (Exception e) {
			jsonObj.put("status", 0);
			jsonObj.put("reason", "上传失败");
			e.printStackTrace();
		}
		 response.getWriter().write(jsonObj.toString());
//		 System.out.println(jsonObj.toString());
	}

	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		imageService = webAppContext.getBean(ImageService.class);
	}
	
	public void processUploadedField(FileItem item,String image_path) throws Exception{
		long filesize = item.getSize();
		if(filesize==0)
			return;
		File uploadImage = new File(image_path);
		item.write(uploadImage);
	}

}
