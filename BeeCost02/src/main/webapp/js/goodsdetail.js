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
    	var isExist;//判断该商品是否存在
    	var isOff;//判断商品是否下架
    	var supID;
    	var pdata;//手机分期数据
    	var price;
    	$("#popexist").click(function(){
			$("#popup").hide();
		});
    	$("#shopcart").click(function(){
			window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2FtoShopCart&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		});
    	var goodid = sessionStorage.getItem("goodid");
		//初始化swiper控件
        var mySwiper = new Swiper('.swiper-container',{
			pagination : '.swiper-pagination',
			observer:true,//修改swiper自己或子元素时，自动初始化swiper
    		observeParents:true,//修改swiper的父元素时，自动初始化swiper
    		autoplay: 2000,
    		//滑动到对应页面时修改页面数据
			onSlideChangeEnd: function(swiper){
		   },	
		});
		//获取当前商品的详情
		$.ajax({
			url:"getGoodsDetails",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {"goodID":goodid},
	      	beforeSend: function () {
				
			},
	      	success:function(data){
	      		if(data.code===1){
	      			$("#img1").attr("src",data.data[0].goodImg);
	      			$("#img2").attr("src",data.data[0].goodImg);
	      			$("#img3").attr("src",data.data[0].goodImg);
	      			$("#img4").attr("src",data.data[0].goodImg);
	      			$(".middle-one").html(data.data[0].goodName);
	      			$("#popname").html(data.data[0].goodName);
	      			$("#oneprice").html("￥"+data.data[0].goodPrice);
	      			$("#operator").html(data.data[0].supplier);
	      			supID = data.data[0].supID;
	      			if(data.data[0].payer===0){
	      				$(".stage1").hide();
	      			}
	      			 
	      		}
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
    	//获取当前商品的其他详情
    	$.ajax({
			url:"getGoodsCSO",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {"goodID":goodid},
	      	beforeSend: function () {
				
			},
	      	success:function(otherdata){
	      		if(otherdata.code===1){
	      			//颜色
	      			var colorstr = "";
	      			var ramstr = "";
	      			var operatorstr = "";
	      			var stagestr = "";
	      			for(var i = 0;i<otherdata.data.color.length;i++){
	      				colorstr+='<span id="'+otherdata.data.color[i].id+'">'+otherdata.data.color[i].value+'</span>';
	      			}
	      			$(".color").html(colorstr);
	      			for(var i = 0;i<otherdata.data.storage.length;i++){
	      				ramstr+='<span id="'+otherdata.data.storage[i].id+'">'+otherdata.data.storage[i].value+'</span>';
	      			}
	      			$(".ram").html(ramstr); 
	      			//控制选择
					//默认第一个被选中
						$(".type span:first-child").addClass("selected");
						//确认选择
						var colornode = $(".color span");
						var colorid;
						for(var i=0;i<colornode.length;i++){
							if(colornode[i].className==="selected"){
								colorid = colornode[i].id;
							}
						}
						var ramnode = $(".ram span");
						var ramid;
						for(var i=0;i<ramnode.length;i++){
							if(ramnode[i].className==="selected"){
								ramid = ramnode[i].id;
							}
						}
						//根据选择更总金额
						$.ajax({
							url:"getGoodPriceByCSO",
					      	type:"get",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data: {"goodID":goodid,"ctype":Number(colorid),"stype":Number(ramid),"supID":supID},
					      	beforeSend: function () {
								
							},
					      	success:function(data){
					      		if(data.code===1){
					      			isExist = data.data.goodPice;
					      			if(data.data.skuStatus===1){
					      				isOff = 1;
					      			}
					      			//根据skuid获取商品套餐信息
					      			$.ajax({
										url:"getPhonePackage",
								      	type:"post",
								      	crossDomain:true,
								      	async:true,
								      	dataType:"json",
								      	data: {"skuid":data.data.gskuID},
								      	beforeSend: function () {
											
										},
								      	success:function(packagedata){
								      		var packagestr = "";
								      		if(packagedata.code===1){
								      			pdata = packagedata; 
								      			for(var i = 0;i<packagedata.data.length;i++){
								      				packagestr+='<span id="'+packagedata.data[i].pID+'">每月话费￥'+packagedata.data[i].wpackage+'个月，需另付'+packagedata.data[i].payable+'元</span>';
								      			}
								      			$(".package").html(packagestr); 
								      			$(".package span:first-child").addClass("selected");
								      			//默认选择第一个
								      			$(".packagedetail").html(packagedata.data[0].details);
								      			price = packagedata.data[0].pacPrice;
								      			$(".package span").on("click",function(){
								      				$(this).addClass("selected");
													$(this).siblings("span").removeClass("selected");
								      				var id = $(this).attr("id");
								      	
								      				for(var j = 0;j<packagedata.data.length;j++){
								      					if(Number(id) === packagedata.data[j].pID){
								      						$(".packagedetail").html("");
								      						$(".packagedetail").html(packagedata.data[j].details);
								      						price = packagedata.data[j].pacPrice;
								      					}
								      				}
								      			});
								      		}
								      	},
								      	error:function(){
								      	   $("#loadingToast").hide();
								           alert("操作失败");
								      	}
							    	});
					      			
					      			
					      		}
					      	},
					      	error:function(){
					           alert("操作失败");
					      	}
				    	});
					$(".type span").on("click",function(){
						$(this).addClass("selected");
						$(this).siblings("span").removeClass("selected");
						//确认选择
						var colornode = $(".color span");
						var colorid;
						for(var i=0;i<colornode.length;i++){
							if(colornode[i].className==="selected"){
								colorid = colornode[i].id;
							}
						}
						var ramnode = $(".ram span");
						var ramid;
						for(var i=0;i<ramnode.length;i++){
							if(ramnode[i].className==="selected"){
								ramid = ramnode[i].id;
							}
						}
						//根据选择更总金额
						$.ajax({
							url:"getGoodPriceByCSO",
					      	type:"get",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data: {"goodID":goodid,"ctype":Number(colorid),"stype":Number(ramid),"supID":supID},
					      	beforeSend: function () {
								
							},
					      	success:function(data){
					      		if(data.code===1){
				      				isExist = data.data.goodPrice;
					      			if(data.data.skuStatus===1){
					      				isOff = 1;
					      			}
					      			//根据skuid获取商品套餐信息
					      		
					      			
					      			
					      		}
					      	},
					      	error:function(){
					           alert("操作失败");
					      	}
				    	});
						
					});
	      		}
	      		
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
		
		//控制弹出层
		$("#bottom span").on("click",function(){
			$("#popup").show();
			//获取点击的id
			var id = $(this).attr("id");
			
			if(id==="add"){
				//具体选择
				$(".popup-three").off("click").on("click",function(){
					if(isExist===null){
						alert("该商品不存在");
						return false;
					}
					if(isOff===1){
						alert("该商品已下架");
						return false;
					}
					//先判断当前用户是否注册
					$.ajax({
						url:"isRegist",
				      	type:"get",
				      	crossDomain:true,
				      	async:true,
				      	dataType:"json",
				      	data: {},
				      	beforeSend: function () {
							
						},
				      	success:function(data){
				      		
					      	if(data.data==="已注册"){
					      		//
					      	//确认选择
								var colornode = $(".color span");
								var colorid;
								for(var i=0;i<colornode.length;i++){
									if(colornode[i].className==="selected"){
										colorid = colornode[i].id;
									}
								}
								var ramnode = $(".ram span");
								var ramid;
								for(var i=0;i<ramnode.length;i++){
									if(ramnode[i].className==="selected"){
										ramid = ramnode[i].id;
									}
								}
								var packagenode = $(".package span");
								var packageid;
								for(var i=0;i<packagenode.length;i++){
									if(packagenode[i].className==="selected"){
										packageid = packagenode[i].id;
									}
								}
								var stagenode = $(".stage span");
								var stageid;
								for(var i=0;i<stagenode.length;i++){
									if(stagenode[i].className==="selected"){
										stageid = stagenode[i].innerHTML.replace("期","");
									}
								}
								
								//根据选择获取
								$.ajax({
									url:"addOrder",
							      	type:"get",
							      	crossDomain:true,
							      	async:true,
							      	dataType:"json",
							      	data: {"goodID":goodid,"ctype":Number(colorid),"stype":Number(ramid),"pID":Number(packageid),"supID":supID},
							      	beforeSend: function () {
										
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
								      		},2000);
									        //跳回申请界面
									        setTimeout(function(){
								      			$("#popup").hide();
								      		},1000);
								      	}else{
								      		
								      	}
							      	},
							      	error:function(){
							      	   $("#loadingToast").hide();
							           alert("操作失败");
							      	}
						    	});
					      		
					       	}else{
					       		alert("还未注册");
					       		window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
					       	}
					      
				      	},
				      	error:function(){
				      	   $("#loadingToast").hide();
				           alert("操作失败");
				      	}
			    	});
					
				});  
				
			}else{
				//具体选择
				$(".popup-three").off("click").on("click",function(){
					if(isExist===null){
						alert("该商品不存在");
						return false;
					}
					if(isOff===1){
						alert("该商品已下架");
						return false;
					}
					//先判断当前用户是否注册
					$.ajax({
						url:"isRegist",
				      	type:"get",
				      	crossDomain:true,
				      	async:true,
				      	dataType:"json",
				      	data: {},
				      	beforeSend: function () {
							
						},
				      	success:function(data){
				      		
					      	if(data.data==="已注册"){
					      		//
					      	//确认选择
								var colornode = $(".color span");
								var colorid;
								for(var i=0;i<colornode.length;i++){
									if(colornode[i].className==="selected"){
										colorid = colornode[i].id;
									}
								}
								var ramnode = $(".ram span");
								var ramid;
								for(var i=0;i<ramnode.length;i++){
									if(ramnode[i].className==="selected"){
										ramid = ramnode[i].id;
									}
								}
								var packagenode = $(".package span");
								var packageid;
								for(var i=0;i<packagenode.length;i++){
									if(packagenode[i].className==="selected"){
										packageid = packagenode[i].id;
									}
								}
								var wpackage
								for(var i = 0;i<pdata.data.length;i++){
									if(Number(packageid)===pdata.data[i].pID){
										wpackage = pdata.data[i].wpackage;
									}
								}
								var stagenode = $(".stage span");
								var stageid;
								for(var i=0;i<stagenode.length;i++){
									if(stagenode[i].className==="selected"){
										stageid = stagenode[i].innerHTML.replace("期","");
									}
								}
								
								//将当前id保存在session中
								sessionStorage.setItem("goodid",goodid);
								sessionStorage.setItem("colorid",colorid);
								sessionStorage.setItem("ramid",ramid);
								sessionStorage.setItem("packageid",packageid);
								sessionStorage.setItem("wpackage",wpackage);
								sessionStorage.setItem("supid",supID);
								sessionStorage.setItem("price",JSON.stringify(price));
								window.location.href = "toPay";
								
								
								
								
								
					      		
					       	}else{
					       		alert("还未注册");
					       		window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
					       	}
					      
				      	},
				      	error:function(){
				      	   $("#loadingToast").hide();
				           alert("操作失败");
				      	}
			    	});
					
				});  
			}
		});
		//跳转到购物车
		
		 	
	});