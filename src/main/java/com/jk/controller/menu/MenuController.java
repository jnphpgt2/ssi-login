/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:MenuController.java 
 * 包名:com.jk.controller.menu 
 * 创建日期:2017年4月20日下午3:39:47 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.controller.menu;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jk.service.menu.MenuService;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：MenuController    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月20日 下午3:39:47    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月20日 下午3:39:47    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
public class MenuController {

	@Resource
	private MenuService menuService;
	
	@RequestMapping("toMenuListPage")
	public String toMenuListPage() {
		return "menu/menu_list";
	}
	
//	@ResponseBody
//	@RequestMapping("selectMenuListJson")
//	public List<Map<String, Object>> selectMenuListJson(int id) {
//		List<Map<String, Object>> menuList = menuService.selectMenuListJson(id);
//		return menuList;
//	}
	
	@RequestMapping("selectMenuListJson")
	public @ResponseBody List<Map<String, Object>> selectMenuListJson(String id) {
		List<Map<String, Object>> menuList = null;
		if (null == id || "".equals(id)) {
			menuList = menuService.selectMenuListJson(0);
		}
		return menuList;
	}
	
	@RequestMapping("insertMenu")
	@ResponseBody
	public Map<String, Object> insertMenu(@RequestBody String parameterJson) {
		Map<String, Object> paraMap = new Gson().fromJson(parameterJson, new TypeToken<Map<String, Object>>(){}.getType());
		Map<String, Object> map = menuService.insertMenu(paraMap);
		return map;
	}
	
}
