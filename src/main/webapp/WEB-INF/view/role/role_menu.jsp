<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 真的是一个同行冲突，该怎么解决 -->
	<!-- 我也是醉了 -->
	<!-- 同行冲突，需要手动解决 -->
	<!-- 爱信不信 -->

	<!-- 这突如其来的心塞是怎么回事：教授 -->

	
	<!-- 为了注释而注释，也是闲的蛋疼：小郭 -->
	<!-- 放置一个数的容器：小郭 -->
	<!-- 小郭写的注释，哈哈：小郭 -->
	
	<!-- 一支穿云箭 -->
	
	<!-- 天王盖地虎，小鸡炖蘑菇 -->
	<!-- 教授写的注释 -->
	<div id="role_menu_tree"></div>
	
	<!-- 非同行冲突，不用手动解决 -->
	<!-- 两个黄鹂鸣翠柳：教授 -->
	
	
	
	<script type="text/javascript">
		//初始化树节点
		$(function() {
			$("#role_menu_tree").tree({
				url:"<%=request.getContextPath() %>/selectRoleMenuTree.jhtml?roleID=${role.roleID}",
				checkbox:true,
				//data:[{text:'11',checked:0}]
			});
		})
	</script>
	
	<!-- 一行白鹭上西天：小郭 -->
	<!-- 这个冲突不在同一行，不用刻意解决 -->
</body>
</html>