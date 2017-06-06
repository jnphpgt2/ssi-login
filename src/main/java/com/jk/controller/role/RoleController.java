/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:RoleController.java 
 * 包名:com.jk.controller.role 
 * 创建日期:2017年4月19日上午9:15:01 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jk.entity.role.Role;
import com.jk.service.role.RoleService;

import common.util.JsonOutUtil;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：RoleController    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月19日 上午9:15:01    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月19日 上午9:15:01    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@RequestMapping("toRoleListPage")
	public String toRoleListPage() {
		return "role/role_list";
	}
	
	@RequestMapping("selectRoleJsonList")
	public void selectRoleJsonList(Role role, int rows, int page, HttpServletResponse response) {
		//查询总条数
		int total = roleService.selectRoleCount(role);
		if (0 == page) {
			page = 1;
		}
		if (0 == rows) {
			rows = 3;
		}
		role.setPageIndex(page);
		role.setPageSize(rows);
		role.setTotalCount(total);
		role.countInfo();
		List<Role> roleList = roleService.selectRoleJsonList(role);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", roleList);
		String json = new Gson().toJson(map);
		JsonOutUtil.jsonOut(json, response);
	}
	
	@RequestMapping("toAddRolePage")
	public String toAddRolePage() {
		return "role/add_role";
	}
	
	@RequestMapping("insertRole")
	public void insertRole(Role role, HttpServletResponse response) {
		roleService.insertRole(role);
		JsonOutUtil.jsonOut("{}", response);
	}
	
	@RequestMapping("selectRoleInfoByRoleID")
	public String selectRoleInfoByRoleID(Role role, Model model) {
		Role r = roleService.selectRoleInfoByRoleID(role);
		model.addAttribute("role", r);
		return "role/edit_role";
	}
	
	@RequestMapping("updateRoleByRoleID")
	public void updateRoleByRoleID(Role role, HttpServletResponse response) {
		roleService.updateRoleByRoleID(role);
		JsonOutUtil.jsonOut("{}", response);
	}
	
	@RequestMapping("toRoleMenuPage")
	public String toRoleMenuPage(Role role, Model model) {
		model.addAttribute("role", role);
		return "role/role_menu";
	}
	
	@RequestMapping("selectRoleMenuTree")
	@ResponseBody
	public List<Map<String, Object>> selectRoleMenuTree(String id, Role role) {
		List<Map<String, Object>> list = null;
		System.out.println(id);
		if (null == id || "".equals(id)) {
			Map<String, Integer> m = new HashMap<String, Integer>();
			m.put("menuID", 0);
			m.put("roleID", role.getRoleID());
			list = roleService.selectRoleMenuTree(m);
		}
		
		return list;
	}
	
	@RequestMapping
	@ResponseBody
	public String deleteRoleByRoleID(Role role) {
		roleService.deleteRoleByRoleID(role);
		return "{\"flag\":0}";
	}
}
