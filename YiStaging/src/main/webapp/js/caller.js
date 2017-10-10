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
		//点击提交
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
			var relation = $("#relation").val();
   			if(relation!=""){
			}else{
					alert("请输入与联系人关系");
					return false;
			}
   			var tel = $("#tel").val();
   			var telreg=/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
   			if(tel!=""){
				if(!(telreg.test(tel))){
					alert("请输入正确的手机号码");
					return false;
				}
			}else{
					alert("手机号码不能那个为空");
					return false;
			}
   			$.ajax({
				url:"saveContacts",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"name":name,"relationship":relation,"phone":tel},
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
			       	}else{
			       		alert("录入失败");
			       	}
			      	
		      	},
		      	error:function(){
		      	   $("#loadingToast").hide();
		           alert("操作失败");
		      	}
	    	});
   		});
    	
    });