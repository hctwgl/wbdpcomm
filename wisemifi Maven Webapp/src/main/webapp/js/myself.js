$(function(){
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
    
   //获取订单信息
   $.ajax({
		url:"getMyself",
      	type:"post",
      	crossDomain:true,
      	async:true,
      	dataType:"json",
      	data: {},
      	beforeSend: function () {
		
			},
      	success:function(result){
      		data = result.data
	      	if(data.length===0){
	      		$("#nogoods").show();
	      		$("#mygoods").hide();
	      		$("#buy").on("click",function(){
	      			window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb125fba57e2524e2&redirect_uri=http%3A%2F%2Fwisedp.com%2Fwisemifi%2Fwx%2Fwisemifi%2Ftobuypage&response_type=code&scope=snsapi_base&state=123#wechat_redirect';
	      		});
	      		$("#scan").on("click",scanCode);
	      		
	       	}else{
	       		$("#nogoods").hide();
	      		for(var i = 0;i<data.length;i++){
	      			var divstr='<div class="change-record"><div class="record">';
	      			if(data[i].orderType===1){
	      				divstr+='<span class="order">购买维泽无线</span><span class="cash">-￥'+data[i].price+'</span>';
	      			}else if(data[i].orderType===2){
	      				divstr+='<span class="order">包年套餐充值</span><span class="cash">-￥'+data[i].price+'</span>';
	      			}else{
	      				divstr+='<span class="order">推广产品奖励</span><span class="cash">+￥'+data[i].price+'</span>';
	      			}
					divstr+='</div><div class="record record-status">';			
					divstr+='<span>'+data[i].orderCreateTime+'</span>';
					if(data[i].orderStatus===1){
						divstr+='<span>已支付</span></div></div>';
					}else if(data[i].orderStatus===2){
						divstr+='<span>未支付</span></div></div>';
					}else if(data[i].orderStatus===3){
						divstr+='<span>未到账</span></div></div>';
					}else{
						divstr+='<span>已到账</span></div></div>';
					}
					$(".change-detail").append(divstr);
	      		}
	      		
	      		$("#mygoods").show();
	       		//判断有没有二维码
	       		$.ajax({
					url:"getQRcode",
			      	type:"post",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	data: {},
			      	beforeSend: function () {
					},
			      	success:function(codedata){
			      		if(codedata.data){
			      			//有二维码
			      			$(".code").show();
			      			$(".nocode").hide();
		      				$("#codeimg").attr("src",codedata.data);
		      				$("#detailimg").attr("src",codedata.data);
		      				$(".code-img").on("click",function(){
		      					$("#codedetail").show();
		      				});
		      				$("#codedetail").not("#detailimg").on("click",function(){
		      					$("#codedetail").hide();
		      				});
			      		}else{
			      			$(".code").hide();
			      			$(".nocode").show();
			      			$("#createcode").on("click",createCode);
			      		}
			      	},
			      	error:function(){
			           alert("二维码获取失败");
			      	}
				});
	       		
	      		    		
	       	}
      	
      	},
      	error:function(){
      		$("#loadingToast").hide();
           alert("操作失败");
      	}
    });
   
	
});
//生成二维码
function createCode(){
	var name = $("#name").val(),
 	tel = $("#tel").val();
	var namereg=/^[\u4E00-\u9FA5]{2,4}$/
	if(name!=""){
		if(!(namereg.test(name))){
			alert("请输入正确的姓名");
			return false;
		}
	}else{
			alert("姓名不能为空");
			return false;
	}
	var telreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(tel!=""){
		if(!telreg.test(tel)){
			alert("手机号码格式不正确");
			return false;
		}
	}else{
		alert("手机号码不能为空");
		return false;
	}	
	$.ajax({
		url:"creatQRcode",
      	type:"post",
      	crossDomain:true,
      	async:true,
      	dataType:"json",
      	data: {"customerName":name,"customerPhone":tel},
      	beforeSend: function () {
		},
      	success:function(data){
	      	//将二维码显示出来
	      	$(".nocode").hide();
	      	$(".code").show();
	      	$("#codeimg").attr("src",data.data);
	    
      	},
      	error:function(){
      	   $("#loadingToast").hide();
           alert("操作失败");
      	}
	});
	
}
//扫描二维码
function scanCode(){
	var url = window.location.href;
	//获取签名等参数并验证
	$.ajax({
		url:"gettic",
	    type: 'post',
		dataType: 'json',
		data: {"url" : url},
		complete: function(XMLHttpRequest,textStatus){},
		error: function(XMLHttpRequest,textStatus,errorThrown){
			alert("发生错误："+errorThrown);
		},
		success: function(res){
			var appId = res.appId;
			var noncestr = res.noncestr;
			var jsapi_ticket = res.jsapi_ticket;
			var timestamp = res.timestamp;
			var signature = res.signature;
			wx.config({
				debug: false,
				appId: appId,
				timestamp: timestamp,
				nonceStr: noncestr,
				signature: signature,
				jsApiList: ['scanQRCode']
		 	});
		 }
	});
	if (typeof WeixinJSBridge == "undefined"){
		   if( document.addEventListener ){
		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		   }else if (document.attachEvent){
		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		   }
		}else{
			wx.ready(function(){
				wx.scanQRCode({
				    needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
				    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
				    success: function (res) {
				    	window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb125fba57e2524e2&redirect_uri=http%3A%2F%2Fwisedp.com%2Fwisemifi%2Fwx%2Fwisemifi%2Ftobuypage&response_type=code&scope=snsapi_base&state=123#wechat_redirect';
					}
				});
			});
		      
		}
	
}
