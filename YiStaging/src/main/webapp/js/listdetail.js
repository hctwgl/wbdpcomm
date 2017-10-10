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
		
		//获取session的id
		var oid = Number(sessionStorage.getItem("oid"));
		//显示当前用户的购物车
		$.ajax({
			url:"getOrderListDetail",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {"oid":oid},
	      	beforeSend: function () {
	      		
				
			},
	      	success:function(data){
		      	if(data.code===1){
		      		$("#img").attr("src",data.data.goodImg);
		      		$("#name").html(data.data.goodName);
		      		$("#color").html(data.data.color);
		      		$("#time").html(data.data.orderDate);
		      		$("#allprice").html("总价:￥"+data.data.pacPrice);
		      		$("#color1").html("颜色:"+data.data.color);
		      		$("#ram").html("存储:"+data.data.storage);
		      		$("#operator").html("运营商:"+data.data.supplier);
		      		$("#package").html("套餐:"+data.data.wpackage);
		      		$("#packagedetail").html("套餐详情:"+data.data.details);
		      		
		      		
		       	}else{
		       		
		       	}
		      
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
    });