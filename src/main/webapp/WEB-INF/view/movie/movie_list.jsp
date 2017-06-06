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
	<span id="movie_cz_btn_group" style="display: none">
		<div id="movie_play_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-large-clipart'" onclick="open_movie_play_dialog()">视频播放</div>
		<div id="movie_down_load_btn" class="easyui-linkbutton" data-options="plain:true" onclick="down_load_movie()">下载</div>
	</span>
	
	<!-- 数据表格 -->
	<div id="movie_dg" class="easyui-datagrid"></div>
	
	<!-- 设置工具条 -->
	<div id="movie_tb">
		<!-- 按钮组 -->
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="open_add_movie_dialog()">添加</div>
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="open_edit_user_dialog()">修改</div>
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="delete_selected_row()">删除</div>
		<div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="down_load_selected_movie()">下载视频</div>
	</div>

	<script type="text/javascript">
		//初始化数据表格
		$("#movie_dg").datagrid({
			url:"<%=request.getContextPath() %>/selectMovieJsonList.jhtml",
			pagination:true,
			pageSize:3,
			pageList:[3,5,8,10],
			//工具栏
		    toolbar:"#movie_tb",
		    //按Ctrl键多选
		    ctrlSelect:true,
			columns:[[    
			          {field:'movieID',title:'id',width:100},    
			          {field:'movieTitle',title:'电影名称',width:100},    
			          {field:'movieContent',title:'电影介绍',width:100},    
			          {field:'movieSize',title:'电影大小',width:100},    
			          {field:'movieCreateDate',title:'上映日期',width:100},    
			          {field:'movieTypeName',title:'电影类型',width:100},    
			          {field:'moviePrice',title:'电影价格',width:100},    
			          {field:'userName',title:'上传者姓名',width:100},    
			          {
			        	  field:'movieCZ',
			        	  title:'操作',
			        	  //width:100,
			        	  formatter:function(value, row, index){
			        		  $("#movie_play_btn").attr("onclick", "open_movie_play_dialog(" + row.movieID + ")");
			        		  $("#movie_down_load_btn").attr("onclick", "down_load_movie(" + row.movieID + ")");
			        		  return $("#movie_cz_btn_group").html();
			        	  }
			        	},
			      ]]
		});
	
		//打开添加视频对话框
		function open_add_movie_dialog() {
			var add_movie_dialog = $("<div style='padding:5px'></div>").dialog({
				title:"添加视频",
				autoHeight:true,
				width:360,
				modal:true,
				buttons:[
				         {
				        	 text:"保存",
				        	 handler:function() {
				        		var add_movie_json = add_movie_fun();
				        		//发送ajax请求，把接受到的json保存到数据库
				        		alert(add_movie_json.movieTitle);
				        		$.post(
				        				"<%=request.getContextPath() %>/insertMovie.jhtml",
				        				add_movie_json,
				        				function(data) {
				        					add_movie_dialog.dialog("destroy");
				        					$("#movie_dg").datagrid("load");
				        				},
				        				"json"
				        		);
				        	 }
				        }
				         ],
			});
			//加载子页面
			add_movie_dialog.window({
				href:"<%=request.getContextPath() %>/toAddMoviePage.jhtml",
			});
		}
		
		//打开视频播放对话框
		function open_movie_play_dialog(movie_id) {
			//打开对话框
			var movie_play_dialog = $("<div style='padding:5px'></div>").dialog({
				title:"视频播放",
				minHeight:280,
				width:620,
				modal:true,
				onClose:function() {
					movie_play_dialog.dialog("destroy");
				},
				onBeforeClose:function() {
					disconnect_yunba_server();
				}
			});
			//加载播放页面
			movie_play_dialog.window({
				href:"<%=request.getContextPath() %>/toMoviePlayPage.jhtml?movieID=" + movie_id,
			});
		}
		
		//下载电影
		function down_load_movie(movie_id) {
			location.href = "<%=request.getContextPath() %>/downLoadMovieByMovieID.jhtml?movieID=" + movie_id;
		}
		
		//批量打包下载视频
		function down_load_selected_movie() {
			var down_load_selected_movies = $("#movie_dg").datagrid("getSelections");
			var movieIDs = "";
			for (var i = 0; i < down_load_selected_movies.length; i++) {
				movieIDs = movieIDs + "," + down_load_selected_movies[i].movieID;
			}
			if (0 < movieIDs.length) {
				movieIDs = movieIDs.substring(1);
				//执行下载
				location.href = "<%=request.getContextPath() %>/downLoadMoreMovie.jhtml?movieIDs=" + movieIDs;
			}
		}
	</script>
</body>
</html>