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
    	var alldata;
    	 //初始化省市区选择
	    $("#companyaddress").click(function (e) {
			SelCity(this,e);
		});
		$("#familyaddress").click(function (e) {
			SelCity(this,e);
		});
		//获取我的基本资料
		$.ajax({
			url:"getUserInfs",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
			},
	      	success:function(data){
		      	if(data.code===1){
		      		alldata = data.data;
		      		var mydata = data.data;
		      		//给表单赋值
		      		$("#name").val(mydata.name);
		      		/*$("#sex").val(mydata.gender);*/
		      		$("#idcard").val(mydata.idnum);
		      		$("#company").val(mydata.companyName);
		      		var companyaddress = mydata.companyProvince+"-"+mydata.companyCity+"-"+mydata.companyArea;
		      		$("#companyaddress").val(companyaddress);
		      		$("#companydetail").val(mydata.companyAddress);
		      		/*var familyaddress = mydata.homeProvince+"-"+mydata.homeCity+"-"+mydata.homeArea;
		      		$("#familyaddress").val(familyaddress);
		      		$("#familydetail").val(mydata.homeAddress);*/
		      		/*$("#marry").val(mydata.maritalStatus);
		      		$("#profess").val(mydata.education);*/
		       	}else{
		       		
		       	}
		    
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
    	//点击保存
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
   			var idcard;
   			if($("#idcard").val()===alldata.idnum){
   				 idcard = "";
   			}else{
   				 idcard = $("#idcard").val();
   				 if(idcard!=""){
			
				}else{
					alert("身份证不能为空");
					return false;
				}
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
				url:"updateUserInfo",
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
				        //跳回我的界面
				        setTimeout(function(){
			      			window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftomine&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			      			
			      		},1000);
			       	}else if(data.code===2){
			      		alert("身份证与姓名不一致");
			      	}else{
			       		alert("提交失败");
			       	}
			      
		      	},
		      	error:function(){
		      	   $("#loadingToast").hide();
		           alert("操作失败");
		      	}
	    	});
   		})
    	
    	
    });