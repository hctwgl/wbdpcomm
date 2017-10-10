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
    	//判断属于哪家银行
    	$("#no").on("input",function(){
    		var no = $("#no").val();
    		if(no.length>6){
    			//判断属于哪家银行
				$.getJSON("http://www.wisedp.com/YiStaging/js/bankData.json",function(bankdata){ 
					//截取前六位
					var bin = no.substr(0,6);
					for(var i=0;i<bankdata.length;i++){
						if(bin===bankdata[i].bin){
							$("#banktype").val(bankdata[i].bankName);
						}
					}
				})
    		}
		});
		$.ajax({
			url:"getBankCard",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
			},
	      	success:function(data){
		      	if(data.code===1){
		      		alldata = data.data;
		      		var mydata = data.data;
		      		//给表单赋值
		      		$("#no").val(mydata.bankCard);
		      		$("#banktype").val(mydata.bankType);
		      		$("#openbank").val(mydata.openBank);
		       	}else{
		       		
		       	}
		    
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
		$("#sub").on("click",function(){
			var no;
   			//获取用户输入的值
   			if($("#no").val()===alldata.bankCard){
   				 no = "bankCard";
   			}else{
   				 no = $("#no").val();
   			}
   			
   			var noreg=/^\d{19}$/g;
			if(no!==""){
			
			}else{
				alert("请输入银行卡号");
				return false;
			}
			var banktype = $("#banktype").val();
   			if(banktype!=""){
			}else{
				alert("请输入所属银行");
				return false;
			}
   			var openbank = $("#openbank").val();
   			if(openbank!=""){
			
			}else{
				alert("请输入开户行");
				return false;
			}
   			$.ajax({
				url:"updateBankCard",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"bankCard":no,"bankType":banktype,"openBank":openbank},
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
			      			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftomine&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			      			
			      		},1000);
			       	}else if(data.code===2){
			      		alert("身份证与银行卡不匹配");
			      	}else{
			       		alert("修改失败");
			       	}
			    
		      	},
		      	error:function(){
		      	   $("#loadingToast").hide();
		           alert("操作失败");
		      	}
	    	});
   		});
    });