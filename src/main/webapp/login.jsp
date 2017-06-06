<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆 - 金科集团</title>
</head>
<body>

	<!-- 登陆的模态窗口 -->
	<div class="easyui-dialog" data-options="title:'用户登陆',modal:true,width:320,height:220,closable:false,buttons:[{text:'登陆',handler:function() {user_login()}},{text:'注册'}]"
	style="padding: 16px">
		账号：<input id="login_user_account" class="easyui-textbox" data-options="prompt:'请输入账号',iconCls:'icon-man'"><br>
		密码：<input id="login_user_pwd" class="easyui-textbox" data-options="type:'password',prompt:'请输入账号',iconCls:'icon-lock'"><br>
		验证码：<input id="login_user_imgcode" class="easyui-textbox" data-options="prompt:'请输入验证码',iconCls:'icon-lock'"><br>
		<span onclick="change_img_code()">
			<img id="login_user_imgcode_img" src="<%=request.getContextPath() %>/imgcode">
			<font color="red">看不清，点击换一张</font>
		</span><br>
		<span id="ss_11"></span>
	</div>

	<script type="text/javascript">
		//登陆
		function user_login() {
			//获取账号密码
			var login_user_account = $("#login_user_account").val();
			var login_user_pwd = $("#login_user_pwd").val();
			var login_user_imgcode = $("#login_user_imgcode").val();
			var user_login_json = {
					userAccount:login_user_account,
					userPwd:login_user_pwd,
					userImgCode:login_user_imgcode
			}
			//ajax登陆
			$.post(
				"<%=request.getContextPath() %>/login.jhtml",
				user_login_json,
				function(data) {
					if (0 == data.flag) {
						//跳转主页面
						location.href = "<%=request.getContextPath() %>/toMainPage.jhtml"
					}
					if (1 == data.flag) {
						alert("用户不存在");
					}
					if (2 == data.flag) {
						alert("密码连续错误" + data.failed_count + "次，累计3次，账号将锁定，5分钟后自动解锁");
					}
					if (3 == data.flag) {
						open_time_out();
						alert("大表哥，您的账号已锁定，请五分钟之后再试");
					}
					if (4 == data.flag) {
						alert("大表哥，请输入验证码");
					}
					if (5 == data.flag) {
						alert("小婊砸，验证码错误，请重新输入");
					}
				},
				"json"
			);
		}
		
		//切换验证码
		function change_img_code() {
			$("#login_user_imgcode_img").attr("src", "<%=request.getContextPath() %>/imgcode?time=" + new Date().getTime());
		}
		
		var my_time_out = null;
		var i = 0;
		//5分钟倒计时
		function open_time_out() {
			my_time_out = setTimeout("open_time_out_fun()", 1000);
		}
		
		function open_time_out_fun() {
			$("#ss_11").html(i);
			if (30 == i) {
				$("#ss_11").html("");
				close_time_out();
				return;
			}
			i++;
			setTimeout("open_time_out_fun()", 1000);
		}
		
		//停止定时器
		function close_time_out() {
			i = 0;
			clearTimeout(my_time_out);
		}
	</script>
</body>
</html>