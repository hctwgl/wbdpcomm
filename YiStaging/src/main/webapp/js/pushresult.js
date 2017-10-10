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
    	var quota;//信用额度
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
		      		quota = data.data;
		       	}else{
		       		alert(data.msg);
		       	}
		    
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
    	$.ajax({
			url:"getBrushPush",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
			},
	      	success:function(resultdata){
		      	if(resultdata.status==="SUCCESS"){
		      		var result = resultdata.data;
		      		$(".code").html(result.recomCode);
			    	$(".package-money").html(result.pacMonthlyPrice);
			    	$(".package-period").html(result.pacPeriods);
			    	$(".package-result").html(result.pacPrice);
			    	$(".type").html(result.phoneModel);
		       	}else{
		       		alert(resultdata.msg);
		       	}
		    
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
    	//获取session的扫描结果
    	
    	$("#sub").on("click",function(){
    		var pacPrice = $(".package-result").html();
    		if(Number(quota)<Number(pacPrice)){
    			alert("额度小于当前套餐");
    			return false;
    		}
			window.location.href="toBrushSign";
    	});
    	
    });
    
    