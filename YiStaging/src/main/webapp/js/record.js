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
		
		$(".img").find("img").hide();
		$(".first").find("img").show();
		$(".first").attr("id","selected");
	    $(".img").click(function (e) {
			$(this).find("img").show();
			$(".img").not(this).find("img").hide();
			//给被选择的对像添加一个id
			$(this).attr("id","selected");
		});
		//显示当前用户的购物车
		$.ajax({
			url:"getUserBrush",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
	      		
				
			},
	      	success:function(data){
		      	if(data.status==="SUCCESS"){
		      		if(data.length === 0){
	      						$("#nogoods").show();
	      				}else{
	      					data= data.data;
	      					//详情显示
				      		var divstr = '';
			      			for(var i = 0;i<data.length;i++){
			      				
			      				if(i===0){
			      					divstr+='<div class="record"><div><span>总额：</span><span>￥'+data[i].pacPrice+'</span></div>';
									divstr+='<div><span>分期期数：</span><span>'+data[i].pacPeriods+'期</span></div>';
									divstr+='<div><span>每月应还金额：</span><span>￥'+data[i].pacMonthlyPrice+'</span></div>';
									divstr+='<div><span>还款开始时间：</span><span>'+data[i].startTime+'</span></div>';
									divstr+='<div><span>还款截至时间：</span><span>'+data[i].endTime+'</span></div>';
									divstr+='<div><span>下单时间：</span><span>'+data[i].brushTime+'</span></div>';
									if(data[i].faceStatus===0){
										divstr+='<div><span>审核状态：</span><span>未通过</span></div></div>';
									}else if(data[i].faceStatus===1){
										divstr+='<div><span>审核状态：</span><span>已通过</span></div></div>';
									}else{
										divstr+='<div><span>审核状态：</span><span>审核中</span></div></div>';
									}
			      				}else{
			      					divstr+='<div class="record gap1"><div><span>总额：</span><span>￥'+data[i].pacPrice+'</span></div>';
									divstr+='<div><span>分期期数：</span><span>'+data[i].pacPeriods+'期</span></div>';
									divstr+='<div><span>每月应还金额：</span><span>￥'+data[i].pacMonthlyPrice+'</span></div>';
									divstr+='<div><span>还款开始时间：</span><span>'+data[i].startTime+'</span></div>';
									divstr+='<div><span>还款截至时间：</span><span>'+data[i].endTime+'</span></div>';
									divstr+='<div><span>下单时间：</span><span>'+data[i].brushTime+'</span></div>';
									if(data[i].faceStatus===0){
										divstr+='<div><span>审核状态：</span><span>未通过</span></div></div>';
									}else if(data[i].faceStatus===1){
										divstr+='<div><span>审核状态：</span><span>已通过</span></div></div>';
									}else{
										divstr+='<div><span>审核状态：</span><span>审核中</span></div></div>';
									}
									
			      				}
			      
			      			}
				      		$(".container").append(divstr);
				      	
	      				}
		      		
		       	}else{
		       		
		       	}
		      
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
    });