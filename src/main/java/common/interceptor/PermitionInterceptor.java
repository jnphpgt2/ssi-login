/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:PermitionInterceptor.java 
 * 包名:common.interceptor 
 * 创建日期:2017年4月24日下午3:00:36 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：PermitionInterceptor    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年4月24日 下午3:00:36    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年4月24日 下午3:00:36    
 * 修改备注：       
 * @version </pre>    
 */
public class PermitionInterceptor extends HandlerInterceptorAdapter {

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)    
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = false;
		//获取用户的请求路径
		String requestURI = request.getRequestURI();
		System.out.println("本次请求的路径：" + requestURI);
		//获取session中保存的用户树列表
		HttpSession session = request.getSession();
		List<String> userTree = (List<String>) session.getAttribute("userTree");
		if (null != userTree && 0 < userTree.size()) {
			System.out.println(userTree.get(0));
			for (String string : userTree) {
				if (null != string && requestURI.contains(string)) {
					flag = true;
					break;
				}
			}
		}
		//请求进入控制层之前执行
		System.out.println("pre:请求进入控制层之前执行");
		if (false == flag) {
			//页面重定向到提示页面
			response.sendRedirect("error.jsp");
		}
		return flag;
	}
	
	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)    
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//请求已经进入控制层了，但是还没有向页面返回结果（还没跳转页面）
		System.out.println("post:请求进入控制层之后，跳转页面之前执行");
	}
	
	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)    
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//控制层向页面返回结果之后，也就是跳转完页面之后执行
		System.out.println("after:请求跳转页面结束");
	}
}
