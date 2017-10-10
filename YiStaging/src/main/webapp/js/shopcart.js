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
		//判断用户是否注册
		$.ajax({
			url:"isSCRegist",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
				
			},
	      	success:function(data){
		      	if(data.data==="已注册"){
		      		//显示当前用户的购物车
					var alldata;
					$.ajax({
						url:"getShopCart",
				      	type:"get",
				      	crossDomain:true,
				      	async:true,
				      	dataType:"json",
				      	data: {},
				      	beforeSend: function () {
				      			
						},
				      	success:function(data){
					      	if(data.code===1){
					      		//购物车显示
					      		alldata = data.data;
					      			if(data.data.length==0){
				      					$("#nogoods").show();
				      				}else{
				      					var divstr = '';
				      					for(var i = 0;i<data.data.length;i++){
					      					
						      				if(i===0){
							      				divstr+='<div class="popup-one"><div class="img" id="'+i+'"><img src="img/mall_icon_selected.png"/></div>';
												divstr+='<div class="image"><img src="'+data.data[i].goodImg+'" /></div><div class="detail">'
												divstr+='<div class="describe">'+data.data[i].goodName+'</div><div class="describe">'+data.data[i].color+'</div>';
												divstr+='<div class="gap1 param"><span>'+data.data[i].storage+'</span><span class="gap">'+data.data[i].color+'</span><span class="gap">'+data.data[i].supplier+'</span><span class="gap">'+data.data[i].wpackage+'</span></div>';
												divstr+='<div class="pay"><div><span class="cash">￥'+data.data[i].onePeriod+'</span><span class="num">.00</span><span>x'+data.data[i].periods+'期</span></div><div><span class="cash">总价:￥'+data.data[i].goodPrice+'</span><span class="num out"></span></div></div></div></div>';
						      				}else{
						      					divstr+='<div class="popup-one gap1"><div class="img" id="'+i+'"><img src="img/mall_icon_selected.png"/></div>';
												divstr+='<div class="image"><img src="'+data.data[i].goodImg+'" /></div><div class="detail">'
												divstr+='<div class="describe">'+data.data[i].goodName+'</div><div class="describe">'+data.data[i].color+'</div>';
												divstr+='<div class="gap1 param"><span>'+data.data[i].storage+'</span><span class="gap">'+data.data[i].color+'</span><span class="gap">'+data.data[i].supplier+'</span><span class="gap">'+data.data[i].wpackage+'</span></div>';
												divstr+='<div class="pay"><div><span class="cash">￥'+data.data[i].onePeriod+'</span><span class="num">.00</span><span>x'+data.data[i].periods+'期</span></div><div><span class="cash">总价:￥'+data.data[i].goodPrice+'</span><span class="num out"></span></div></div></div></div>';
						      				}
						      				
						      				
										    
											
					      				}
				      					$("#popup").append(divstr);
				      					$(".img").find("img").hide();
				      					$(".img").click(function() {
											$(this).find("img").toggle();
											$(this).addClass("selected");
										});
				      				}
				      			
		      					//删除订单
								$("#del").on("click",function(){
									//获取当前选中的信息
									var selectednode = $(".popup-one").find(".img");
									var selectedarray = [];
									for(var i=0;i<selectednode.length;i++){
										if(selectednode[i].className==="img selected"){
											//先清空显示内容
											selectedarray.push(Number(alldata[selectednode[i].id].shopcartID));
										}
									}
									if(selectedarray.length===0){
										alert("请选择要移除的商品");
									}else{
										var mymessage=confirm("你确定将选定的商品移出购物车？");  
									    if(mymessage==true)  
										{  
									       //调取移出商品的接口   
									       	$.ajax({
												url:"deleteCarts",
										      	type:"post",
										      	crossDomain:true,
										      	async:true,
										      	dataType:"json",
										      	data: {"cartIDarry":selectedarray},
										      	beforeSend: function () {
												},
										      	success:function(data){
											      	if(data.msg==="成功"){
											      		alert("删除成功");
											      		location.reload();
											       	}else{
											       		alert("删除失败");
											       	}
											      	
										      	},
										      	error:function(){
										      	   $("#loadingToast").hide();
										           alert("操作失败");
										      	}
									    	});
									    }  
									    else if(mymessage==false)  
									    {  
									   		
									    }
										
									}
									  
								});
								//提交订单
								$("#sub").on("click",function(){
									//获取当前选中的信息并保存在session中
									var selectednode = $(".popup-one").find(".img");
									var selectedarray = [];
									for(var i=0;i<selectednode.length;i++){
										if(selectednode[i].className==="img selected"){
											//先清空显示内容
											selectedarray.push(selectednode[i].id);
										}
									}
									if(selectedarray.length===1){
										sessionStorage.setItem("selectedid",selectedarray[0]);
										sessionStorage.setItem("selecteddata",JSON.stringify(alldata));	
										window.location.href = 'toSCPay';
									}else if(selectedarray.length===0){
										alert("请选择要提交的订单");
									}else{
										alert("只能选择一个订单提交");
									}
									
								});
					      		
					      		//控制显示
				      			
					       	}else{
					  
					       	}
					      
				      	},
				      	error:function(){
				      	   $("#loadingToast").hide();
				           alert("操作失败");
				      	}
			    	});
		      
		      		
		       	}else{
//		       		//未注册
		       		alert("未注册");
		       		window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftoregister&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		       	}
		      
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
		
		
    	
    });