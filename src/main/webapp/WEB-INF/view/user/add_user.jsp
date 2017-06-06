<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="user_add_form">
		用户账号：<input id="add_user_account" class="easyui-textbox" data-options="prompt:'请输入用户账号'"><br>
		用户密码：<input id="add_user_pwd" class="easyui-textbox" data-options="prompt:'请输入用户密码', type:'password'"><br>
		用户姓名：<input id="add_user_name" class="easyui-textbox" data-options="prompt:'请输入用户姓名'"><br>
		用户性别：<input name="add_user_sex" type="radio" value="1">男
		<input name="add_user_sex" type="radio" value="2">女<br>
		用户年龄：<input id="add_user_age" class="easyui-numberspinner" data-options="prompt:'请输入用户年龄'"><br>
		出生日期：<input id="add_user_bir" class="easyui-datebox" data-options="prompt:'请输入用户出生日期'">
	</form>
	
</body>
</html>