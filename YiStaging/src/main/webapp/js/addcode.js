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
    	//计算
    	$(".price").on("input",result);
    	$(".period").on("change",result);
    	$(".result").on("input",result1);
    	//点击生成二维码
   		$("#sub").on("click",function(){
   			var price = $(".price").val(),
    		period = $(".period").find('option:selected').text().replace("期","");
    		result = $(".result").val();
    		if(price===''){
    			alert("请输入价格");
    			return false;
    		}
			if(result%period==0){
    		
	    	}else{
	    		alert("总金额请输入"+period+"的倍数");
	    		return false;
	    	}
   			$.ajax({
				url:"createQRCode",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"pacMonthlyPrice":Number(price),"pacPeriods":Number(period)},
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
			      			window.location.href="toORCode";
			      			
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

    //价格计算器
    function result(){
    	var price = $(".price").val(),
    	period = $(".period").find('option:selected').text().replace("期",""),
    	result = $(".result").val();
		//判断输入的哪个
		var sum = price*period;
		$(".result").val(sum);
    }
     //价格计算器
    function result1(){
    	var price = $(".price").val(),
    	period = $(".period").find('option:selected').text().replace("期",""),
    	result = $(".result").val();
    
 		$(".price").val("");
		var per = result/period;
		$(".price").val(per);
    }

    