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
	//获取当前地址经纬度
	var address = sessionStorage.getItem("address");
	if(address){
		
	}else{
		getLocation();
	}
	//初始化城市选择控件
	$(".city").CityPicker(
		function(){
			alert($(".city").val());
		}
	);
	$(".city-picker").on("click", function (e) {
		$("#operator").hide();
		$("#brand").hide();
		
	});
	//导航条控制
	//默认城市被选中
/*	$("#address").addClass("clicked")
	$(".bar").on("click",function(){
		$(this).addClass("clicked");
		$(this).siblings("div").removeClass("clicked");
	});*/
	//弹出筛选选择
	$("#choice").on("click",function(){
		$("#brand").show();
	})
	//关闭筛选选择
	$(".bar").not("#choice").on("click",function(){
		$("#brand").hide();
	});
	//获取手机品牌
	$.ajax({
		url:"getPhoneType",
      	type:"get",
      	crossDomain:true,
      	async:true,
      	dataType:"json",
      	data: {},
      	beforeSend: function () {
			
		},
      	success:function(branddata){
      		
	      	if(branddata.code===1){
	      		//将品牌显示在页面
	      		var divstr = "";
	      		for(var i = 0;i<branddata.data.length;i++){
	      			if(branddata.data.length%2===0){
	      				if(i%2==0){
	      					divstr+='<div><span id="'+branddata.data[i].phoneTypeID+'">'+branddata.data[i].attributeValue+'</span>';
		      			}else{
		      				divstr+='<span id="'+branddata.data[i].phoneTypeID+'">'+branddata.data[i].attributeValue+'</span></div>';
		      			}
	      			}else{
	      				if(i%2==0){
	      					if(i===branddata.data.length){
	      						divstr+='<div><span id="'+branddata.data[i].phoneTypeID+'">'+branddata.data[i].attributeValue+'</span></div>';
	      					}else{
	      						divstr+='<div><span id="'+branddata.data[i].phoneTypeID+'">'+branddata.data[i].attributeValue+'</span>';
	      					}
	      					
		      			}else{
		      				divstr+='<span id="'+branddata.data[i].phoneTypeID+'">'+branddata.data[i].attributeValue+'</span></div>';
		      			}
	      			}
	      			
	      		}
	      		$(".brand-other").html(divstr);
	      		//获取运营商
				$.ajax({
					url:"getOperator",
			      	type:"get",
			      	crossDomain:true,
			      	async:true,
			      	dataType:"json",
			      	data: {},
			      	beforeSend: function () {
						
					},
			      	success:function(operatordata){
			      		
				      	if(operatordata.code===1){
				      		//将品牌显示在页面
				      		var divstr1 = "";
				      		for(var i = 0;i<operatordata.data.length;i++){
				      			if(operatordata.data.length%2===0){
				      				if(i%2==0){
				      					divstr1+='<div><span id="'+operatordata.data[i].operatorID+'">'+operatordata.data[i].operatorName+'</span>';
					      			}else{
					      				divstr1+='<span id="'+operatordata.data[i].operatorID+'">'+operatordata.data[i].operatorName+'</span></div>';
					      			}
				      			}else{
				      				if(i%2==0){
				      					if(i===operatordata.data.length){
				      						divstr1+='<div><span id="'+operatordata.data[i].operatorID+'">'+operatordata.data[i].operatorName+'</span></div>';
				      					}else{
				      						divstr1+='<div><span id="'+operatordata.data[i].operatorID+'">'+operatordata.data[i].operatorName+'</span>';
				      					}
				      					
					      			}else{
					      				divstr1+='<span id="'+operatordata.data[i].operatorID+'">'+operatordata.data[i].operatorName+'</span></div>';
					      			}
				      			}
				      		}
				      		
				      		$(".operator-one").html(divstr1);
				      		//选择品牌
				      		$(".brand-three").on("click",function(){
								$("#brand").hide();
							});
							$(".brand-one div span").on("click",function(){
								
								$(this).toggleClass("brand-selected");
							});
							//重置品牌选择
							$("#reset").on("click",function(){
								$(".brand-one div span").removeClass("brand-selected");
							});
							//手机品牌选择
							$("#sure").on("click",function(){
								$("#brand").hide();
								//获取用户选择的品牌
								var brandnode = $(".brand-other div span");
								var brandarr = [];
								for(var i=0;i<brandnode.length;i++){
									if(brandnode[i].className==="brand-selected"){
										//先清空显示内容
										brandarr.push(Number(brandnode[i].id));
									}
								}
								var operatornode = $(".operator-one div span");
								var operatorarr = [];
								for(var i=0;i<operatornode.length;i++){
									if(operatornode[i].className==="brand-selected"){
										$("#bottom").html();
										//获取此时城市选择的值
										
										operatorarr.push(operatornode[i].innerHTML);
										
									}
								}
								var city = $(".city").val();
								
								//根据条件获取
								if(operatorarr.length===0&&brandarr.length===0){
									
								}else{
									var mixarr = operatorarr.map(function(item){
										return city+item;
									})
									bycityandoperator(mixarr,brandarr,0);
								}
								
							});
				      	
				       }
			      	},
			      	error:function(){
			
			           alert("操作失败");
			      	}
				});
	      		
	       }
      	},
      	error:function(){

           alert("操作失败");
      	}
	});
	
	//默认加载全部
	loadall(0);
	//加载全部
	/*$("#all1").on("change",function(){
    	loadall(0);
	});*/
	
	//价格选择	
});
//获取滚动条当前的位置 
function getScrollTop() { 
	var scrollTop = 0; 
	if (document.getElementById("bottom") && document.getElementById("bottom").scrollTop) { 
	scrollTop = document.getElementById("bottom").scrollTop; 
	} 
	else if (document.body) { 
	scrollTop = document.body.scrollTop; 
	} 
	return scrollTop; 
} 

//获取当前可视范围的高度 
function getClientHeight() { 
	var clientHeight = 0; 
	if (document.body.clientHeight && document.getElementById("bottom").clientHeight) { 
		clientHeight = Math.min(document.body.clientHeight, document.getElementById("bottom").clientHeight); 
	} 
	else { 
		clientHeight = Math.max(document.body.clientHeight, document.getElementById("bottom").clientHeight); 
	} 
	return clientHeight; 
} 

 //获取文档完整的高度 
function getScrollHeight() { 
	return Math.max(document.body.scrollHeight, document.getElementById("bottom").scrollHeight); 
} 
window.onscroll = function () { 
    
}
var x= '1';
//加载前十条数据
function loadall(type){
	//默认加载前十条数据
	$.ajax({
		url:"getGoods",
      	type:"get",
      	crossDomain:true,
      	async:true,
      	dataType:"json",
      	data:{"limit":0,"type":type},
      	beforeSend: function () {
			
		},
      	success:function(phonedata){
      		var alldata;
	      	if(phonedata.code===1){
	      		//将品牌显示在页面
	      		var divstr = "";
	      		alldata = phonedata;
	      		
	      		for(var i = 0;i<phonedata.data.length;i++){
	      			if(phonedata.data.length%2===0){
	      				if(i%2==0){
	      					divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
							divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
						    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
						    divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
		      			}else{
		      				divstr+='<div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
							divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
							divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
		      			}
	      			}else{
	      				if(i%2==0){
	      					if(i===phonedata.data.length){
	      						divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
	      					}else{
	      						divstr+='<div class="bottom-one"><div class="container"  id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
	      					}
		      			}else{
		      					divstr+='<div class="container"  id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
		      			}
	      			}
      				
	      		}
	      		$("#bottom").empty();
	      		$("#bottom").append(divstr);
	      		$(".container").on("click",function(){
					var id = $(this).attr("id");
					sessionStorage.setItem("goodid",id);
					window.location.href = 'toGoodsDetails';
				
				});
	      		var j = 0;
      			
				$("#telprice").off("click").on("click",function(){
					if(x==='0'){
			  			x="1";
			  		}else{
			  			x='0';
			  		}
					loadall(x);
					
				});
	      		$("#bottom").off("scroll").on("scroll",function(){
					if (getScrollTop() + getClientHeight() == getScrollHeight()) { 
						j=j+10;
				    	//ajax从这里开始
				    	$.ajax({
							url:"getGoods",
					      	type:"get",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data:{"limit":j,"type":0},
					      	beforeSend: function () {
								
							},
					      	success:function(teldata){
					      		if(teldata.code===1){
					      			if(teldata.data.length!==0){
					      				for(var i = 0;i<teldata.data.length;i++){
							      			if(teldata.data.length%2===0){
							      				if(i%2==0){
							      					divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
													divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
													divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
								      			}else{
								      				divstr+='<div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
													divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
													divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}
							      			}else{
							      				if(i%2==0){
							      					if(i===teldata.data.length){
							      						divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
							      					}else{
							      						divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
							      					}
								      			}else{
								      					divstr+='<div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}
							      			}
						      			
							      		}
						      			$("#bottom").append(divstr);
						      			$(".container").on("click",function(){
											var id = $(this).attr("id");
											sessionStorage.setItem("goodid",id);
											window.location.href = 'toGoodsDetails';
										
										});
					      			}
					      			
					      		}
					      		
					      	},
					      	error:function(){
					           alert("操作失败");
					      	}
				    	});
			    	 
			    	}
				});
	       }
      	},
      	error:function(){

           alert("操作失败");
      	}
	});
}
//根据手机品牌选择
function loadbrand(brandid,type){
	//默认加载前十条数据
	$.ajax({
		url:"getGoodsByPhoneTypeID",
      	type:"get",
      	crossDomain:true,
      	async:true,
      	dataType:"json",
      	data:{"limit":0,"phoneTypeID":brandid,"type":type},
      	beforeSend: function () {
			
		},
      	success:function(phonedata){
      		var alldata;
	      	if(phonedata.code===1){
	      		//将品牌显示在页面
	      		var divstr = "";
	      		alldata = phonedata;
	      		if(phonedata.data.length!==0){
	      			for(var i = 0;i<phonedata.data.length;i++){
		      			if(phonedata.data.length%2===0){
		      				if(i%2==0){
		      					divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
			      			}else{
			      				divstr+='<div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
			      			}
		      			}else{
		      				if(i%2==0){
		      					if(i===phonedata.data.length){
		      						divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
		      					}else{
		      						divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
		      					}
			      			}else{
			      					divstr+='<div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
			      			}
		      			}
	      			
		      		}
	      			$("#bottom").empty();
		      		$("#bottom").append(divstr);
		      		$(".container").on("click",function(){
						var id = $(this).attr("id");
						sessionStorage.setItem("goodid",id);
						window.location.href = 'toGoodsDetails';
					
					});
	      		}else{
	      			$("#bottom").empty();
	      		}
	      		
	      		var j = 0;
				$("#telprice").off("click").on("click",function(){
					if(x==='0'){
			  			x="1";
			  		}else{
			  			x='0'
			  		}
					loadbrand(brandid,x);
					
				});
	      		$("#bottom").off("scroll").on("scroll",function(){
					if (getScrollTop() + getClientHeight() == getScrollHeight()) { 
						j=j+10;
				    	//ajax从这里开始
				    	$.ajax({
							url:"getGoodsByPhoneTypeID",
					      	type:"get",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data:{"limit":j,"phoneTypeID":brandid,"type":type},
					      	beforeSend: function () {
								
							},
					      	success:function(teldata){
					      		if(teldata.code===1){
					      			if(teldata.data.length!==0){
					      				for(var i = 0;i<teldata.data.length;i++){
							      			if(teldata.data.length%2===0){
							      				if(i%2==0){
							      					divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
													divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
													divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
								      			}else{
								      				divstr+='<div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
													divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
													divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}
							      			}else{
							      				if(i%2==0){
							      					if(i===teldata.data.length){
							      						divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
							      					}else{
							      						divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
							      					}
								      			}else{
								      					divstr+='<div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}
							      			}
						      				
							      		}
					  
						      			$("#bottom").append(divstr);
						      			$(".container").on("click",function(){
											var id = $(this).attr("id");
											sessionStorage.setItem("goodid",id);
											window.location.href = 'toGoodsDetails';
										
										});
					      			}
					      			
					      		}
					      		
					      	},
					      	error:function(){
					           alert("操作失败");
					      	}
				    	});
			    	 
			    	}
				});
	       }
      	},
      	error:function(){

           alert("操作失败");
      	}
	});
}
//根据运营商选择
function loadoperator(operatorid,type){
	//默认加载前十条数据
	$.ajax({
		url:"getGoodsByOperator",
      	type:"get",
      	crossDomain:true,
      	async:true,
      	dataType:"json",
      	data:{"limit":0,"operatorType":Number(operatorid),"type":type},
      	beforeSend: function () {
			
		},
      	success:function(phonedata){
      		var alldata;
	      	if(phonedata.code===1){
	      		//将品牌显示在页面
	      		var divstr = "";
	      		alldata = phonedata;
	      		if(phonedata.data.length!==0){
		      		for(var i = 0;i<phonedata.data.length;i++){
		      			if(phonedata.data.length%2===0){
		      				if(i%2==0){
		      					divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
			      			}else{
			      				divstr+='<div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
			      			}
		      			}else{
		      				if(i%2==0){
		      					if(i===phonedata.data.length){
		      						divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
		      					}else{
		      						divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
		      					}
			      			}else{
			      					divstr+='<div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
			      			}
		      			}
	      				
		      		}
		      		$("#bottom").empty();
		      		$("#bottom").append(divstr);
		      		$(".container").on("click",function(){
						var id = $(this).attr("id");
						sessionStorage.setItem("goodid",id);
						window.location.href = 'toGoodsDetails';
					
					});
	      		}else{
	      			$("#bottom").empty();
	      		}
	      		var j = 0;
				$("#telprice").off("click").on("click",function(){
					if(x==='0'){
			  			x="1";
			  		}else{
			  			x='0'
			  		}
					loadoperator(operatorid,x);
				});
	      		$("#bottom").off("scroll").on("scroll",function(){
					if (getScrollTop() + getClientHeight() == getScrollHeight()) { 
						j=j+10;
				    	//ajax从这里开始
				    	$.ajax({
							url:"getGoodsByOperator",
					      	type:"get",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data:{"limit":j,"phoneTypeID":brandid,"type":type},
					      	beforeSend: function () {
								
							},
					      	success:function(teldata){
					      		if(teldata.code===1){
					      			if(teldata.data.length!==0){
					      				for(var i = 0;i<teldata.data.length;i++){
							      			if(teldata.data.length%2===0){
							      				if(i%2==0){
							      					divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
													divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
													divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
								      			}else{
								      				divstr+='<div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
													divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
													divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}
							      			}else{
							      				if(i%2==0){
							      					if(i===teldata.data.length){
							      						divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
							      					}else{
							      						divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
							      					}
								      			}else{
								      					divstr+='<div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}
							      			}
						      		
							      		}
						      			$("#bottom").append(divstr);
						      			$(".container").on("click",function(){
											var id = $(this).attr("id");
											sessionStorage.setItem("goodid",id);
											window.location.href = 'toGoodsDetails';
										
										});
					      			}
					      			
					      		}
					      		
					      	},
					      	error:function(){
					           alert("操作失败");
					      	}
				    	});
			    	 
			    	}
				});
	       }
      	},
      	error:function(){
           alert("操作失败");
      	}
	});
}
//根据城市选择
function loadcity(city,type){
	//默认加载前十条数据
	$.ajax({
		url:"getGoodsByCity",
      	type:"post",
      	crossDomain:true,
      	async:true,
      	dataType:"json",
      	data:{"limit":0,"city":city,"type":type},
      	beforeSend: function () {
			
		},
      	success:function(phonedata){
      		var alldata;
	      	if(phonedata.code===1){
	      		//将品牌显示在页面
	      		var divstr = "";
	      		alldata = phonedata;
	      		if(phonedata.data.length!==0){
		      		for(var i = 0;i<phonedata.data.length;i++){
		      			if(phonedata.data.length%2===0){
		      				if(i%2==0){
		      					divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
			      			}else{
			      				divstr+='<div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
			      			}
		      			}else{
		      				if(i%2==0){
		      					if(i===phonedata.data.length){
		      						divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
		      					}else{
		      						divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
		      					}
			      			}else{
			      					divstr+='<div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
			      			}
		      			}
	      				
		      		}
		      		$("#bottom").empty();
		      		$("#bottom").append(divstr);
		      		$(".container").on("click",function(){
						var id = $(this).attr("id");
						sessionStorage.setItem("goodid",id);
						window.location.href = 'toGoodsDetails';
					
					});
				}else{
					$("#bottom").empty();
				}
	      		var j = 0;
				$("#telprice").off("click").on("click",function(){
					if(x==='0'){
			  			x="1";
			  		}else{
			  			x='0'
			  		}
					loadcity(city,x);
				});
	      		$("#bottom").off("scroll").on("scroll",function(){
					if (getScrollTop() + getClientHeight() == getScrollHeight()) { 
						j=j+10;
				    	//ajax从这里开始
				    	$.ajax({
							url:"getGoodsByCity",
					      	type:"post",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data:{"limit":j,"city":city,"type":type},
					      	beforeSend: function () {
								
							},
					      	success:function(teldata){
					      		if(teldata.code===1){
					      			if(teldata.data.length!==0){
					      				for(var i = 0;i<teldata.data.length;i++){
							      			if(teldata.data.length%2===0){
							      				if(i%2==0){
							      					divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
													divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
													divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}else{
								      				divstr+='<div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
													divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
													divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}
							      			}else{
							      				if(i%2==0){
							      					if(i===teldata.data.length){
							      						divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
							      					}else{
							      						divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
							      					}
								      			}else{
								      					divstr+='<div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}
							      			}
						      		
							      		}
						      			$("#bottom").append(divstr);
						      			$(".container").on("click",function(){
											var id = $(this).attr("id");
											sessionStorage.setItem("goodid",id);
											window.location.href = 'toGoodsDetails';
										
										});
					      			}
					      			
					      		}
					      		
					      	},
					      	error:function(){
					           alert("操作失败");
					      	}
				    	});
			    	 
			    	}
				});
	       }
      	},
      	error:function(){
           alert("操作失败");
      	}
	});
}
//根据城市和运营商同时选择
function bycityandoperator(mixarr,brandarr,type){
	
	//默认加载前十条数据
	$.ajax({
		url:"getGoodsByScreen",
      	type:"post",
      	crossDomain:true,
      	async:true,
      	dataType:"json",
      	data:{"limit":0,"cityAndotype":mixarr,phoneTypeID:brandarr,type:type},
      	beforeSend: function () {
			
		},
      	success:function(phonedata){
      		var alldata;
	      	if(phonedata.code===1){
	      		//将品牌显示在页面
	      		var divstr = "";
	      		alldata = phonedata;
	      		if(phonedata.data.length!==0){
		      		for(var i = 0;i<phonedata.data.length;i++){
		      			if(phonedata.data.length%2===0){
		      				if(i%2==0){
		      					divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
			      			}else{
			      				divstr+='<div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
								divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
								divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
			      			}
		      			}else{
		      				if(i%2==0){
		      					if(i===phonedata.data.length){
		      						divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
		      					}else{
		      						divstr+='<div class="bottom-one"><div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
		      					}
			      			}else{
			      					divstr+='<div class="container" id="'+phonedata.data[i].goodID+'"><div class="img"><img src="'+phonedata.data[i].goodImg+'"></div>';
									divstr+='<div class="name">'+phonedata.data[i].goodName+'</div>';
							    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+phonedata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+phonedata.data[i].supplier+'</div></div>';
									divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
			      			}
		      			}
	      				
		      		}
		      		$("#bottom").empty();
		      		$("#bottom").append(divstr);
		      		$(".container").on("click",function(){
						var id = $(this).attr("id");
						sessionStorage.setItem("goodid",id);
						window.location.href = 'toGoodsDetails';
					
					});
	      		}else{
	      			$("#bottom").empty();
	      		}
	      		var j = 0;
				$("#telprice").off("click").on("click",function(){
					if(x==='0'){
			  			x="1";
			  		}else{
			  			x='0'
			  		}
					bycityandoperator(mixarr,brandarr,x);
				});
	      		$("#bottom").off("scroll").on("scroll",function(){
					if (getScrollTop() + getClientHeight() == getScrollHeight()) { 
						j=j+10;
				    	//ajax从这里开始
				    	$.ajax({
							url:"getGoodsByScreen",
					      	type:"post",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data:{"limit":j,"cityAndotype":mixarr,phoneTypeID:brandarr,type:type},
					      	beforeSend: function () {
								
							},
					      	success:function(teldata){
					      		if(teldata.code===1){
					      			if(teldata.data.length!==0){
					      				for(var i = 0;i<teldata.data.length;i++){
							      			if(teldata.data.length%2===0){
							      				if(i%2==0){
							      					divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
													divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
													divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
								      			}else{
								      				divstr+='<div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
													divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
													divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}
							      			}else{
							      				if(i%2==0){
							      					if(i===teldata.data.length){
							      						divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
							      					}else{
							      						divstr+='<div class="bottom-one"><div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div>';
							      					}
								      			}else{
								      					divstr+='<div class="container" id="'+teldata.data[i].goodID+'"><div class="img"><img src="'+teldata.data[i].goodImg+'"></div>';
														divstr+='<div class="name">'+teldata.data[i].goodName+'</div>';
												    	divstr+='<div class="detail"><div><span>市场价</span><span class="cash">￥'+teldata.data[i].goodPrice+'</span><span class="num">.00</span></div><div class="theprice">'+teldata.data[i].supplier+'</div></div>';
														divstr+='<div class="sign"><span>0元</span><span>购机</span></div></div></div>';
								      			}
							      			}
						      		
							      		}
						      			$("#bottom").append(divstr);
						      			$(".container").on("click",function(){
											var id = $(this).attr("id");
											sessionStorage.setItem("goodid",id);
											window.location.href = 'toGoodsDetails';
										
										});
					      			}
					      			
					      		}
					      		
					      	},
					      	error:function(){
					           alert("操作失败");
					      	}
				    	});
			    	 
			    	}
				});
	       }
      	},
      	error:function(){
           alert("操作失败");
      	}
	});
}
function getLocation(){
	sessionStorage.setItem("address","1");
   var options={
       enableHighAccuracy:true, 
       maximumAge:1000
   }
   if(navigator.geolocation){
       //浏览器支持geolocation
       navigator.geolocation.getCurrentPosition(onSuccess,onError,options);
       
   }else{
       //浏览器不支持geolocation
       }
   }

   //成功时
    function onSuccess(position){
       //返回用户位置
	   //经度
	    
		AMap.plugin('AMap.Geocoder',function(){//回调函数
		    //实例化Geocoder
		    geocoder = new AMap.Geocoder({
		        city: "010"//城市，默认：“全国”
		    });
		    //TODO: 使用geocoder 对象完成相关功能
		})
		var longitude =position.coords.longitude+0.008774687519;
	   //纬度
	    var latitude = position.coords.latitude+0.00374531687912;
		var lnglatXY=[longitude, latitude];//地图上所标点的坐标
		geocoder.getAddress(lnglatXY, function(status, result) {
		    if (status === 'complete' && result.info === 'OK') {
		       //获得了有效的地址信息:
		       var address = result.regeocode.addressComponent.city;
		       address = address.replace("市","");
		       //将当前值绑定在表单中
		       $(".city").val(address);
		       
		    }else{
		       //获取地址失败
		    }
		});  
  


   }
  

           //失败时
   function onError(error){
       switch(error.code){
           case 1:
           alert("位置服务被拒绝");
	   break;
	
	   case 2:
	   alert("暂时获取不到位置信息");
	   break;
	
	   case 3:
	   alert("获取信息超时");
	   break;
	
	   case 4:
	    alert("未知错误");
           break;
       }

   }
       
    