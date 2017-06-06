/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:MenuServiceImpl.java 
 * 包名:com.jk.service.menu.impl 
 * 创建日期:2017年4月20日下午4:32:27 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.menu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jk.dao.menu.MenuDao;
import com.jk.service.menu.MenuService;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：MenuServiceImpl    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月20日 下午4:32:27    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月20日 下午4:32:27    
 * 修改备注：       
 * @version </pre>    
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao menuDao;
	
	/* (non-Javadoc)    
	 * @see com.jk.service.menu.MenuService#selectMenuListJson(int)    
	 */
	@Override
	public List<Map<String, Object>> selectMenuListJson(int id) {
		List<Map<String, Object>> menuList = menuDao.selectMenuListJson(id);
		if (null != menuList && 0 < menuList.size()) {
			for (Map<String, Object> map : menuList) {
				String leafStr = map.get("leaf").toString();
				if (0 == Integer.valueOf(leafStr)) {
					//不是叶子节点，调用本身查询子节点集合
					String idStr = map.get("id").toString();
					map.put("children", selectMenuListJson(Integer.valueOf(idStr)));
				}
			}
		}
		return menuList;
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.menu.MenuService#insertMenu(java.util.Map)    
	 */
	@Override
	public Map<String, Object> insertMenu(Map<String, Object> paraMap) {
		//1、添加节点
		menuDao.insertMenu(paraMap);
		//2、根据ID查询出刚刚添加的节点
		Map<String, Object> map = menuDao.selectMenuInfoByMenuID(paraMap);
		return map;
	}
}
