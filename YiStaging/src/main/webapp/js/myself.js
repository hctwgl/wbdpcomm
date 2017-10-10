	//不同分辨率下设置不同的fontsize值
    var _self = this;
    var deviceWidth = (window.innerWidth > 0) ? window.innerWidth : screen.width; 
    console.log(deviceWidth); //hack某些浏览器获取手机屏幕宽度
    _self.width = 750;//设计稿宽度
    _self.fontSize = 100;//以100字体为参照物
    _self.actualWidth = function() {
            return deviceWidth;
    };
    document.getElementsByTagName("html")[0].setAttribute("style", "font-size:" + (_self.actualWidth() * _self.fontSize) / _self.width + "px !important");
    $(function(){
    	$.ajax({
			url:"isMRegist",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
				
			},
	      	success:function(data){
	      		code = data.code;
		      	if(data.data==="已注册"){
		      		//
		      		//获取当前额度
					$.ajax({
						url:"getPollen",
				      	type:"get",
				      	crossDomain:true,
				      	async:true,
				      	dataType:"json",
				      	data: {},
				      	beforeSend: function () {
						},
				      	success:function(data){
					      	if(data.code===1){
						      		var quota =  parseInt(data.data);
						      		if(!isNaN(quota)){
									       $("#quota").html(data.data);
									    } else{
									        $("#quota").html("0");
								    }
						      		
						       }
				      	},
				      	error:function(){
				      	  
				           alert("操作失败");
				      	}
			    	});
		      		$("#btn").html('获取信用额度');
		      		$("#btn").on("click",function(){
		      			//重新获取花粉信用
		       			$.ajax({
							url:"getPollen",
					      	type:"get",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data: {},
					      	beforeSend: function () {
							},
					      	success:function(data){
						      	if(data.code===1){
						      		var quota =  parseInt(data.data);
						      		if(!isNaN(quota)){
									       $("#quota").html(data.data);
									    } else{
									        $("#quota").html("0");
								    }
						      		
						       }
					      	},
					      	error:function(){
					      	  
					           alert("操作失败");
					      	}
				    	});
		       		});
		      		
		       	}else{
		       		$("#btn").html('立即申请');
		       		$("#btn").on("click",function(){
		       			window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		       		});
		       	}
		       	isRegister(data.data)
		      
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
		
		
		
		
    	
    	
    });
    function isRegister(code){
    	//导航条跳转
		$("#list").on("click",function(){
			if(code==="已注册"){
				window.location.href = "toMOrderList";
			}else{
				alert("请先注册");
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			}
			
		});
		$("#record").on("click",function(){
			if(code==="已注册"){
				window.location.href = "torecord";
			}else{
				alert("请先注册");
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			}
			
		});
    	$("#base").on("click",function(){
			if(code==="已注册"){
				window.location.href = "toUserInfs";
			}else{
				alert("请先注册");
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			}
		});
		$("#caller").on("click",function(){
			if(code==="已注册"){
				window.location.href = "toMyCaller";
			}else{
				alert("请先注册");
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			}
		});
		$("#card").on("click",function(){
			if(code==="已注册"){
				window.location.href = "toMyCard";
			}else{
				alert("请先注册");
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			}
		});
		$("#social").on("click",function(){
			if(code==="已注册"){
				window.location.href = "toSSA";
			}else{
				alert("请先注册");
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			}
		});
		$("#bank").on("click",function(){
			if(code==="已注册"){
				window.location.href = "toMyBank";
			}else{
				alert("请先注册");
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			}
		});
		$("#code").on("click",function(){
			if(code==="已注册"){
				window.location.href = "toORCode";
			}else{
				alert("请先注册");
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			}
		});
    	
    }
