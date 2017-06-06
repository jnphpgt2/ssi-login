<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
window._bd_share_config={
		"common":{
			"bdSnsKey":{},
			"bdText":"",
			"bdMini":"2",
			"bdMiniList":false,
			"bdPic":"",
			"bdStyle":"0",
			"bdSize":"16"},
		"slide":{
			"type":"slide",
			"bdImg":"6",
			"bdPos":"right",
			"bdTop":"100"},
		"image":{
			"viewList":["qzone","tsina","tqq","renren","weixin"],
			"viewText":"分享到：",
			"viewSize":"16"},
		"selectShare":{
			"bdContainerClass":null,
			"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}
	};
	with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
	</script>
	
	
	<!-- 视频和弹幕的父容器 -->
	<div class="abp">
		<!-- 视频播放容器 -->
		<div class="video" id="CuPlayer">
			<b> <img src="<%=request.getContextPath() %>/js/player2/images/loading.gif" /> 网页视频播放器加载中，请稍后...</b>
		</div>
		<!-- 弹幕容器，必须设置class="container"
		这个容器必须包含在class="abp"的容器中 -->
		<div id='my-comment-stage' class="container" style="width: 80%; height: 20%">
	    </div>
	    <div>
	    	消息：<input id="topic_msg_text">
	    	<input type="button" value="发送弹幕" onclick="publish_movie_dmxx()">
	    </div>
	</div>
	


	<script type="text/javascript">
		$(function() {
			
			//声明频道
			window.topic = "topic_movie_${movie.movieID}";
			
			var so = new SWFObject("<%=request.getContextPath() %>/js/player2/player.swf?FlvID=${movie.movieID}","ply","520","325","9","#000000");
			so.addParam("allowfullscreen","true");
			so.addParam("allowscriptaccess","always");
			so.addParam("wmode","opaque");
			so.addParam("quality","high");
			so.addParam("salign","lt");
			so.addVariable("JcScpFile","<%=request.getContextPath() %>/moviePlay.jhtml");
			//so.addVariable("JcScpFile","<%=request.getContextPath() %>/js/player2/CuSunV2set.xml");
			//so.addVariable("JcScpVideoPath","http://localhost:8080/movies/gg_1.mp4"); 
			so.write("CuPlayer");
			
			//实例化弹幕控制器
			//注意：new CommentManager(JSDOM)
			//jquery转js：dom[0]
			//js转jquery：$(dom)
		    var CM = new CommentManager($("#my-comment-stage")[0]);
			//初始化弹幕控制器
		    CM.init();
		    // 启动播放弹幕（在未启动状态下弹幕不会移动）
		    CM.start();
		    // 开放 CM 对象到全局这样就可以在 console 终端里操控
		    window.CM = CM;
		    // 弹幕播放(播放的都是测试数据)
		    //cmtController();
		    
		    
		  	//实例化Yunba对象
			window.yunba = new Yunba({
				server: 'http://sock.yunba.io',
				port: 3000,
				appkey: "590ad9e3f1ae5ffe36712382" // 这里是您在 “第二步” 中获取到的 AppKey。
			});
			//初始化Yunba对象
			yunba.init(function(success) {
				if (success) {
					//自定义会话ID
				    var cid = Math.random().toString().substr(2);
				    // 连接云巴服务器(cid,回调函数)
				    yunba.connect_by_customid(cid, function(success, msg, sessionid) {
				        if (success) {
				        	// 设置收到信息回调函数
				        	yunba.set_message_cb(yunba_msg_cb);
				            // 订阅弹幕 TOPIC
				            yunba.subscribe({
				                'topic': topic
				            },
				            function(success, msg) {
				                if (success) {
				                  console.log('subscribed');
				                } else {
				                  console.log(msg);
				                }
				            });
				        } else {
				          console.log(msg);
				        }
				    });
				  } else {
				    console.log('yunba init failed');
				  }
			});
		});
	
		//收到订阅消息的回调函数
		function yunba_msg_cb(data) {
			console.log(data);
			CM.send(eval("(" + data.msg + ")"));
		}
		
		//发送弹幕消息
		function publish_movie_dmxx() {
			var topic_msg_text = $('#topic_msg_text').val();
			if (null != topic_msg_text && "" != topic_msg_text) {
				$('#topic_msg_text').val("");
		    	var json_message = {
		    		    "mode": 1,
		    		    "text": topic_msg_text,
		    		    "stime": 0,
		    		    "size": 25,
		    		    "color": 0xff9933,
		    		    "dur": 10000
		    	}
		    	//json对象转成json字符串
		    	var message = JSON.stringify(json_message);
		    	//此处发布的消息必须是字符串，不能是json对象
		     	yunba.publish({'topic': topic, 'msg': message}, function (success, msg) {
			        if (success) {
		
			        } else {
			          alert(msg);
			        }
		      	});
			}
	    }
		
		//断开云巴服务器连接
		function disconnect_yunba_server() {
			yunba.disconnect();
		}
		
		
		//===========以下都是测试弹幕功能用的数据以及方法===================
		function getCmtDataList() {
		    var cmtArr = [];

		    // 可以使用jsonp获取服务器的字幕数据
		    /*$.ajax({
		        type : 'GET',
		        url : 'http://192.168.9.67/test.php',
		        dataType : 'jsonp',
		        data : {sid : 100},
		        success : function(data) {
		            cmtArr = data.dataList;

		            if (cmtArr && cmtArr.length > 0) {
		                sendMsg(cmtArr);
		            }
		        }
		    });*/

		    // 测试数据
		    cmtArr = [
		        {"text":"大家期待什么新品啊", "bgColor":"#424448", "icon":"http://face.weiphone.net/data/avatar/000/15/31/95_avatar_big.jpg"},
		        {"text":"会有什么惊喜吗？", "bgColor":"#424448", "icon":"http://face.weiphone.net/data/avatar/000/15/31/95_avatar_big.jpg"},
		        {"text":"等待中。。", "bgColor":"#23b28b", "icon":"http://face.weiphone.net/data/avatar/000/15/31/95_avatar_big.jpg"},
		        {"text":"会有什么新产品呢？", "bgColor":"#424448", "icon":"http://face.weiphone.net/data/avatar/000/15/31/95_avatar_big.jpg"},
		        {"text":"定时执行", "bgColor":"#23b28b", "icon":"http://face.weiphone.net/data/avatar/000/15/31/95_avatar_big.jpg"},
		        {"text":"1123333446红咖喱的非农房价", "bgColor":"#ec4262", "icon":"http://face.weiphone.net/data/avatar/000/15/31/95_avatar_big.jpg"},
		        {"text":"测试接口发评论00", "bgColor":"#ec4262", "icon":"http://face.weiphone.net/data/avatar/000/15/31/95_avatar_big.jpg"},
		        {"text":"测试接口发评论00", "bgColor":"#3dbbc0", "icon":"http://face.weiphone.net/data/avatar/000/15/31/95_avatar_big.jpg"},
		        {"text":"啊啊啊啊啊啊啊哦哦哦诶IEIE恩家报表出具", "bgColor":"#ec4262", "icon":"http://face.weiphone.net/data/avatar/000/15/31/95_avatar_big.jpg"},
		        {"text":"的方式的方法反反复复反复反复", "bgColor":"#23b28b", "icon":"http://face.weiphone.net/data/avatar/000/15/31/95_avatar_big.jpg"}
		    ]

		    sendMsg(cmtArr);
		}

		function sendMsg(cmtArr) {

		    for (var i=0; i<cmtArr.length; i++) {
		        var cmtItem = cmtArr[i],
		            iconStr = '';

		        if (cmtItem.icon && cmtItem.icon.length > 0) {
		            iconStr = '<span class="icon"><img src="'+ cmtItem.icon +'"></span>';
		        }

		        // 字幕的节点内容
		        cmtItem.text = iconStr + cmtItem.text;
		        cmtItem.mode = 1;
		        cmtItem.dur = Math.floor(Math.random()*4000 + 4000);

		        CM.send(cmtItem);
		    }
		}

		function cmtController() {
		    getCmtDataList();

		    setTimeout(function(){
		        cmtController();
		    }, 5000);
		}
	
	</script>

</body>
</html>