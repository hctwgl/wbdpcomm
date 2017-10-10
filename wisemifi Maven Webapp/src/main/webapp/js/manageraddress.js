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
	//获取session里面的值
	var pagetype = sessionStorage.getItem("pagetype");
	var alldata;
	if(pagetype){
		//确定
		$("#sub").html("提交");
		//说明是已录取过资料 获取其资料
		$.ajax({
			url:"selectCash",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
				
				},
	      	success:function(data){
	      	if(data.status==="SUCCESS"){
				//给表单赋值
				alldata = data.data;
				$("#name").val(data.data.name);
				$("#sex").val(data.data.sex);
				$("#tel").val(data.data.phone);
				var address = data.data.province+"-"+data.data.city+"-"+data.data.area;
				$("#address").val(address);
	      		$("#addressdetail").val(data.data.deltaiaddress);
				$("#card").val(data.data.card);       
	       	}else{
	       		alert("数据异常");
	       	}
	      	
	      	},
	      	error:function(){
	           alert("操作失败");
	      	}
	    });
	}else{
		
	}
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
		var sex =  $("#sex").val();
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
		var addressprovince = address.split("-")[0];
		var addresscity = address.split("-")[1];
		var addressarea = address.split("-")[2];
		var addressdetail = $("#addressdetail").val();
		if(address!=""){
		}else{
			alert("地址不能为空");
			return false;
		}
		if(addressdetail!=""){
		}else{
			alert("地址不能为空");
			return false;
		}
		var card = $("#card").val();
		var cardreg=/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		if(card!=""){
			if(pagetype){
				if(card===alldata.card){
					card = '';
				}else{
					if(!(cardreg.test(card))){
						alert("请输入正确的身份证号");
						return false;
					}
				}
			}else{
				if(!(cardreg.test(card))){
					alert("请输入正确的身份证号");
					return false;
					}
			}
		
			
		}else{
				alert("身份证不能为空");
				return false;
		}
	if(pagetype){
				//修改
		//将获取的值保存到后台
		$.ajax({
			url:"updateCashLoan",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {"id":alldata.id,"name":name,"phone":tel,"delaiaddress":addressdetail,"city":addresscity,"area":addressarea,"province":addressprovince,"sex":sex,"card":card},
	      	beforeSend: function () {
				//防止重复提交弹出loading窗口
				$("#loadingToast").show();
				},
	      	success:function(data){
	      		//返回成功关闭loading
				$("#loadingToast").hide();
	      	if(data.status==="SUCCESS"){
	      		//自动弹出完成窗
				$("#toast").show();
				//自动关闭弹出完成窗
	      		setTimeout(function(){
	      			$("#toast").hide();
	      			
	      		},1000);
	      		setTimeout(function(){
				    //跳转到card界面
		        	window.location.href="toloan";
	      		},1000);
		          
	       	}else{
	       		alert('数据异常');
	       	}
	      	
	      	},
	      	error:function(){
	      		$("#loadingToast").hide();
	           alert("操作失败");
	      	}
	    });
	}else{
				//新增
				//将获取的值保存到后台
			$.ajax({
				url:"saveCashLoan",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"name":name,"phone":tel,"delaiaddress":addressdetail,"city":addresscity,"area":addressarea,"province":addressprovince,"sex":sex,"card":card},
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
		      		setTimeout(function(){
					    //跳转到card界面
			        	window.location.href = "tocaller";
		      		},1000);
			          
		       	}else{
		       		alert('新增失败');
		       	}
		      	
		      	},
		      	error:function(){
		      		$("#loadingToast").hide();
		           alert("操作失败");
		      	}
		    });
		}
		
   });
	
})
