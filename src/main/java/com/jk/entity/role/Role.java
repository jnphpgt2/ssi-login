/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:Role.java 
 * 包名:com.jk.entity.role 
 * 创建日期:2017年4月19日上午9:22:03 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.entity.role;

import java.io.Serializable;

import common.util.Page;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：Role    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月19日 上午9:22:03    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月19日 上午9:22:03    
 * 修改备注：       
 * @version </pre>    
 */
public class Role extends Page implements Serializable {

	private int roleID;
	
	private String roleName;
	
	private String roleDesc;

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
}
