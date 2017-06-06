<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="role_edit_form">
		<input type="hidden" id="edit_role_id" value="${role.roleID }"><br>
		角色名称：<input id="edit_role_name" value="${role.roleName }" class="easyui-textbox" data-options="prompt:'请输入角色名称'"><br>
		角色描述：<input id="edit_role_desc" value="${role.roleDesc }" class="easyui-textbox" data-options="multiline:true,prompt:'请输入用户姓名',height:60"><br>
	</form>
	
</body>
</html>