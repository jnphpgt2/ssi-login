/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:RoleService.java 
 * 包名:com.jk.service.role 
 * 创建日期:2017年4月19日上午9:24:09 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.role;

import java.util.List;
import java.util.Map;

import com.jk.entity.role.Role;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：RoleService    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月19日 上午9:24:09    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月19日 上午9:24:09    
 * 修改备注：       
 * @version </pre>    
 */
public interface RoleService {

	/** <pre>selectRoleCount(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月19日 上午9:27:23    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月19日 上午9:27:23    
	 * 修改备注： 
	 * @param role
	 * @return</pre>    
	 */
	int selectRoleCount(Role role);

	/** <pre>selectRoleJsonList(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月19日 上午9:35:54    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月19日 上午9:35:54    
	 * 修改备注： 
	 * @param role
	 * @return</pre>    
	 */
	List<Role> selectRoleJsonList(Role role);

	/** <pre>insertRole(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月19日 上午10:11:05    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月19日 上午10:11:05    
	 * 修改备注： 
	 * @param role</pre>    
	 */
	void insertRole(Role role);

	/** <pre>selectRoleInfoByRoleID(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月19日 上午10:44:28    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月19日 上午10:44:28    
	 * 修改备注： 
	 * @param role
	 * @return</pre>    
	 */
	Role selectRoleInfoByRoleID(Role role);

	/** <pre>updateRoleByRoleID(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月19日 下午1:41:51    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月19日 下午1:41:51    
	 * 修改备注： 
	 * @param role</pre>    
	 */
	void updateRoleByRoleID(Role role);

	List<Map<String, Object>> selectRoleMenuTree(Map<String, Integer> map);

	/** <pre>deleteRoleByRoleID(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月24日 上午10:38:14    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月24日 上午10:38:14    
	 * 修改备注： 
	 * @param role</pre>    
	 */
	void deleteRoleByRoleID(Role role);

}
