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
    	$(".img").on("click",function(){
			$(this).find("img").toggle();
		});
    	var alldata;
    	$("#credit").on("input",function(){
    		var credit = $("#credit").val();
    		if(credit.length>6){
    			//判断属于哪家银行
				$.getJSON("http://www.wisedp.com/YiStaging/js/bankData.json",function(bankdata){ 
					//截取前六位
					var bin = credit.substr(0,6);
					for(var i=0;i<bankdata.length;i++){
						if(bin===bankdata[i].bin){
							$("#creditbank").val(bankdata[i].bankName);
						}
					}
				})
    		}
		});
		//获取社保卡
    	$.ajax({
			url:"getSSA",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
			},
	      	success:function(data){
		      	if(data.code===1){
		      		var mydata = data.data;
		      		//给表单赋值
		      		if(mydata!=null){
		      			$("#account").val(mydata.ssa);
		      			$("#pwd").val(mydata.password);
		      			//判断是否为老用户
		      		}
		      		
		      		
		       	}else{
		       		
		       	}
		    
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
    	//获取车险保单
    	$.ajax({
			url:"getMineInsNum",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
			},
	      	success:function(data){
		      	if(data.code===1){
		      		var mydata = data.data;
		      		//给表单赋值
		      		if(mydata!=null){
		      			if(mydata.plateNum){
		      				$("#car").val(mydata.plateNum);
		      			}
		      			if(mydata.insuranceComp){
		      				$("#company").val(mydata.insuranceComp);
		      			}
		      			if(mydata.insuranceNum){
		      				$("#num").val(mydata.insuranceNum);
		      			}
		      		/*	if(mydata.isNoOlder){
		      				if(mydata.isNoOlder===0){
								$(".img").find("img").show();
			      			}else{
			      				$(".img").find("img").hide();
			      			}
		      			}*/
		      			
		      		}
		      		
		       	}else{
		       		
		       	}
		    
	      	},
	      	error:function(){
	           alert("操作失败");
	      	}
    	});
    	$.ajax({
			url:"getMineCC",
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
		      		if(mydata!=null){
		      			//给表单赋值
			      		$("#credit").val(mydata.creditcard);
			      		$("#creditbank").val(mydata.bankname);
		      		}
		      		
		      		
		       	}else{
		       		
		       	}
		    
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
		$("#sub").on("click",function(){
			/*var isselected;
			if($(".img img").is(":hidden")){
				isselected = 1;
			}else{
				isselected = 0;
			}*/
   			//获取所有输入的值
			var inputnode = $(".input");
			var inputTexts = [];
			for(var i = 0;i<inputnode.length;i++){
				inputTexts.push(inputnode[i].value);
			}
			var isexist = [];
			for(var i = 0;i<inputTexts.length;i++){
				if(inputTexts[i]!==""){
					isexist.push("1");
				}
			}	
			if(isexist.length===0){
				alert("社保，车险，信用卡至少输入一项");
				return false;
			}
   			//获取用户输入的值
   			var account = $("#account").val();
			var pwd = $("#pwd").val();
			var car = $("#car").val();
			var company = $("#company").val();
			var num = $("#num").val();
			var credit;
			if(alldata!==null){
				if($("#credit").val()===alldata.creditcard){
	   				credit = "creditcard";
	   			}else{
	   				credit = $("#credit").val();
	   			}
			}else{
				credit = $("#credit").val();
			}
			
			var creditbank = $("#creditbank").val();
			if(account===""&&pwd!==""){
				alert("请输入社保用户");
				return false;
			}
			if(account!==""&&pwd===""){
				alert("请输入社保密码");
				return false;
			}
			if(car===""&&company!==""){
				alert("请输入车牌号码");
				return false;
			}
			if(car!==""&&company===""){
				alert("请输入保险公司");
				return false;
			}
		
			if(credit!==""&&creditbank===""){
				alert("请输入信用卡所属银行");
				return false;
			}
			
			if(credit===""&&creditbank!==""){
				alert("请输入信用卡号");
				return false;
			}
   			$.ajax({
				url:"updateSSAINCC",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"creditcard":credit,"bankname":creditbank,"plateNum":car,"insuranceComp":company,"insuranceNum":num,"SSA":account,"password":pwd,"isNoOlder":3},
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
			      			window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftomine&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
				      	},1000);
			       	}else{
			       		alert("录入失败");
			       	}
			      	
		      	},
		      	error:function(){
		      	   $("#loadingToast").hide();
		           alert("操作失败");
		      	}
	    	});
   		});
    	
    	
    });