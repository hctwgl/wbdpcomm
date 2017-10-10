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
		//点击提交
   		$("#sub").on("click",function(){
   			var oneserverId1 = $("#oneserverId1").html();
   			if(oneserverId1===""){
   				alert("请先上传人脸照片");
   				return false;
   			}
   			$.ajax({
				url:"saveFace",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"oneserverId1":oneserverId1},
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
			      			window.location.href="toOrderSign";
			      		},100);
			       	}else{
			       		alert("上传照片与身份证不匹配，点击头像重新上传");
			       	}
			      	
		      	},
		      	error:function(){
		      	   $("#loadingToast").hide();
		           alert("操作失败");
		      	}
	    	});
   		});
    	
    });