/** 
 * <pre>项目名称:ssi-01 
 * 文件名称:Book.java 
 * 包名:com.jk.entity.book 
 * 创建日期:2017年4月13日下午3:04:59 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.entity.book;

import java.io.Serializable;

/** 
 * <pre>项目名称：ssi-01    
 * 类名称：Book    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月13日 下午3:04:59    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月13日 下午3:04:59    
 * 修改备注：       
 * @version </pre>    
 */
public class Book implements Serializable {

	private String bookName;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
}
