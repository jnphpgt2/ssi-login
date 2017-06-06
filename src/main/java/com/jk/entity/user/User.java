/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:User.java 
 * 包名:com.jk.entity.user 
 * 创建日期:2017年4月17日上午10:18:14 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.entity.user;

import java.io.Serializable;
import java.util.Date;

import common.util.Page;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：User    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月17日 上午10:18:14    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月17日 上午10:18:14    
 * 修改备注：       
 * @version </pre>    
 */
public class User extends Page implements Serializable {

	private int userID;
	
	private String userAccount;
	
	private String userPwd;
	
	private String userName;
	
	private int userSex;
	
	private int userAge;
	
	private Date userBir;
	
	private String userBirStr;
	
	private int userFailedCount;
	
	private Date userUpdateDate;
	
	private String userImgCode;
	
	private String sysImgCode;

	public String getUserBirStr() {
		return userBirStr;
	}

	public void setUserBirStr(String userBirStr) {
		this.userBirStr = userBirStr;
	}

	public String getUserImgCode() {
		return userImgCode;
	}

	public void setUserImgCode(String userImgCode) {
		this.userImgCode = userImgCode;
	}

	public String getSysImgCode() {
		return sysImgCode;
	}

	public void setSysImgCode(String sysImgCode) {
		this.sysImgCode = sysImgCode;
	}

	public Date getUserUpdateDate() {
		return userUpdateDate;
	}

	public void setUserUpdateDate(Date userUpdateDate) {
		this.userUpdateDate = userUpdateDate;
	}

	public int getUserFailedCount() {
		return userFailedCount;
	}

	public void setUserFailedCount(int userFailedCount) {
		this.userFailedCount = userFailedCount;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public Date getUserBir() {
		return userBir;
	}

	public void setUserBir(Date userBir) {
		this.userBir = userBir;
	}
}
