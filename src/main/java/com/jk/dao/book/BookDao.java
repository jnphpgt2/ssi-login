/** 
 * <pre>项目名称:ssi-01 
 * 文件名称:BookDao.java 
 * 包名:com.jk.dao.book 
 * 创建日期:2017年4月13日下午3:20:36 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.book;

import java.util.List;

import com.jk.entity.book.Book;

/** 
 * <pre>项目名称：ssi-01    
 * 类名称：BookDao    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月13日 下午3:20:36    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月13日 下午3:20:36    
 * 修改备注：       
 * @version </pre>    
 */
public interface BookDao {

	/** <pre>test(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月13日 下午3:21:52    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月13日 下午3:21:52    
	 * 修改备注： 
	 * @param book</pre>    
	 */
	void test(Book book);

	/** <pre>insertBook(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月13日 下午3:41:29    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月13日 下午3:41:29    
	 * 修改备注： 
	 * @param book</pre>    
	 */
	void insertBook(Book book);

	/** <pre>selectBookList(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月13日 下午3:53:58    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月13日 下午3:53:58    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Book> selectBookList();

}
