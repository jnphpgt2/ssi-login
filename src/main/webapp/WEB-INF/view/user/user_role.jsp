<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="user_role_tree"></div>
	
	<script type="text/javascript">
		$(function() {
			$("#user_role_tree").tree({
				url:"<%=request.getContextPath() %>/selectUserRoleTree.jhtml?userID=${user.userID}",
				checkbox:true,
				//data:[{text:'11',checked:0}]
			});
		})
	</script>
</body>
</html>