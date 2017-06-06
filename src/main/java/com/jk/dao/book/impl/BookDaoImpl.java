/** 
 * <pre>项目名称:ssi-01 
 * 文件名称:BookDaoImpl.java 
 * 包名:com.jk.dao.book.impl 
 * 创建日期:2017年4月13日下午3:21:25 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.book.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.jk.dao.book.BookDao;
import com.jk.entity.book.Book;

/** 
 * <pre>项目名称：ssi-01    
 * 类名称：BookDaoImpl    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月13日 下午3:21:25    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月13日 下午3:21:25    
 * 修改备注：       
 * @version </pre>    
 */
@Repository
public class BookDaoImpl extends SqlMapClientDaoSupport implements BookDao {

	/* (non-Javadoc)    
	 * @see com.jk.dao.book.BookDao#test(com.jk.entity.book.Book)    
	 */
	@Override
	public void test(Book book) {
		System.out.println("dao层" + book.getBookName());
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.book.BookDao#insertBook(com.jk.entity.book.Book)    
	 */
	@Override
	public void insertBook(Book book) {
		this.getSqlMapClientTemplate().insert("book.insertBook", book);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.book.BookDao#selectBookList()    
	 */
	@Override
	public List<Book> selectBookList() {
		return this.getSqlMapClientTemplate().queryForList("book.selectBookList");
	}
}
