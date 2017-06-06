/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:UserServiceImpl.java 
 * 包名:com.jk.service.user.impl 
 * 创建日期:2017年4月17日上午10:25:11 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jk.dao.user.UserDao;
import com.jk.entity.user.User;
import com.jk.service.user.UserService;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：UserServiceImpl    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月17日 上午10:25:11    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月17日 上午10:25:11    
 * 修改备注：       
 * @version </pre>    
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#login(com.jk.entity.user.User)    
	 */
	@Override
	public Map<String, Object> login(User user) {
		//在map中放一个键值对，flag：数字（0，登陆成功；1，用户名错误；2，密码错误）
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("failed_count", 0);
		map.put("flag", 1);
		map.put("userInfo", null);
		
		//首先判断验证码
		if (null == user.getUserImgCode()) {
			//验证码为空
			map.put("flag", 4);
		} else if (user.getUserImgCode().equals(user.getSysImgCode())) {
			User u = userDao.login(user);
			if (null != u) {
				//已登录失败3次，需要判断日期是否超过了5分钟
				long userUpdateDate = u.getUserUpdateDate().getTime();
				long time = new Date().getTime();
				
				//判断失败次数
				if (0 == u.getUserFailedCount() || 0 < u.getUserFailedCount() % 3
						|| (0 == u.getUserFailedCount() % 3 && 1000 * 60 * 5 <= time - userUpdateDate)) {
					if (null != u.getUserPwd() && u.getUserPwd().equals(user.getUserPwd())) {
						u.setUserFailedCount(0);
						userDao.updateLoginFailedCount(u);
						map.put("flag", 0);
						map.put("userInfo", u);
					} else {
						//密码错误，需要修改错误次数+1
						u.setUserFailedCount(u.getUserFailedCount() + 1);
						userDao.updateLoginFailedCount(u);
						map.put("failed_count", u.getUserFailedCount());
						map.put("flag", 2);
					}
				} else {
					map.put("failed_count", u.getUserFailedCount());
					map.put("flag", 3);
				}
			}
		} else {
			//验证码错误
			map.put("flag", 5);
		}
		return map;
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#selectUserJsonList(com.jk.entity.user.User)    
	 */
	@Override
	public List<User> selectUserJsonList(User user) {
		return userDao.selectUserJsonList(user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#selectUserCount(com.jk.entity.user.User)    
	 */
	@Override
	public int selectUserCount(User user) {
		return userDao.selectUserCount(user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#insertUser(com.jk.entity.user.User)    
	 */
	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#selectUserByUserID(com.jk.entity.user.User)    
	 */
	@Override
	public User selectUserByUserID(User user) {
		return userDao.selectUserByUserID(user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#updateUserByUserID(com.jk.entity.user.User)    
	 */
	@Override
	public void updateUserByUserID(User user) {
		userDao.updateUserByUserID(user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#selectUserRoleTree(com.jk.entity.user.User)    
	 */
	@Override
	public List<Map> selectUserRoleTree(User user) {
		List<Map> mapList = userDao.selectUserRoleTree(user);
		if (null != mapList && 0 < mapList.size()) {
			for (Map map : mapList) {
				Integer userID = Integer.valueOf(map.get("userID").toString());
				if (user.getUserID() == userID) {
					map.put("checked", 1);
				}
			}
		}
		return mapList;
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#updateUserRole(java.util.List)    
	 */
	@Override
	public void updateUserRole(List<Map<String, Integer>> fromJson) {
		if (null != fromJson && 0 < fromJson.size()) {
			//1、删除这个用户之前所有的角色（操作的是用户角色中间表）
			userDao.deleteAllRoleByUserID(fromJson.get(0).get("userID"));
			//2、把刚刚接到的这个用户的新角色添加到数据库中（操作的是用户角色中间表）
			userDao.batchInsertRoleToUser(fromJson);
		}
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#selectMainTreeList(java.lang.String)    
	 */
	@Override
	public List<Map<String, Object>> selectMainTreeList(int id) {
		List<Map<String, Object>> list = userDao.selectMainTreeList(id);
		if (null != list && 0 < list.size()) {
			for (Map<String, Object> map : list) {
				//从当前元素中去除leaf属性，判断是否是叶子节点
				int menuLeaf = Integer.valueOf(map.get("leaf").toString());
				if (0 == menuLeaf) {
					map.put("children", selectMainTreeList(Integer.valueOf(map.get("id").toString())));
				}
			}
		}
		return list;
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#selectUserAllTreeList(com.jk.entity.user.User)    
	 */
	@Override
	public List<String> selectUserAllTreeList(User u) {
		return userDao.selectUserAllTreeList(u);
	}
}
