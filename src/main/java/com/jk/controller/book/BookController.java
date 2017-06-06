/** 
 * <pre>项目名称:ssi-01 
 * 文件名称:BookController.java 
 * 包名:com.jk.controller.book 
 * 创建日期:2017年4月13日下午2:09:27 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.controller.book;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jk.entity.book.Book;
import com.jk.service.book.BookService;

/** 
 * <pre>项目名称：ssi-01    
 * 类名称：BookController    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月13日 下午2:09:27    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月13日 下午2:09:27    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
public class BookController {
	
	@Resource
	private BookService bookService;

//	@RequestMapping("test")
//	public ModelAndView test(String name) {
//		System.out.println(this.hashCode());
//		System.out.println(name + "你的顽抗让我诗兴大发");
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("title", name);
//		mv.setViewName("../aaa");
//		return mv;
//	}
	
//	@RequestMapping("test")
//	public String test(String name, Model model) {
//		System.out.println(this.hashCode());
//		System.out.println(name + "你的顽抗让我诗兴大发");
//		model.addAttribute("title", name);
//		return "../aaa";
//	}
	
//	@RequestMapping("test")
//	public String test(String name, HttpServletRequest request) {
//		System.out.println(this.hashCode());
//		System.out.println(name + "你的顽抗让我诗兴大发");
//		request.setAttribute("title", name);
//		return "../aaa";
//	}
	
//	@RequestMapping("test")
//	public String test(String name, ModelMap mm) {
//		System.out.println(this.hashCode());
//		System.out.println(name + "你的顽抗让我诗兴大发");
//		mm.addAttribute("title", name);
//		return "../aaa";
//	}
	
	@RequestMapping("test")
	public String test(Book book) {
		System.out.println(this.hashCode());
		System.out.println(book.getBookName() + "你的顽抗让我诗兴大发");
		bookService.test(book);
		return "../aaa";
	}
	
	@RequestMapping("insertBook")
	public String insertBook(Book book) {
		bookService.insertBook(book);
		return "redirect:selectBookList.jhtml";
	}
	
	@RequestMapping("selectBookList")
	public ModelAndView selectBookList(Book book) {
		System.out.println("book:请求已经进入控制层");
		List<Book> boList = bookService.selectBookList();
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", boList);
		mv.setViewName("book/list");
		return mv;
	}
}
