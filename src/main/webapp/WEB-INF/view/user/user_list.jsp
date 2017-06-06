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
	<span id="user_cz_btn_group" style="display: none">
		<div id="user_cz_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-large-clipart'" onclick="open_user_role_dialog()">角色操作</div>
	</span>

	<!-- 数据表格 -->
	<div id="user_dg" class="easyui-datagrid"></div>
	
	<!-- 设置工具条 -->
	<div id="user_tb">
		<!-- 按钮组 -->
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="open_add_user_dialog()">添加</div>
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="open_edit_user_dialog()">修改</div>
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="delete_selected_row()">删除</div>
	</div>
	
	<script type="text/javascript">
		//初始化数据表格
		$("#user_dg").datagrid({
			url:"<%=request.getContextPath() %>/selectUserJsonList.jhtml",
			pagination:true,
			pageSize:3,
			pageList:[3,5,8,10],
			//工具栏
		    toolbar:"#user_tb",
		    //按Ctrl键多选
		    ctrlSelect:true,
			columns:[[    
			          {field:'userID',title:'id',width:100},    
			          {field:'userAccount',title:'账号',width:100},    
			          {field:'userName',title:'姓名',width:100},    
			          {
			        	  field:'userSex',
			        	  title:'性别',
			        	  width:100,
			        	  formatter:function(value,row,index) {
			        		  if (1 == value) {
			        			  return "男";
			        		  } else {
			        			  return "女";
			        		  }
			        	  }
			          },   
			          {field:'userAge',title:'年龄',width:100},   
			          {field:'userBirStr',title:'生日',width:100},
			          {
			        	  field:'userCZ',
			        	  title:'操作',
			        	  width:100,
			        	  formatter:function(value, row, index){
			        		  $("#user_cz_btn").attr("onclick", "open_user_role_dialog(" + row.userID + ")");
			        		  return $("#user_cz_btn_group").html();
			        	  }
			        	},
			      ]]
		})
	
		//打开添加对话框
		function open_add_user_dialog() {
			$("#user_add_form").form("clear");
			var add_user_dialog = $("<div id='add_user_div' style='padding:5px'></div>").dialog({
				title:"添加用户",
				height:220,
				width:300,
				modal:true,
				buttons:[
				         {
				        	 text:"保存",
				        	 handler:function() {
				        		 //你大爷的，springmvc接收前台值得时候只能是基本数据类型和字符串，日期不能接收
				        		 //你大爷的，为了避免dialog组件混乱，使用完的dialog必须销毁（destroy），而不是简单地close关闭
				        		 
				        		 //获取表单中的值，并且拼成json对象
				        		 var add_user_account = $("#add_user_account").textbox("getValue");
				        		 var add_user_pwd = $("#add_user_pwd").textbox("getValue");
				        		 var add_user_name = $("#add_user_name").textbox("getValue");
				        		 var add_user_sex = 0;
				        		 var add_user_sexs = $("[name='add_user_sex']");
				        		 for (var i = 0; i < add_user_sexs.length; i++) {
				        			 if (add_user_sexs[i].checked) {
				        				 add_user_sex = add_user_sexs[i].value;
				        			 }
				        		 }
				        		 var add_user_age = $("#add_user_age").numberspinner("getValue");
				        		 var add_user_bir = $("#add_user_bir").datebox("getValue");
				        		 
				        		 var add_user_json = {
				        				 userAccount:add_user_account,
				        				 userPwd:add_user_pwd,
				        				 userName:add_user_name,
				        				 userSex:add_user_sex,
				        				 userAge:add_user_age,
				        				 userBirStr:add_user_bir,
				        		 }
				        		 //ajax获取到表单并提交
				        		 $.post(
				        				 "<%=request.getContextPath() %>/insertUser.jhtml",
				        				 add_user_json,
				        				 function(data) {
				        					 //关闭对话框
							        		 add_user_dialog.dialog("destroy");
				        					 //刷新数据表格
				        					 $("#user_dg").datagrid("load");
				        				 },
				        				 "json"
				        		);
				        	 }},
				         {
				        	text:"取消",
				        	handler:function(node) {
				        		//关闭对话框
				        		add_user_dialog.dialog("destroy");
				        	}
				        }
				]
			});
			add_user_dialog.window({
				href:"<%=request.getContextPath() %>/toAddUserPage.jhtml"
			});
		}
		
		//打开修改对话框
		function open_edit_user_dialog() {
			//获取选中的数据
			var edit_user_data = $("#user_dg").datagrid("getSelected");
			if (edit_user_data) {
				var edit_user_dialog = $("<div id='edit_user_div' style='padding:5px'></div>").dialog({
					title:"修改用户信息",
					height:220,
					width:300,
					modal:true,
					buttons:[
					         {
				        	 text:"保存",
				        	 handler:function() {
				        		 //获取表单中的值，并且拼成json对象
				        		 var edit_user_id = $("#edit_user_id").val();
				        		 var edit_user_account = $("#edit_user_account").textbox("getValue");
				        		 var edit_user_name = $("#edit_user_name").textbox("getValue");
				        		 var edit_user_sex = 0;
				        		 var edit_user_sexs = $("[name='edit_user_sex']");
				        		 for (var i = 0; i < edit_user_sexs.length; i++) {
				        			 if (edit_user_sexs[i].checked) {
				        				 edit_user_sex = edit_user_sexs[i].value;
				        			 }
				        		 }
				        		 var edit_user_age = $("#edit_user_age").numberspinner("getValue");
				        		 var edit_user_bir = $("#edit_user_bir").datebox("getValue");
				        		 
				        		 var edit_user_json = {
				        				 userID:edit_user_id,
				        				 userAccount:edit_user_account,
				        				 userName:edit_user_name,
				        				 userSex:edit_user_sex,
				        				 userAge:edit_user_age,
				        				 userBirStr:edit_user_bir,
				        		 }
				        		 //ajax获取到表单并提交
				        		 $.post(
				        				 "<%=request.getContextPath() %>/updateUserByUserID.jhtml",
				        				 edit_user_json,
				        				 function(data) {
				        					 //关闭对话框
							        		 edit_user_dialog.dialog("destroy");
				        					 //刷新数据表格
				        					 $("#user_dg").datagrid("reload");
				        				 },
				        				 "json"
				        		);
				        	 }},
				         {
				        	text:"取消",
				        	handler:function() {
				        		//关闭对话框
				        		edit_user_dialog.dialog("destroy");
				        	}
				        }
					],
				});
				edit_user_dialog.window({
					href:"<%=request.getContextPath() %>/toEditUserPage.jhtml?userID=" + edit_user_data.userID
				});
			} else {
				$.messager.alert("提示","请至少选择一条");
			}
		}
		
		
		//设置全局变量，记录用户所操作的用户ID
		var global_user_id = 0;
		
		//打开用户角色编辑对话框
		function open_user_role_dialog(user_id) {
			var user_role_dialog = $("<div style='padding:5px'></div>").dialog({
				title:"用户>>角色",
				height:200,
				width:300,
				modal:true,
				buttons:[
							{
								 text:"保存",
								 handler:function() {
									 //获取到所有已经勾选上的角色
									 var user_role_check_nodes = $("#user_role_tree").tree("getChecked");
									 if (0 < user_role_check_nodes.length) {
										 var user_role_arr = [];
										 for (var i = 0; i < user_role_check_nodes.length; i++) {
											 var user_role_obj = {userID:user_id,roleID:user_role_check_nodes[i].id};
											 user_role_arr.push(user_role_obj);
										 }
										 //ajax
										 $.ajax({ 
								            type:"post", 
								            url:"<%=request.getContextPath() %>/updateUserRole.jhtml", 
								            dataType:"json", 
								            //这一行必须的，不写回报415这个错误
								            contentType:"application/json;charset=utf-8",               
								            data:JSON.stringify(user_role_arr),
								            success:function(data){
								            	user_role_dialog.dialog("destroy");
								            } 
								         });
									 } else {
										 $.messager.alert("提示", "请至少勾选一项");
									 }
								}
							 },
							 {
								 text:"取消",
								 handler:function() {
									 user_role_dialog.dialog("destroy");
								}
							 }
				]
			});
			user_role_dialog.window({
				href:"<%=request.getContextPath() %>/toUserRolePage.jhtml?userID=" + user_id
			});
		}
	</script>
</body>
</html>