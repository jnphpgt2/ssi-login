/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:RoleDaoImpl.java 
 * 包名:com.jk.dao.role.impl 
 * 创建日期:2017年4月19日上午9:26:33 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.role.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.dao.role.RoleDao;
import com.jk.entity.role.Role;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：RoleDaoImpl    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月19日 上午9:26:33    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月19日 上午9:26:33    
 * 修改备注：       
 * @version </pre>    
 */
@Repository
public class RoleDaoImpl extends SqlMapClientDaoSupport implements RoleDao {

	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#selectRoleCount(com.jk.entity.role.Role)    
	 */
	@Override
	public int selectRoleCount(Role role) {
		return (int) this.getSqlMapClientTemplate().queryForObject("role.selectRoleCount", role);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#selectRoleJsonList(com.jk.entity.role.Role)    
	 */
	@Override
	public List<Role> selectRoleJsonList(Role role) {
		return this.getSqlMapClientTemplate().queryForList("role.selectRoleJsonList", role);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#insertRole(com.jk.entity.role.Role)    
	 */
	@Override
	public void insertRole(Role role) {
		this.getSqlMapClientTemplate().insert("role.insertRole", role);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#selectRoleInfoByRoleID(com.jk.entity.role.Role)    
	 */
	@Override
	public Role selectRoleInfoByRoleID(Role role) {
		return (Role) this.getSqlMapClientTemplate().queryForObject("role.selectRoleInfoByRoleID", role);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#updateRoleByRoleID(com.jk.entity.role.Role)    
	 */
	@Override
	public void updateRoleByRoleID(Role role) {
		this.getSqlMapClientTemplate().update("role.updateRoleByRoleID", role);
	}
	
	@Override
	public List<Map<String, Object>> selectAllMenuTree(Map<String, Integer> m) {
		return this.getSqlMapClientTemplate().queryForList("menu.selectMenuListJson", m.get("menuID"));
	}
	
	@Override
	public List<Map<String, Object>> selectRoleMenuTree(Map<String, Integer> m) {
		return this.getSqlMapClientTemplate().queryForList("role.selectRoleMenuTree", m.get("roleID"));
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#deleteRoleByRoleID(com.jk.entity.role.Role)    
	 */
	@Override
	public void deleteRoleByRoleID(final Role role) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			/* (non-Javadoc)    
			 * @see org.springframework.orm.ibatis.SqlMapClientCallback#doInSqlMapClient(com.ibatis.sqlmap.client.SqlMapExecutor)    
			 */
			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				//开启批量
				sqlMap.startBatch();
				//执行批量
				sqlMap.delete("role.deleteRoleByRoleID", role);
				sqlMap.delete("role.deleteRoleMenuByRoleID", role);
				sqlMap.delete("role.deleteUserRoleByRoleID", role);
				//提交批量
				sqlMap.executeBatch();
				return null;
			}
		});
	}
}
