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
    	var alldata;
    	 //初始化省市区选择
	    
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
    	//获取验证码
    	$("#send").on("click",getcode);
    	//计算
    	$(".price").on("input",result);
    	$(".period").on("change",result);
    	//点击保存
   		$("#sub").on("click",function(){
   			var code = $("#code").val(),
   			validate = $("#validate").val(),
   			price = $(".price").val(),
    		period = $(".period").find('option:selected').text().replace("期","");
    		sum = $(".package-result").html();
    		if(code===''){
    			alert("推荐码为空");
    			return false;
    		}
    		if(validate===''){
    			alert("验证码为空");
    			return false;
    		}
    		if(price===''){
    			alert("请输入价格空");
    			return false;
    		}
   			$.ajax({
				url:"saveBrushToSession",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"recomCode":code,"message":validate,"pacMonthlyPrice":Number(price),"pacPeriods":Number(period),"pacPrice":Number(sum)},
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
   		})
    	
    	
    });
    //获取验证码
    function getcode(){
		var code = $("#code").val();
		if(code===""){
			alert("请先输入业务员推荐码");
			return false;
		}
		
		$.ajax({
			url:"sendMessages",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {"recomCode":code},
	      	beforeSend: function () {
			},
	      	success:function(data){
		      	if(data.status==="SUCCESS"){
		      		time();
		      		
		       	}else{
		       		alert(data.msg);
		       	}
		    
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
		
	}
    //价格计算器
    function result(){
    	var price = $(".price").val(),
    	period = $(".period").find('option:selected').text().replace("期","");
    	var sum = price*period;
    	$(".package-result").html(sum);
    	
    }
var wait = 60;
//60s倒计时器点击重发
function time(){
	var o = document.getElementsByClassName("send");
	 if (wait == 0) {
 	  $("#send").on("click",getcode); 
 	  $("#send").html("");
	  $("#send").html("发送验证码");
	  //点击注册
	  $("#send").addClass("send");
	  $("#send").removeClass("sendother");
	  wait = 60;
	  } else {
	  	//设置button样式
	   $("#send").off("click",getcode);
	   $("#send").removeClass("send");
	   $("#send").addClass("sendother");
	   $("#send").html("");
	   $("#send").html("重新发送(" + wait + "s)");
	   wait--;
	   setTimeout(function() {
	   	time()
	   },
	   1000)
	}
}
    