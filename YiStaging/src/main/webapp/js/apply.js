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
    	 //初始化省市区选择
	    $("#companyaddress").click(function (e) {
			SelCity(this,e);
		});
		//禁止弹出手机键盘
		$("#companyaddress").focus(function(){
		    document.activeElement.blur();
		});
		$("#familyaddress").click(function (e) {
			SelCity(this,e);
		});
		//禁止弹出手机键盘
		$("#familyaddress").focus(function(){
	        document.activeElement.blur();
	    });
    	//点击注册
   		$("#sub").on("click",function(){
   			//获取用户输入的值
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
   			/*var sex = $("#sex").val();*/
   			var idcard = $("#idcard").val();
   			var cardreg=/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
   			if(idcard!=""){
				if(!(cardreg.test(idcard))){
					alert("请输入正确的身份证号");
					return false;
				}
			}else{
					alert("身份证不能为空");
					return false;
			}
   			/*var marry = $("#marry").val();*/
   			var company = $("#company").val();
   			if(company!=""){
			}else{
				alert("公司名称不能为空");
				return false;
			}
   			var companyaddress = $("#companyaddress").val();
   			var companyprovince = companyaddress.split("-")[0];
   			var companycity = companyaddress.split("-")[1];
   			var companyarea = companyaddress.split("-")[2];
   			var companydetail = $("#companydetail").val();
   			if(companyaddress!=""){
			}else{
				alert("公司地址不能为空");
				return false;
			}
			if(companydetail!=""){
			}else{
				alert("公司详细地址不能为空");
				return false;
			}
   			/*var familyaddress = $("#familyaddress").val();
   			var familyprovince = familyaddress.split("-")[0];
   			var familycity = familyaddress.split("-")[1];
   			var familyarea = familyaddress.split("-")[2];
   			var familydetail = $("#familydetail").val();
   			if(familyaddress!=""){
			}else{
				alert("家庭地址不能为空");
				return false;
			}
			if(familydetail!=""){
			}else{
				alert("家庭详细地址不能为空");
				return false;
			}*/
   		/*	var delivery = $("#delivery").val();
   			if(delivery!=""){
			}else{
				alert("收货地址不能为空");
				return false;
			}*/
   			/*var profess = $("#profess").val();*/
   			
   			$.ajax({
				url:"saveUserBaseInfo",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"name":name,"IDNum":idcard,"companyName":company,
		      	"companyProvince":companyprovince,"companyCity":companycity,"companyArea":companyarea,"companyAddress":companydetail},
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
			      			window.location.href="toCard";
			      			
			      		},1000);
			       	}
			      	else if(data.code===2){
			      		alert("身份证与姓名不一致");
			      	}else{
			       		alert("身份证与姓名不一致");
			       	}
			      
		      	},
		      	error:function(){
		      	   $("#loadingToast").hide();
		           alert("操作失败");
		      	}
	    	});
   		});	
    });