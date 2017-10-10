$(function(){
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
   //初始化时间选择控件
   //初始化省市区选择
    $("#address").click(function (e) {
		SelCity(this,e);
	});
	//禁止弹出手机键盘
	$("#address").focus(function(){
        document.activeElement.blur();
   });
   //获取二次购买的收货地址
   $.ajax({
		url:"getAddress",
	    type: 'post',
		dataType: 'json',
		data: {},
		complete: function(XMLHttpRequest,textStatus){},
		error: function(XMLHttpRequest,textStatus,errorThrown){
			alert("发生错误："+errorThrown);
		},
		success: function(data){
			var result = data.data;
			if(data.length!==0){
				//说明是二次购买
				$("#name").val(result.customerName);
				$("#tel").val(result.phone);
				var address = result.address;
				address = address.split("+");
				$("#address").val(address[0]);
				$("#addressdetail").val(address[1]);
				
			}
	    }
	});
   //保存当前用户
    $("#bottom").on("click",function(){
		var name = $("#name").val();
		var namereg=/^[\u4E00-\u9FA5]{2,4}$/
		if(name!=""){
			if(!(namereg.test(name))){
				alert("请输入汉字");
				return false;
			}
		}else{
			alert("姓名不能为空");
			return false;
		}
		var tel = $("#tel").val();
		var telreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(tel!=""){
			if(!telreg.test(tel)){
				alert("手机号码格式不正确");
				return false;
			}
		}else{
			alert("手机号码不能为空");
			return false;
		}
		
		var address = $("#address").val();
		var addressdetail = $("#addressdetail").val();
		if(address!=""){
		}else{
			alert("地址不能为空");
			return false;
		}
		if(addressdetail!=""){
		}else{
			alert("详细地址不能为空");
			return false;
		}
		//将当前信息保存在session中
		sessionStorage.setItem("name",name);
		sessionStorage.setItem("tel",tel);
		sessionStorage.setItem("address",address);
		sessionStorage.setItem("addressdetail",addressdetail);
		window.location.href = 'toorder';
	});
	
});
