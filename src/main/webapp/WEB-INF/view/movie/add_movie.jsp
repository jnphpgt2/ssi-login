<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	视频名称：<input id="movie_title" class="easyui-textbox" data-options="prompt:'请输入视频名称'"><br>
	视频简介：<input id="movie_content" class="easyui-textbox" data-options="prompt:'请输入视频简介',multiline:true,height:36"><br>
	视频类型：<input id="movie_type" value="0"><br>
	视频价格：<input id="movie_price" class="easyui-numberspinner" data-options="prompt:'请输入视频价格'"><br>
	选择视频：<input id="movie_path" type="file"><br>
	
	<script type="text/javascript">
		//初始化类型下拉框
		$('#movie_type').combobox({    
		    url:'<%=request.getContextPath() %>/selectMovieTypeListJson.jhtml',    
		    /* data:[{    
		        "id":0,    
		        "text":"请选择"   
		    },{    
		        "id":1,    
		        "text":"古装"   
		    },{    
		        "id":2,    
		        "text":"现代",    
		        "selected":true   
		    }], */
		    valueField:'movieTypeID',    
		    textField:'movieTypeName'   
		}); 
		
		//声明全局json变量
		var add_movie_json = new Object();
		
		//初始化uploadify组件
		$(function() {
		    $("#movie_path").uploadify({
		        height:30,
		        swf:'<%=request.getContextPath() %>/js/uploadify/uploadify.swf',
		        uploader:'<%=request.getContextPath() %>/uploadMovieByAsync.jhtml',
		        width:120,
		        buttonText:"选择视频",
		        //后台中同样属性名的file文件接受这个文件
		        fileObjName:"movieFile",
		        //设置超时时间
		        successTimeout:120,
		        //成功回调函数
		        onUploadSuccess:function(file, data, response) {
		        	//data就是返回的json字符串数据
		        	data = eval("(" + data + ")");
		        	add_movie_json = data;
		        }
		    });
		});
		
		/* function dfgh(data) {
			alert(444);
			add_movie_json = data;
		} */
		
		function add_movie_fun() {
			//获取文本框的值添加到json对象中
			var movie_title = $("#movie_title").textbox("getValue");
			var movie_content = $("#movie_content").textbox("getValue");
			var movie_type = $("#movie_type").combo("getValue");
			var movie_price = $("#movie_price").numberspinner("getValue");
			var user_id = "${userInfo.userID}";
			
			add_movie_json.movieTitle = movie_title;
			add_movie_json.movieContent = movie_content;
			add_movie_json["movieTypeID"] = movie_type;
			add_movie_json["moviePrice"] = movie_price;
			add_movie_json["userID"] = user_id;
			//console.log(add_movie_json);
			return add_movie_json;
		}
	</script>
</body>
</html>