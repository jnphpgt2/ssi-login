/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:MenuDaoImpl.java 
 * 包名:com.jk.dao.menu.impl 
 * 创建日期:2017年4月20日下午4:33:35 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.menu.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.jk.dao.menu.MenuDao;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：MenuDaoImpl    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月20日 下午4:33:35    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月20日 下午4:33:35    
 * 修改备注：       
 * @version </pre>    
 */
@Repository
public class MenuDaoImpl extends SqlMapClientDaoSupport implements MenuDao {

	/* (non-Javadoc)    
	 * @see com.jk.dao.menu.MenuDao#selectMenuListJson(int)    
	 */
	@Override
	public List<Map<String, Object>> selectMenuListJson(int id) {
		return this.getSqlMapClientTemplate().queryForList("menu.selectMenuListJson", id);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.menu.MenuDao#insertMenu(java.util.Map)    
	 */
	@Override
	public void insertMenu(Map<String, Object> paraMap) {
		this.getSqlMapClientTemplate().insert("menu.insertMenu", paraMap);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.menu.MenuDao#selectMenuInfoByMenuID(java.util.Map)    
	 */
	@Override
	public Map<String, Object> selectMenuInfoByMenuID(Map<String, Object> paraMap) {
		return (Map<String, Object>) this.getSqlMapClientTemplate().queryForObject("menu.selectMenuInfoByMenuID", paraMap);
	}
}
