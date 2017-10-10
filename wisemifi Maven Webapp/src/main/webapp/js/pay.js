$(function(){
	var name = sessionStorage.getItem("name"),
   tel = sessionStorage.getItem("tel"),
   address = sessionStorage.getItem("address"),
   addressdetail = sessionStorage.getItem("addressdetail");
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
				jsApiList: ['chooseWXPay']
		 	});
		 }
	});
	$("#sub").on("click",function(){
		if (typeof WeixinJSBridge == "undefined"){
		   if( document.addEventListener ){
		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		   }else if (document.attachEvent){
		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		   }
		}else{
			wx.ready(function(){
				//获取统一下单
				$.ajax({
					url:"wxpay",
				    type: 'post',
					dataType: 'json',
					data: {"price": 1,"userip": returnCitySN["cip"],"customerName":name,"phone":tel,"address":address+"+"+addressdetail,"goodInfo":"维泽mifi无线路由"},
					complete: function(XMLHttpRequest,textStatus){},
					error: function(XMLHttpRequest,textStatus,errorThrown){
						alert("发生错误："+errorThrown);
					},
					success: function(res){
						var appId = res.data.appId;
						var noncestr = res.data.nonce;
						var package = res.data.packageName;
						var timestamp = res.data.timestamp;
						var signature = res.data.signature;
						var signType = res.data.signType;
						WeixinJSBridge.invoke(
					       'getBrandWCPayRequest', {
					           "appId":appId,     //公众号名称，由商户传入     
					           "timeStamp":timestamp,         //时间戳，自1970年以来的秒数     
					           "nonceStr":noncestr, //随机串     
					           "package":package,     
					           "signType":signType,         //微信签名方式：     
					           "paySign":signature //微信签名 
					       },
					       function(res){     
					           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
					           		window.location.href = '';
					           }
					           else{
					           	alert("已放弃支付");
					           }
					           // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
					       }
					    )
					 }
				});
		    ;
			});
		      
		}
	});
})



