<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="easyui-layout">

	<!-- 布局 -->
	<div data-options="region:'north',height:80,split:true">
		<div class="easyui-linkbutton" onclick="user_logout()">退出登录</div>
	</div>
	<div data-options="region:'west',width:148,split:true">
		<!-- 手风琴 -->
		<div class="easyui-accordion" data-options="fit:true">
			<div title="手风琴1">
				<!-- 菜单树 -->
				<div id="main_tree"></div>
			</div>
			<div title="手风琴2"></div>
			<div title="手风琴3"></div>
		</div>
	</div>
	<div data-options="region:'center',split:true">
		<!-- 选项卡 -->
		<div id="main_tab" class="easyui-tabs" data-options="fit:true">
			<div title="主页面">
				<h2>真正的主页面，哈哈哈</h2>
				<form action="insertBook.jhtml" method="post">
				<input name="bookName">
				<input type="submit">
				</form>
			</div>
		</div>
		
		
		
	</div>
	
	<script type="text/javascript">
		//初始化菜单树
		$("#main_tree").tree({
			/* data:[
			      {
			      		id:1,
			      		text:'root',
			      		leaf:0,
			      		children:[
			      		          {
			      		        	  id:2,
			      		        	  text:'用户管理',
			      		        	  leaf:0,
			      		        	  children:[
			      		        	            {id:3,text:'用户列表',url:'/toUserListPage.jhtml',leaf:1},
			      		        	          	{id:4,text:'用户aa',url:'/toUserListPage.jhtml',leaf:1}
			      		        	            ]
			      		        	},
			      		        	{
				      		        	  id:5,
				      		        	  text:'角色管理',
				      		        	  leaf:0,
				      		        	  children:[
				      		        	            {id:6,text:'角色列表',url:'/toRoleListPage.jhtml',leaf:1},
				      		        	            ]
			      		        	},
			      		        	{
				      		        	  id:7,
				      		        	  text:'权限管理',
				      		        	  leaf:0,
				      		        	  children:[
				      		        	            {id:8,text:'权限列表',url:'/toMenuListPage.jhtml',leaf:1},
				      		        	            ]
				      		        	}
			      		]
			      }
			], */
			url:"<%=request.getContextPath() %>/selectMainTreeList.jhtml",
			onClick:function(node) {
				if (1 == node.leaf) {
					var my_tab = $("#main_tab").tabs("getTab",node.text);
					if (my_tab) {
						//激活已存在选项卡
						$("#main_tab").tabs("select", node.text);
						//刷新选项卡内容
						var my_selected_tab = $("#main_tab").tabs("getSelected");
						//my_selected_tab.panel("refresh", "<%=request.getContextPath() %>/" + node.url);
						my_selected_tab.panel("refresh", node.url);
					} else {
						//打开选项卡
						$("#main_tab").tabs("add",{
							title:node.text,
							closable:true,
							//href:"<%=request.getContextPath() %>/" + node.url
							href:node.url
						})
					}
				}
			}
		});
		
		//用户退出登录
		function user_logout() {
			//alert("屎可以乱吃，话不可以乱讲");
			location.href = "<%=request.getContextPath() %>/logout.jhtml";
		}
	
	</script>

</body>
</html>
