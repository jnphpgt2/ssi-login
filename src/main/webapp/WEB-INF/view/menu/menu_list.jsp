<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 设置工具条 -->
	<div id="menu_tb">
		<!-- 按钮组 -->
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="open_add_rootmenu_dialog()">添加</div>
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="open_edit_user_dialog()">修改</div>
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="delete_selected_row()">删除</div>
	</div>
	
	<!-- 权限树 -->
	<div id="menu_list_tree"></div>
	
	<!-- 定义菜单列表：右键树节点的时候展示 -->
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div data-options="iconCls:'icon-add'">Append</div>
		<div data-options="iconCls:'icon-remove'">Remove</div>
	</div>
	
	<script type="text/javascript">
		//初始化菜单树
		$("#menu_list_tree").tree({
			url:"<%=request.getContextPath() %>/selectMenuListJson.jhtml",
			//data:[{id:1,text:'root',state:'closed'}],
			onClick:function(node) {
				//alert(node.text + node.url);
			},
			onContextMenu: function(e, node){
				//右击事件
				e.preventDefault();
				$('#menu_list_tree').tree('select', node.target);
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}
		});
		
		//打开添加根节点对话框
		function open_add_rootmenu_dialog() {
			//判断页面有没有根节点
			var root_node = $("#menu_list_tree").tree("getSelected")
			if (root_node) {
				var menu_node_pid = root_node.pid;
				if (0 == menu_node_pid) {
					
					//拼接表单
					var module_add = "<input id='menu_pid' type='hidden' value='" + root_node.id + "'>"
						+ "权限名称：<input id='menu_name' class='easyui-textbox' data-options='prompt:\"请输入模块名称\"'><br>";
					
					//为root添加模块
					var module_add_dialog = $("<div style='padding:5px'></div>").dialog({
						title:"添加模块",
						height:120,
						width:300,
						modal:true,
						content:module_add,
						buttons:[
						         {
						        	 text:'保存',
						        	 handler:function() {
						        		 var menu_pid = $("#menu_pid").val();
						        		 var menu_name = $("#menu_name").textbox("getValue");
						        		 var menu_add_json = {
						        				 menuID:0,
						        				 menuPid:menu_pid,
						        				 menuName:menu_name
						        		 };
						        		 //ajax
										 $.ajax({ 
								            type:"post",
								            url:"<%=request.getContextPath() %>/insertMenu.jhtml", 
								            dataType:"json",
								            //这一行必须的，不写回报415这个错误
								            contentType:"application/json;charset=utf-8",             
								            data:JSON.stringify(menu_add_json),
								            success:function(data){
								            	//为父节点追加子节点
								            	$('#menu_list_tree').tree('append', {
													parent: root_node.target,
													data:data
												});

								            	module_add_dialog.dialog("destroy");
								            } 
								         });
						        	 }
						         },
						         {text:'取消',
						        	 handler:function() {
						        		 module_add_dialog.dialog("destroy");
						        	 }
						         }
						]
					});
				} else if (1 == menu_node_pid) {
					//为模块添加添加权限
					//拼接表单
					var module_add = "<input id='menu_pid' type='hidden' value='" + root_node.id + "'>"
						+ "权限名称：<input id='menu_name' class='easyui-textbox' data-options='prompt:\"请输入权限名称\"'><br>"
						+ "权限路径：<input id='menu_url' class='easyui-textbox' data-options='prompt:\"请输入权限路径\"'><br>"
						+ "按钮：<input name='add_menu_button' type='radio' value='0' checked>否<input name='add_menu_button' type='radio' value='1'>是<br>";
					
					//为root添加模块
					var module_add_dialog = $("<div style='padding:5px'></div>").dialog({
						title:"添加权限",
						//height:120,
						autoHeight:true,
						width:300,
						modal:true,
						content:module_add,
						buttons:[
						         {
						        	 text:'保存',
						        	 handler:function() {
						        		 var menu_pid = $("#menu_pid").val();
						        		 var menu_name = $("#menu_name").textbox("getValue");
						        		 var menu_url = $("#menu_url").textbox("getValue");
						        		 var menu_button = 0;
						        		 var menu_button_nodes = $("[name='add_menu_button']");
						        		 for (var i = 0; i < menu_button_nodes.length; i++) {
						        			 if (menu_button_nodes[i].checked) {
						        				 menu_button = menu_button_nodes[i].value;
						        			 }
						        		 }
						        		 var menu_add_json = {
						        				 menuID:0,
						        				 menuPid:menu_pid,
						        				 menuName:menu_name,
						        				 menuUrl:menu_url,
						        				 menuLeaf:1,
						        				 menuButton:menu_button,
						        		 };
						        		 //ajax
										 $.ajax({ 
								            type:"post",
								            url:"<%=request.getContextPath() %>/insertMenu.jhtml", 
								            dataType:"json",
								            //这一行必须的，不写回报415这个错误
								            contentType:"application/json;charset=utf-8",             
								            data:JSON.stringify(menu_add_json),
								            success:function(data){
								            	//为父节点追加子节点
								            	$('#menu_list_tree').tree('append', {
													parent: root_node.target,
													data:data
												});

								            	module_add_dialog.dialog("destroy");
								            } 
								         });
						        	 }
						         },
						         {text:'取消',
						        	 handler:function() {
						        		 module_add_dialog.dialog("destroy");
						        	 }
						         }
						]
					});
				}
				
			} else {
				$.messager.alert("提示", "请选择父节点");
			}
		}
	
	</script>
</body>
</html>