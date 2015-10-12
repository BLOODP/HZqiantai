package com.qiantai_business.utl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 反射处理
 * @author hadoop
 *
 */
public class ReflectHandler {
	
	
	/**
	 * 获取所有属性
	 * @param obj
	 * @return
	 */
	public static Field[] getFields(Object obj) {
		return obj.getClass().getDeclaredFields();
	}
	
	public static String[] getFieldName(Object obj){
		
		Field[] fields = getFields(obj);
		int length = fields.length;
		String[] str = new String[length];
		for(int i=0;i<length;i++){
			str[i] = fields[i].getName();
		}
		
		return str;
	}
	
	public static String getFieldValue(Object obj,String fieldName){
		
		String name = fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
		String getter = "get"+name;
		Object result = null;
		
		try {
			Method method = obj.getClass().getMethod(getter);
			result = method.invoke(obj, new Object[]{});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if(result!=null){
			if(name.endsWith("submitdate")){
				DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				return df.format(result);
			}
			return result.toString();
			
		}
		
		return "";
	}

}
