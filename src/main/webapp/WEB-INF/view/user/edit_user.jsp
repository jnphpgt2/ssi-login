<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="user_edit_form">
		<input type="hidden" id="edit_user_id" value="${user.userID }">
		用户账号：<input id="edit_user_account" readonly="readonly" value="${user.userAccount }" class="easyui-textbox" data-options="prompt:'请输入用户账号'"><br>
		用户姓名：<input id="edit_user_name" value="${user.userName }" class="easyui-textbox" data-options="prompt:'请输入用户姓名'"><br>
		用户性别：<input name="edit_user_sex" type="radio" value="1" <c:if test="${user.userSex == 1 }">checked</c:if>>男
		<input name="edit_user_sex" type="radio" value="2" <c:if test="${user.userSex == 2 }">checked</c:if>>女<br>
		用户年龄：<input id="edit_user_age" value="${user.userAge }" class="easyui-numberspinner" data-options="prompt:'请输入用户年龄'"><br>
		出生日期：<input id="edit_user_bir" value="${user.userBirStr }" class="easyui-datebox" data-options="prompt:'请输入用户出生日期'">
	</form>
	
</body>
</html>