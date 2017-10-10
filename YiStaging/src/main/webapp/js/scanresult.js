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
		//获取我的额度
		$.ajax({
			url:"getBrushPollen",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
			},
	      	success:function(data){
		      	if(data.status==="SUCCESS"){
		      		$(".cash").html(data.data);
		      		
		       	}else{
		       		alert(data.msg);
		       	}
		    
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
    	//获取session的扫描结果
    	var result =JSON.parse(sessionStorage.getItem("result")) ;
    	//
    	
    	$(".name").html(result.name);
    	$(".tel").html(result.phone);
    	$(".code").html(result.recommend);
    	$(".package-money").html(result.pacMonthlyPrice);
    	$(".package-period").html(result.pacPeriods);
    	var pacPrice = result.pacMonthlyPrice*result.pacPeriods;
    	$(".package-result").html(pacPrice);
    	$("#sub").on("click",function(){
    			$.ajax({
					url:"saveBrushToSession",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	data: {"recomCode":result.recommend,"pacMonthlyPrice":result.pacMonthlyPrice,"pacPeriods":result.pacPeriods,"pacPrice":pacPrice},
			      	beforeSend: function () {
						//防止重复提交弹出loading窗口
						$("#loadingToast").show();
					},
			      	success:function(data){
			      		//返回成功关闭loading
				      	$("#loadingToast").hide();
				      	if(data.status==="SUCCESS"){
				      		//自动弹出完成窗
				      		$("#toast").show();
				      		//自动关闭弹出完成窗
				      		setTimeout(function(){
				      			$("#toast").hide();
				      		},1000);
					        //跳回我的界面
					        setTimeout(function(){
				      			window.location.href="toBrushSign";
				      			
				      		},1000);
				       	}else{
				       		alert(data.msg);
				       	}
				      
			      	},
			      	error:function(){
			      	   $("#loadingToast").hide();
			           alert("操作失败");
			      	}
		    	});
    	});
    	
    });
    
    