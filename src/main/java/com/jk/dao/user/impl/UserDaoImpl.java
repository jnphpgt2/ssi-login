/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:UserDaoImpl.java 
 * 包名:com.jk.dao.user.impl 
 * 创建日期:2017年4月17日上午10:26:17 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.user.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.dao.user.UserDao;
import com.jk.entity.user.User;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：UserDaoImpl    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月17日 上午10:26:17    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月17日 上午10:26:17    
 * 修改备注：       
 * @version </pre>    
 */
@Repository
public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#login(com.jk.entity.user.User)    
	 */
	@Override
	public User login(User user) {
		return (User) this.getSqlMapClientTemplate().queryForObject("user.login", user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#updateLoginFailedCount(com.jk.entity.user.User)    
	 */
	@Override
	public void updateLoginFailedCount(User u) {
		this.getSqlMapClientTemplate().update("user.updateLoginFailedCount", u);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#selectUserJsonList(com.jk.entity.user.User)    
	 */
	@Override
	public List<User> selectUserJsonList(User user) {
		return this.getSqlMapClientTemplate().queryForList("user.selectUserJsonList", user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#selectUserCount(com.jk.entity.user.User)    
	 */
	@Override
	public int selectUserCount(User user) {
		return (int) this.getSqlMapClientTemplate().queryForObject("user.selectUserCount", user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#insertUser(com.jk.entity.user.User)    
	 */
	@Override
	public void insertUser(User user) {
		this.getSqlMapClientTemplate().insert("user.insertUser", user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#selectUserByUserID(com.jk.entity.user.User)    
	 */
	@Override
	public User selectUserByUserID(User user) {
		return (User) this.getSqlMapClientTemplate().queryForObject("user.selectUserByUserID", user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#updateUserByUserID(com.jk.entity.user.User)    
	 */
	@Override
	public void updateUserByUserID(User user) {
		this.getSqlMapClientTemplate().update("user.updateUserByUserID", user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#selectUserRoleTree(com.jk.entity.user.User)    
	 */
	@Override
	public List<Map> selectUserRoleTree(User user) {
		return this.getSqlMapClientTemplate().queryForList("user.selectUserRoleTree", user);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#deleteAllRoleByUserID(java.lang.Integer)    
	 */
	@Override
	public void deleteAllRoleByUserID(Integer integer) {
		this.getSqlMapClientTemplate().delete("user.deleteAllRoleByUserID", integer);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#batchInsertRoleToUser(java.util.List)    
	 */
	@Override
	public void batchInsertRoleToUser(final List<Map<String, Integer>> fromJson) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			/* (non-Javadoc)    
			 * @see org.springframework.orm.ibatis.SqlMapClientCallback#doInSqlMapClient(com.ibatis.sqlmap.client.SqlMapExecutor)    
			 */
			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				//开启批量
				sqlMap.startBatch();
				//执行批量
				for (Map<String, Integer> map : fromJson) {
					sqlMap.insert("user.batchInsertRoleToUser", map);
				}
				//提交批量
				sqlMap.executeBatch();
				return null;
			}
		});
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#selectMainTreeList(int)    
	 */
	@Override
	public List<Map<String, Object>> selectMainTreeList(int id) {
		return this.getSqlMapClientTemplate().queryForList("user.selectMainTreeList", id);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#selectUserAllTreeList(com.jk.entity.user.User)    
	 */
	@Override
	public List<String> selectUserAllTreeList(User u) {
		return this.getSqlMapClientTemplate().queryForList("user.selectUserAllTreeList", u);
	}
}
