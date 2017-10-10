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
		//隐藏选择按钮
		$(".img img").hide();
		$(".img").on("click",function(){
			$(this).find("img").toggle();
		});
		//配送方式控制
		$(".pick").on("click",function(){
			$("#pick").show();
			$("#express").hide();
		});
		$(".express").on("click",function(){
			$("#pick").hide();
			$("#express").show();
		});
		//控制选择
		$(".type span").on("click",function(){
			$(this).addClass("selected");
			$(this).siblings("span").removeClass("selected");
		})
		//控制弹出层
		$("#bottom span").on("click",function(){
			$("#popup").show();

		})
    	
    	
    });