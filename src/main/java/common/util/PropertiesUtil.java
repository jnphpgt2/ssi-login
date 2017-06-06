/** 
 * <pre>项目名称:mongo-02 
 * 文件名称:PropertiesUtil.java 
 * 包名:common.util 
 * 创建日期:2017年3月27日下午1:56:05 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 
 * <pre>项目名称：mongo-02    
 * 类名称：PropertiesUtil    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年3月27日 下午1:56:05    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年3月27日 下午1:56:05    
 * 修改备注：       
 * @version </pre>    
 */
public class PropertiesUtil {

	public static String getProperty(String key) {
		String value = null;
		//加载properties文件
		Properties p = new Properties();
		try {
			InputStream resourceAsStream = PropertiesUtil.class.getResourceAsStream("/resources/datasource.properties");
			p.load(resourceAsStream);
			value = p.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
