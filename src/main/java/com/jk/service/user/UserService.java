/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:UserService.java 
 * 包名:com.jk.service.user 
 * 创建日期:2017年4月17日上午10:24:51 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.user;

import java.util.List;
import java.util.Map;

import com.jk.entity.user.User;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：UserService    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月17日 上午10:24:51    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月17日 上午10:24:51    
 * 修改备注：       
 * @version </pre>    
 */
public interface UserService {

	/** <pre>login(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月17日 上午10:27:54    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月17日 上午10:27:54    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	Map<String, Object> login(User user);

	/** <pre>selectUserJsonList(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月18日 上午11:19:17    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月18日 上午11:19:17    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	List<User> selectUserJsonList(User user);

	/** <pre>selectUserCount(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月18日 下午1:53:11    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月18日 下午1:53:11    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	int selectUserCount(User user);

	/** <pre>insertUser(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月18日 下午2:55:51    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月18日 下午2:55:51    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	void insertUser(User user);

	/** <pre>selectUserByUserID(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月18日 下午4:22:05    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月18日 下午4:22:05    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	User selectUserByUserID(User user);

	/** <pre>updateUserByUserID(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月18日 下午4:42:27    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月18日 下午4:42:27    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	void updateUserByUserID(User user);

	/** <pre>selectUserRoleTree(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月19日 下午4:53:56    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月19日 下午4:53:56    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	List<Map> selectUserRoleTree(User user);

	/** <pre>updateUserRole(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月20日 下午2:43:43    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月20日 下午2:43:43    
	 * 修改备注： 
	 * @param fromJson</pre>    
	 */
	void updateUserRole(List<Map<String, Integer>> fromJson);

	/** <pre>selectMainTreeList(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月24日 下午1:58:16    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月24日 下午1:58:16    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	List<Map<String, Object>> selectMainTreeList(int id);

	/** <pre>selectUserAllTreeList(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月24日 下午4:21:44    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月24日 下午4:21:44    
	 * 修改备注： 
	 * @param u
	 * @return</pre>    
	 */
	List<String> selectUserAllTreeList(User u);

}
