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
    var data,stageid,nowdata;
    $(function(){
    	//控制同意协议
    	//选择控制
		$(".img1 img").hide();
		$(".img1").on("click",function(){
			$(this).find("img").toggle();
		});
    	//获取session里面选中的值
    	goodid = sessionStorage.getItem("goodid");
    	colorid = sessionStorage.getItem("colorid");
    	ramid = sessionStorage.getItem("ramid");
    	wpackage = sessionStorage.getItem("wpackage");
    	supid = sessionStorage.getItem("supid");
    	pid = sessionStorage.getItem("packageid");
    	price = JSON.parse(sessionStorage.getItem("price"));
    	//获取当前选中的内容
    	$.ajax({
			url:"getGoodsInfoInPay",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {"goodID":Number(goodid),"ctype":Number(colorid),"stype":Number(ramid),"pID":Number(pid),"supID":Number(supid),"wpackage":wpackage},
	      	beforeSend: function () {
			},
	      	success:function(gooddata){
		      	if(gooddata.code===1){
		      		nowdata = gooddata.data;
		      		//蒋当条订单显示出来
		      		$("#img").attr("src",gooddata.data.goodImg);
		      		$("#name").html(gooddata.data.goodName);
		      		$("#color1").html(gooddata.data.cType);
		      		$("#color2").html("颜色:"+gooddata.data.cType);
		      		$("#ram").html("存储:"+gooddata.data.sType);
		      		$("#operator").html("运营商:"+gooddata.data.oType);
		      		$("#package").html("套餐:"+wpackage);
		      		$("#allprice").html("￥"+gooddata.data.goodPrice);
		      		//花粉信用
		      		if(gooddata.data.quota===-1){
						$("#quota").html("当前花粉信用可抵扣0元");
		      		}else if(gooddata.data.quota===-2){
						$("#quota").html("当前花粉信用可抵扣0元");
		      		}else{
			      		$("#quota").html("当前花粉信用可抵扣"+gooddata.data.quota+"元");
		      		}
		      			//根据总价和花粉信用大小判断实际付款
		      		if(gooddata.data.goodPrice>gooddata.data.quota){
		      			$("#actual").html("￥"+(gooddata.data.goodPrice-gooddata.data.quota));
		      		}else{
		      			$("#actual").html("￥0");
		      		}
		       	}else{
		       		
		       	}
		      
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
		//提交当前订单
		$("#payout").on("click",function(){
			if($(".img1 img").is(":hidden")){
				alert("请勾选贷款协议");
				return false;
			}
			if(nowdata.quota==-2){
				alert("您还没获取花粉信用");
			}else{
				if(nowdata.quota==-1){
					alert("您账户已被拉黑");
				}else{
					if(nowdata.quota>price){
						//获取用户选中的值
						var salesMancode = $("#salesMancode").val();
						if(salesMancode===""){
							alert("推荐码不能为空");
							return false;
						}
						$.ajax({
							url:"addUserOrder",
					      	type:"get",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data: {"goodsID":nowdata.goodID,"pollenID":nowdata.pollenID,"quota":nowdata.quota,"goodsMoney":price,
					      		"skuID":nowdata.skuID,"pID":nowdata.pID,"salesMancode":salesMancode},
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
							            	sessionStorage.setItem("type","1");
							      			window.location.href="toface";
							      		},1000);
						       	}else if(data.code===44){
						       		alert("您账户已被拉黑");
						       	}else{
						       		alert("提交订单失败");
						       	}
						      
					      	},
					      	error:function(){
					      	   $("#loadingToast").hide();
					           alert("操作失败");
					      	}
				    	});
					}else{
						alert("花粉信用不足无法提交");
					}
				}
			}
		
		});
    });