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
   		//点击获取验证码
   		$("#getcode").on("click",getcode);
   		//点击注册
   		$("#sub").on("click",checkcode);
   		});
   //获取验证码
   function getcode(){
		var telreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		var tel = $("#tel").val();
		if(!telreg.test(tel)){
			alert("手机号码格式不正确");
			return false;
		}
		$.ajax({
			url:"sentcode",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {"phone":tel},
	      	success:function(data){
	      	if(data.code===1){
			        time();
		       	}else{
		       		alert("获取失败");
		       	}
	      	},
	      	error:function(){
	           alert("获取数据失败");
	      	}
	    });
   		
   }
   //检验验证码
   function checkcode(){
		var code = $("#code").val();
		if(code===""){
			alert("请输入验证码");
			return false;
		}
		$.ajax({
			url:"checkcode",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {"code":code},
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
		        //跳回申请界面
		        setTimeout(function(){
	      			window.location.href="toUserBaseInfo";
	      		},1000);
	       	}
	      	else{
	       		alert("验证码错误");
	       	}
	      	
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
   		
   }
var wait = 60;
//60s倒计时器点击重发
function time(){
	var o = document.getElementById("getcode");
	 if (wait == 0) {
 	  $("#getcode").on("click",getcode);
	  o.removeAttribute("disabled");   
	  $("#getcode").html("获取验证码");
	  //点击注册
   	  $("#sub").on("click",checkcode);
	   wait = 60;
	  } else {
	  	//设置button样式
	   $("#getcode").off("click");
	   $("#getcode").html("重新发送(" + wait + "s)");
	   wait--;
	   setTimeout(function() {
	   	time()
	   },
	   1000)
	}
}
