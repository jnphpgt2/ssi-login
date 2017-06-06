/** 
 * <pre>项目名称:ssi-01 
 * 文件名称:BookServiceImpl.java 
 * 包名:com.jk.service.book.impl 
 * 创建日期:2017年4月13日下午3:12:51 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.book.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.book.BookDao;
import com.jk.entity.book.Book;
import com.jk.service.book.BookService;

/** 
 * <pre>项目名称：ssi-01    
 * 类名称：BookServiceImpl    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月13日 下午3:12:51    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月13日 下午3:12:51    
 * 修改备注：       
 * @version </pre>    
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	/* (non-Javadoc)    
	 * @see com.jk.service.book.BookService#test(com.jk.entity.book.Book)    
	 */
	@Override
	public void test(Book book) {
		System.out.println("service层" + book.getBookName());
		bookDao.test(book);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.book.BookService#insertBook(com.jk.entity.book.Book)    
	 */
	@Override
	public void insertBook(Book book) {
		bookDao.insertBook(book);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.book.BookService#selectBookList()    
	 */
	@Override
	public List<Book> selectBookList() {
		return bookDao.selectBookList();
	}
}
