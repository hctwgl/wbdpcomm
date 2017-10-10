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
   //获取session里面的数据
   var name = sessionStorage.getItem("name"),
   tel = sessionStorage.getItem("tel"),
   address = sessionStorage.getItem("address"),
   addressdetail = sessionStorage.getItem("addressdetail");
   $("#name").html(name);
   $("#tel").html(tel);
   $("#address").html(address+addressdetail);	
});
