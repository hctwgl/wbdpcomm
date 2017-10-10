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
		 //控制选择收货地址管理
		 $(".img").find("img").hide();
		 $(".first").find("img").show();
		 $(".first").attr("id","selected");
		 //
	    $(".img").click(function (e) {
			$(this).find("img").show();
			//增加一个id
			$(this).attr("id","selected");
			$(".img").not(this).find("img").hide();
			$(".img").not(this).attr("id","");
		});
		$("#sub").on("click",function(){
			//获取当前用户选择的收货地址
			alert($("#selected").siblings("div").find("span:nth-child(1)").text());
			alert($("#selected").siblings("div").find("span:nth-child(2)").text());
		});
    	
    });