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
    	//获取身份证
    	$.ajax({
			url:"getUserIDCard",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
			},
	      	success:function(data){
		      	if(data.code===1){
		      		$("#img_positive").attr("src",data.data.oneserverId1);
		      		$("#img_opposite").attr("src",data.data.oneserverId2);
		       	}else{
		       		
		       	}
		    
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
		$("#sub").on("click",function(){
			var type ;
			//获取上传后的id
			var oneserverId1 = $("#oneserverId1").html();
			var oneserverId2 = $("#oneserverId2").html();
			var url1;
			var url2;
			if(oneserverId1===""&&oneserverId2===""){
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftomine&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			}else{
				//当只改oneserverId1时
				if(oneserverId1!==""&&oneserverId2===""){
					url1 = oneserverId1;
					url2 = $("#img_opposite").attr("src");
					type = 1;
				}
				//当只改oneserverId2时
				if(oneserverId1===""&&oneserverId2!==""){
					url1 = $("#img_positive").attr("src");
					url2 = oneserverId2;
					type = 2;
				}
				//当全部修改时候
				if(oneserverId1!==""&&oneserverId2!==""){
					url1 = oneserverId1;
					url2 = oneserverId2;
					type = 3;
				}
				
				
				
				$.ajax({
					url:"updateUserIDCard",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	data: {"oneserverId1":url1,"oneserverId2":url2,"type":type},
			      	beforeSend: function () {
						//防止重复提交弹出loading窗口
						$("#loadingToast").show();
					},
			      	success:function(data){
			      		//返回成功关闭loading
				      	$("#loadingToast").hide();
				      	if(data.code===1){
				      		//自动弹出完成窗
				      		$("#toast").show();
				      		//自动关闭弹出完成窗
				      		setTimeout(function(){
				      			$("#toast").hide();
				      		},1000);
					        //跳回下一步界面
					        setTimeout(function(){
				      			window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftomine&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
				      		},1000);
				       	}else{
				       		alert("上传失败");
				       	}
				      
			      	},
			      	error:function(){
			      	   $("#loadingToast").hide();
			           alert("操作失败");
			      	}
		    	});
				
			}
			
			
		})
    });