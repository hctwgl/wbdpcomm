$(function(){
	var url = window.location.href;
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
				jsApiList: ['chooseImage','uploadImage']
		 });
	   }	
	});
	var loallds = new Array();
	var serverIds = new Array();
	var oneloalld  = "";
	var num = 0;
		$(".form").click(function() {
			var type = $(this).attr("id");
			wx.ready(function() {
	              		 wx.chooseImage({  
					debug: false,
	                   			count: 1, 
				   	sizeType: ['original','compressed'],
	                    		sourceType: ['camera'], 
	                    		success: function (res) {  
	                      		oneloalld  =  res.localIds; 
					loallds[0] = oneloalld;
					if(type=="positive")
						$("#img_positive").attr("src",oneloalld.toString());
					else if(type=="opposite")
						$("#img_opposite").attr("src",oneloalld.toString());
					uploadImages(oneloalld,type);
				}
	   });
			});
		});
		
		var serverIdstring = "";
		
		function uploadImages(oneloalld,type){
			//解决IOS无法上传的坑  
			if (oneloalld.indexOf("wxlocalresource") != -1) {  
			 	oneloalld = localId.replace("wxlocalresource", "wxLocalResource");  
			} 
			wx.ready(function() {
				wx.uploadImage({  
				    debug: false,
				        localId: oneloalld.toString(),
				        isShowProgressTips: 1, 
				       success: function (res) {  
						var oneserverId = res.serverId;

						if(type=="positive")
						  $("#oneserverId1").html(oneserverId);
						else if(type=="opposite")
						  $("#oneserverId2").html(oneserverId);
				   },  
			       fail: function (res) {  
			    	   $.alert("上传失败，请重新上传！");  
			       }
				 });  	
			});
	    }

});