/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:UserDao.java 
 * 包名:com.jk.dao.user 
 * 创建日期:2017年4月17日上午10:25:53 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.user;

import java.util.List;
import java.util.Map;

import com.jk.entity.user.User;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：UserDao    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月17日 上午10:25:53    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月17日 上午10:25:53    
 * 修改备注：       
 * @version </pre>    
 */
public interface UserDao {

	/** <pre>login(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月17日 上午10:31:41    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月17日 上午10:31:41    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	User login(User user);

	/** <pre>updateLoginFailedCount(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月17日 下午1:48:03    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月17日 下午1:48:03    
	 * 修改备注： 
	 * @param u</pre>    
	 */
	void updateLoginFailedCount(User u);

	/** <pre>selectUserJsonList(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月18日 上午11:19:59    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月18日 上午11:19:59    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	List<User> selectUserJsonList(User user);

	/** <pre>selectUserCount(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月18日 下午1:53:39    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月18日 下午1:53:39    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	int selectUserCount(User user);

	/** <pre>insertUser(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月18日 下午2:56:25    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月18日 下午2:56:25    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	void insertUser(User user);

	/** <pre>selectUserByUserID(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月18日 下午4:31:57    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月18日 下午4:31:57    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	User selectUserByUserID(User user);

	/** <pre>updateUserByUserID(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月18日 下午4:43:06    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月18日 下午4:43:06    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	void updateUserByUserID(User user);

	/** <pre>selectUserRoleTree(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月19日 下午4:54:20    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月19日 下午4:54:20    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	List<Map> selectUserRoleTree(User user);

	/** <pre>deleteAllRoleByUserID(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月20日 下午2:47:50    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月20日 下午2:47:50    
	 * 修改备注： 
	 * @param integer</pre>    
	 */
	void deleteAllRoleByUserID(Integer integer);

	/** <pre>batchInsertRoleToUser(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月20日 下午2:58:56    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月20日 下午2:58:56    
	 * 修改备注： 
	 * @param fromJson</pre>    
	 */
	void batchInsertRoleToUser(List<Map<String, Integer>> fromJson);

	/** <pre>selectMainTreeList(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月24日 下午2:07:36    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月24日 下午2:07:36    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	List<Map<String, Object>> selectMainTreeList(int id);

	/** <pre>selectUserAllTreeList(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月24日 下午4:22:15    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月24日 下午4:22:15    
	 * 修改备注： 
	 * @param u
	 * @return</pre>    
	 */
	List<String> selectUserAllTreeList(User u);

}
