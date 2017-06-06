<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${pg.pageIndex <= 1 }">
	<input type="button" value="首页" disabled="disabled">
	上一页
</c:if>
<c:if test="${pg.pageIndex > 1 }">
	<input type="button" value="首页" onclick="turnPages(1)">
	<a href="javascript:turnPages(${pg.pageIndex - 1 })">上一页</a>
</c:if>


<c:if test="${pg.pageIndex >= pg.totalPage }">
	下一页
	<input type="button" value="尾页" disabled="disabled">
</c:if>
<c:if test="${pg.pageIndex < pg.totalPage }">
	<a href="javascript:turnPages(${pg.pageIndex + 1 })">下一页</a>
	<input type="button" value="尾页" onclick="turnPages(${pg.totalPage })">
</c:if>
共${pg.totalPage }页
当前第${pg.pageIndex }页
共${pg.totalCount }条
每页${pg.pageSize }条
    
    