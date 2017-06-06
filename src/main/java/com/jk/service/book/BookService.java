/** 
 * <pre>项目名称:ssi-01 
 * 文件名称:BookService.java 
 * 包名:com.jk.service.book 
 * 创建日期:2017年4月13日下午3:12:35 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.book;

import java.util.List;

import com.jk.entity.book.Book;

/** 
 * <pre>项目名称：ssi-01    
 * 类名称：BookService    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月13日 下午3:12:35    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月13日 下午3:12:35    
 * 修改备注：       
 * @version </pre>    
 */
public interface BookService {

	/** <pre>test(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月13日 下午3:13:36    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月13日 下午3:13:36    
	 * 修改备注： 
	 * @param book</pre>    
	 */
	void test(Book book);

	/** <pre>insertBook(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月13日 下午3:41:06    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月13日 下午3:41:06    
	 * 修改备注： 
	 * @param book</pre>    
	 */
	void insertBook(Book book);

	/** <pre>selectBookList(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年4月13日 下午3:53:04    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年4月13日 下午3:53:04    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Book> selectBookList();

}
