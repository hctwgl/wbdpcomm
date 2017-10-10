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
		$("#sub").on("click",function(){
			//获取上传后的id
			var oneserverId1 = $("#oneserverId1").html();
			var oneserverId2 = $("#oneserverId2").html();
			var oneserverId3 = $("#oneserverId3").html();
			if(oneserverId1===""){
				alert("请上传身份证正面");
				return false;
			}
			if(oneserverId2===""){
				alert("请上传身份证反面");
				return false;
			}
			if(oneserverId3===""){
				alert("请上传手持身份证");
				return false;
			}
			//新增图片
			$.ajax({
				url:"saveImg",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"oneserverId1":oneserverId1,"oneserverId2":oneserverId2,"oneserverId3":oneserverId3,"type":5},
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
			      		 //跳回下一步界面
				        setTimeout(function(){
			      			window.location.href="tosuccess";
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
    	
    	
    });
});