/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:MenuService.java 
 * 包名:com.jk.service.menu 
 * 创建日期:2017年4月20日下午4:31:52 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.menu;

import java.util.List;
import java.util.Map;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：MenuService    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月20日 下午4:31:52    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月20日 下午4:31:52    
 * 修改备注：       
 * @version </pre>    
 */
public interface MenuService {

	/** <pre>selectMenuListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月20日 下午4:35:29    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月20日 下午4:35:29    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	List<Map<String, Object>> selectMenuListJson(int id);

	/** <pre>insertMenu(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月20日 下午5:50:22    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月20日 下午5:50:22    
	 * 修改备注： 
	 * @param paraMap
	 * @return</pre>    
	 */
	Map<String, Object> insertMenu(Map<String, Object> paraMap);

}
