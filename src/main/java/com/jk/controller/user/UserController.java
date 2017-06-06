/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:UserController.java 
 * 包名:com.jk.controller.book 
 * 创建日期:2017年4月17日上午10:22:06 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jk.entity.user.User;
import com.jk.service.user.UserService;

import common.base.MySessionContext;
import common.util.IPUtil;
import common.util.JsonOutUtil;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：UserController    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月17日 上午10:22:06    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月17日 上午10:22:06    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("login")
	public void login(User user, HttpServletRequest request, HttpServletResponse response) {
		String ip = IPUtil.obtainIP(request);
        System.out.println("客户端的IP地址是" + ip);
		
		//获取session对象
		HttpSession session = request.getSession();
		//从session中获取后台验证码
		String string = session.getAttribute("imageCode").toString();
		user.setSysImgCode(string);
		Map<String, Object> map = userService.login(user);
		//判断登陆成功
		if (0 == Integer.valueOf(map.get("flag").toString())) {
			//首先获取用户信息
			User u = (User) map.get("userInfo");
			//从数据库查询出这个用户完整的权限树
			List<String> userAllTreeList = userService.selectUserAllTreeList(u);
			session.setAttribute("userTree", userAllTreeList);
			//把用户信息放进session，登录验证的时候用来判断用户是否登录
			session.setAttribute("userInfo", u);
			//设置session过期时间(单位：秒钟)
			session.setMaxInactiveInterval(600);
			
			//获取map中保存的session
			Map<String, HttpSession> findSession = MySessionContext.findSession(u.getUserID());
			if (null != findSession) {
				//移除本用户上次登录保存的session
				MySessionContext.delSession(u.getUserID(), session);
			}
			//保存这个session
			MySessionContext.addSession(u.getUserID(), session);
		}
		String json = new Gson().toJson(map);
		System.out.println(json);
		JsonOutUtil.jsonOut(json, response);
	}
	
	@RequestMapping("toMainPage")
	public String toMainPage() {
		return "index";
	}
	
	@RequestMapping("toUserListPage")
	public String toUserListPage() {
		return "user/user_list";
	}
	
	@RequestMapping("selectUserJsonList")
	public void selectUserJsonList(User user, int rows, int page, HttpServletResponse response) {
		//查询总条数
		int totalCount = userService.selectUserCount(user);
		user.setTotalCount(totalCount);
		if (0 == page) {
			page = 1;
		}
		if (0 == rows) {
			rows = 3;
		}
		user.setPageIndex(page);
		user.setPageSize(rows);
		user.countInfo();
		List<User> userList = userService.selectUserJsonList(user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totalCount);
		map.put("rows", userList);
		String json = new Gson().toJson(map);
		JsonOutUtil.jsonOut(json, response);
	}
	
	@RequestMapping("toAddUserPage")
	public String toAddUserPage() {
		return "user/add_user";
	}
	
	@RequestMapping("insertUser")
	public void insertUser(User user, HttpServletResponse response) {
		userService.insertUser(user);
		JsonOutUtil.jsonOut("{}", response);
	}
	
	@RequestMapping("toEditUserPage")
	public String toEditUserPage(User user, Model model) {
		User u = userService.selectUserByUserID(user);
		model.addAttribute("user", u);
		return "user/edit_user";
	}
	
	@RequestMapping("updateUserByUserID")
	public void updateUserByUserID(User user, HttpServletResponse response) {
		userService.updateUserByUserID(user);
		JsonOutUtil.jsonOut("{}", response);
	}
	
	@RequestMapping("toUserRolePage")
	public String toUserRolePage(User user, Model model) {
		model.addAttribute("user", user);
		return "user/user_role";
	}
	
	@RequestMapping("selectUserRoleTree")
	public void selectUserRoleTree(User user, HttpServletResponse response) {
		List<Map> userRoleTreeList = userService.selectUserRoleTree(user);
		String json = new Gson().toJson(userRoleTreeList);
		JsonOutUtil.jsonOut(json, response);
	}
	
	@RequestMapping("updateUserRole")
	public void updateUserRole(@RequestBody String parameterJson, HttpServletResponse response) {
		List<Map<String,Integer>> fromJson = new Gson().fromJson(parameterJson, new TypeToken<List<Map<String,Integer>>>() {}.getType());
		userService.updateUserRole(fromJson);
		JsonOutUtil.jsonOut("{}", response);
	}
	
	@RequestMapping("selectMainTreeList")
	@ResponseBody
	public List<Map<String, Object>> selectMainTreeList(String id) {
		List<Map<String, Object>> list = null;
		if (null == id || "".equals(id)) {
			list = userService.selectMainTreeList(0);
		}
		return list;
	}
	
	@RequestMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		//销毁session对象
		request.getSession().invalidate();
		//重定向请求到登录页面
		try {
			response.sendRedirect("login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
