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
	$(".head").on("click",function(){
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
				    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
				    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
				    success: function (res) {
				    	sessionStorage.setItem("result",res.resultStr);
				    	window.location.href = "toscanresult";
					}
				});
			});
		      
		}
	});
})



