<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 设置一个隐藏的button，用于用户赋角色 
	你大爷的，因为html页面从上往下加载，相同id的标签，默认只选第一个
	所以这个按钮必须放在页面最顶端
	-->
	<span id="role_cz_btn_group" style="display: none">
		<div id="role_cz_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-large-clipart'" onclick="open_role_menu_dialog()">权限操作</div>
	</span>

	<!-- 数据表格 -->
	<div id="role_dg" class="easyui-datagrid"></div>
	
	<!-- 设置工具条 -->
	<div id="role_tb">
		<!-- 按钮组 -->
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="open_add_role_dialog()">添加</div>
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="open_edit_role_dialog()">修改</div>
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="delete_role()">删除</div>
	</div>
	
	<script type="text/javascript">
		//初始化数据表格
		$("#role_dg").datagrid({
			url:"<%=request.getContextPath() %>/selectRoleJsonList.jhtml",
			pagination:true,
			pageSize:3,
			pageList:[3,5,8,10],
			//工具栏
		    toolbar:"#role_tb",
		  	//按Ctrl键多选
		    ctrlSelect:true,
			columns:[[    
			          {field:'roleID',title:'id',width:100},    
			          {field:'roleName',title:'角色名称',width:100},    
			          {field:'roleDesc',title:'角色描述',width:100},   
			          {
			        	  field:'roleCZ',
			        	  title:'操作',
			        	  width:100,
			        	  formatter:function(value, row, index) {
			        		  $("#role_cz_btn").attr("onclick", "open_role_menu_dialog(" + row.roleID + ")")
			        		  return $("#role_cz_btn_group").html();
			        	  }}, 
			      ]]
		});
	
		//打开添加角色对话框
		function open_add_role_dialog() {
			var add_role_dialog = $("<div style='padding:5px'></div>").dialog({
				title:"添加角色",
				height:200,
				width:280,
				modal:true,
				buttons:[
						{
							 text:"保存",
							 handler:function() {
								 //获取表单中的值并拼接为json
								 var add_role_name = $("#add_role_name").textbox("getValue");
								 var add_role_desc = $("#add_role_desc").textbox("getValue");
								 var add_role_json = {
										 roleName:add_role_name,
										 roleDesc:add_role_desc
								 };
								 //ajax请求
								 $.post(
										 "<%=request.getContextPath() %>/insertRole.jhtml",
										 add_role_json,
										 function(data) {
											 //关闭对话框
											 add_role_dialog.dialog("destroy");
											 //刷新数据表格
											 $("#role_dg").datagrid("load");
										 },
										 "json"
								 );
								 
							 }},
						{
							text:"取消",
							handler:function(node) {
								//关闭对话框
								add_role_dialog.dialog("destroy");
							}
						}
				]
			});
			add_role_dialog.window({
				href:"<%=request.getContextPath() %>/toAddRolePage.jhtml"
			});
		}
		
		//打开修改角色信息对话框
		function open_edit_role_dialog() {
			//获取选中的数据
			var edit_role_data = $("#role_dg").datagrid("getSelected");
			if (edit_role_data) {
				var add_role_dialog = $("<div style='padding:5px'></div>").dialog({
					title:"修改角色",
					height:200,
					width:280,
					modal:true,
					buttons:[
							{
								 text:"保存",
								 handler:function() {
									 //获取表单中的值并拼接为json
									 var edit_role_id = $("#edit_role_id").val();
									 var edit_role_name = $("#edit_role_name").textbox("getValue");
									 var edit_role_desc = $("#edit_role_desc").textbox("getValue");
									 var edit_role_json = {
											 roleID:edit_role_id,
											 roleName:edit_role_name,
											 roleDesc:edit_role_desc
									 };
									 //ajax请求
									 $.post(
											 "<%=request.getContextPath() %>/updateRoleByRoleID.jhtml",
											 edit_role_json,
											 function(data) {
												 //关闭对话框
												 add_role_dialog.dialog("destroy");
												 //刷新数据表格
												 $("#role_dg").datagrid("reload");
											 },
											 "json"
									 );
									 
								 }},
							{
								text:"取消",
								handler:function(node) {
									//关闭对话框
									add_role_dialog.dialog("destroy");
								}
							}
					]
				});
				add_role_dialog.window({
					href:"<%=request.getContextPath() %>/selectRoleInfoByRoleID.jhtml?roleID=" + edit_role_data.roleID
				});
			} else {
				$.messager.alert("提示","请至少选择一条");
			}
		}
		
		//角色操作权限对话框
		function open_role_menu_dialog(role_id) {
			var role_menu_dialog = $("<div style='padding:5px'></div>").dialog({
				title:"角色>>权限",
				//height:600,
				minHeight:200,
				width:300,
				modal:true,
				buttons:[
							{
								 text:"保存",
								 handler:function() {
									 role_menu_dialog.dialog("destroy");
								}
							 },
							 {
								 text:"取消",
								 handler:function() {
									 role_menu_dialog.dialog("destroy");
								}
							 }
				]
			});
			//在对话框中打开第二个页面
			role_menu_dialog.window({
				href:"<%=request.getContextPath() %>/toRoleMenuPage.jhtml?roleID=" + role_id
			});
		}
		
		//删除角色
		function delete_role() {
			//获取选中的数据
			var del_select_role = $("#role_dg").datagrid("getSelected");
			if (del_select_role) {
				//执行删除
				$.messager.confirm("确认", "小婊砸，你真的要删除？", function(f) {
					if(f) {
						//发送ajax请求，删除数据
						$.get(
							"<%=request.getContextPath() %>/deleteRoleByRoleID.jhtml",
							{roleID:del_select_role.roleID},
							function(data) {
								//刷新数据表格
								$("#role_dg").datagrid("reload");
							},
							"json"
						);
					}
				});
			} else {
				//提示选择数据
				$.messager.alert("提示", "大表哥，请选择操作数据");
			}
		}
	</script>
</body>
</html>