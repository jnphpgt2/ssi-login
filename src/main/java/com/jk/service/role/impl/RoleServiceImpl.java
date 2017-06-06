/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:RoleServiceImpl.java 
 * 包名:com.jk.service.role.impl 
 * 创建日期:2017年4月19日上午9:24:32 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.role.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.role.RoleDao;
import com.jk.entity.role.Role;
import com.jk.service.role.RoleService;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：RoleServiceImpl    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月19日 上午9:24:32    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月19日 上午9:24:32    
 * 修改备注：       
 * @version </pre>    
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#selectRoleCount(com.jk.entity.role.Role)    
	 */
	@Override
	public int selectRoleCount(Role role) {
		return roleDao.selectRoleCount(role);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#selectRoleJsonList(com.jk.entity.role.Role)    
	 */
	@Override
	public List<Role> selectRoleJsonList(Role role) {
		return roleDao.selectRoleJsonList(role);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#insertRole(com.jk.entity.role.Role)    
	 */
	@Override
	public void insertRole(Role role) {
		roleDao.insertRole(role);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#selectRoleInfoByRoleID(com.jk.entity.role.Role)    
	 */
	@Override
	public Role selectRoleInfoByRoleID(Role role) {
		return roleDao.selectRoleInfoByRoleID(role);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#updateRoleByRoleID(com.jk.entity.role.Role)    
	 */
	@Override
	public void updateRoleByRoleID(Role role) {
		roleDao.updateRoleByRoleID(role);
	}
	
	@Override
	public List<Map<String, Object>> selectRoleMenuTree(Map<String, Integer> m) {
		//1、查询出所有的权限
		List<Map<String, Object>> menuList = selectAllMenuTree(m);
		//2、查询出本角色的权限
		List<Map<String, Object>> roleMenuIDList = roleDao.selectRoleMenuTree(m);
		//3、循环所有的权限，与本角色权限对比（比ID），如果ID相同，这个权限就被勾选，否则不勾选
		bijiaoquanxian(menuList, roleMenuIDList);
		return menuList;
	}
	
	
	private void bijiaoquanxian(List<Map<String, Object>> menuList, List<Map<String, Object>> roleMenuIDList) {
		if (null != menuList && 0 <menuList.size()) {
			for (Map<String, Object> map1 : menuList) {
				//判断不是root和模块
				String pidStr = map1.get("pid").toString();
				if (1 >= Integer.valueOf(pidStr)) {
					List<Map<String, Object>> menu2List = (List<Map<String, Object>>) map1.get("children");
					bijiaoquanxian(menu2List, roleMenuIDList);
				} else {
					//这种情况是获取到了权限集合
					//循环角色自己的权限
					if (null != roleMenuIDList && 0 < roleMenuIDList.size()) {
						for (Map<String, Object> mapID : roleMenuIDList) {
							//获取权限ID
							//判断不是root和模块
							String idStr = map1.get("id").toString();
							String id2Str = mapID.get("menuID").toString();
							int a1 = Integer.valueOf(idStr);
							int a2 = Integer.valueOf(id2Str);
							System.out.println(a1 == a2);
							//把权限ID和当前角色的权限ID对比，如果一样就勾选，如果不一样就无所谓
							if (a1 == a2) {
								//勾选此项
								map1.put("checked", 1);
							}
						}
					}
				}
			}
		}
	}
	
	
	private List<Map<String, Object>> selectAllMenuTree(Map<String, Integer> m) {
		//1、查询出所有的权限
		List<Map<String, Object>> menuList = roleDao.selectAllMenuTree(m);
		if (null != menuList && 0 < menuList.size()) {
			for (Map<String, Object> map : menuList) {
				String leafStr = map.get("leaf").toString();
				if (0 == Integer.valueOf(leafStr)) {
					//不是叶子节点，调用本身查询子节点集合
					String idStr = map.get("id").toString();
					Map<String, Integer> m2 = new HashMap<String, Integer>();
					m2.put("roleID", m.get("roleID"));
					m2.put("menuID", Integer.valueOf(idStr));
					map.put("children", selectAllMenuTree(m2));
				}
			}
		}
		return menuList;
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#deleteRoleByRoleID(com.jk.entity.role.Role)    
	 */
	@Override
	public void deleteRoleByRoleID(Role role) {
		//1、删除角色表
		//2、删除角色-权限关联表
		//3、删除用户-角色关联表
		
		//直接把参数传给dao层，开启批量删除多表
		roleDao.deleteRoleByRoleID(role);
	}
}
