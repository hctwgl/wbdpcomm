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
    	//获取我的二维码数据
    	$.ajax({
			url:"getQRCode",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
				
			},
	      	success:function(data){
		      	if(data.status==="SUCCESS"){
		      		if(data.type===0){
		      			$(".img").show();
		      			$("#all").removeClass("all");
		      			$("#all").addClass("noall");
		      			$(".title").hide();
		      			$(".container").hide();
		      			$("#sub").hide();
		      		}else{
		      			var divstr = '';
		      			result = data.data;
		      			alldata = data.data;
		      			if(result.length===0){
		      				//当没有二维码时
		      				$("#container").removeClass("container");
		      				$("#container").addClass("nocode");
		      				divstr+='<div class="code-container no"><div class="code-title">点击新增套餐</div><div class="code-img add">';
		      				divstr+='<img src="img/mine_icon_add.png" /></div></div>';
		      			}else{
		      				$("#container").removeClass("nocode");
		      				$("#container").addClass("container");
		      				for(var i = 0;i<result.length;i++){
		      					if(i===0){
		      						//当二维码为第一条时
		      						divstr+='<div class="code"><div class="code-container no"><div class="code-title">点击新增套餐</div><div class="code-img add">';
		      						divstr+='<img src="img/mine_icon_add.png" /></div></div>';
		      						divstr+='<div id='+i+' class="code-container"><div class="code-title">'+result[i].pacMonthlyPrice+'*'+result[i].pacPeriods+'='+result[i].pacPrice+'</div>';
		      						divstr+='<div class="code-img"><img src="'+result[i].imgUrl+'" /></div></div></div>';
		      					}else{
		      						if(result.length%2===0){
		      							//当结果数组长度为偶数时
					      				if(i%2==0){
					      					//当下标为偶数项时
					      					divstr+='<div id='+i+' class="code-container"><div class="code-title">'+result[i].pacMonthlyPrice+'*'+result[i].pacPeriods+'='+result[i].pacPrice+'</div>';
		      								divstr+='<div class="code-img"><img src="'+result[i].imgUrl+'" /></div></div></div>';
						      			}else{
						      				//当下标为奇数数项时
						      				if(i===result.length-1){
						      					//最后一项
					      						divstr+='<div class="code"><div id='+i+' class="code-container"><div class="code-title">'+result[i].pacMonthlyPrice+'*'+result[i].pacPeriods+'='+result[i].pacPrice+'</div>';
		      								    divstr+='<div class="code-img "><img src="'+result[i].imgUrl+'" /></div></div></div>';
					      					}else{
					      						divstr+='<div class="code"><div id='+i+' class="code-container"><div class="code-title">'+result[i].pacMonthlyPrice+'*'+result[i].pacPeriods+'='+result[i].pacPrice+'</div>';
		      									divstr+='<div class="code-img "><img src="'+result[i].imgUrl+'" /></div></div>';
					      					}
						      			}
					      			}else{
					      				//当结果数组长度为奇数时
					      				if(i%2==0){
					      					divstr+='<div id='+i+' class="code-container"><div class="code-title">'+result[i].pacMonthlyPrice+'*'+result[i].pacPeriods+'='+result[i].pacPrice+'</div>';
		      								divstr+='<div class="code-img"><img src="'+result[i].imgUrl+'" /></div></div></div>';
						      			}else{
						      				divstr+='<div class="code"><div id='+i+' class="code-container"><div class="code-title">'+result[i].pacMonthlyPrice+'*'+result[i].pacPeriods+'='+result[i].pacPrice+'</div>';
		      								divstr+='<div class="code-img "><img src="'+result[i].imgUrl+'" /></div></div>';
						      			}
					      				
					      			}
		      					}
	      						
		      					
		      				}
		      				
		      			}
		      			$("#container").html(divstr);
		      			//点击添加
		      			$(".add").on("click",function(){
		      				window.location.href = 'toaddcode';
		      			});
		      			//点击选择
				    	$(".code-container").not(".no").on("click",function(){
				    		$(this).addClass("bord");
				    		$(this).children(".code-title").addClass("selected");
				    		$(".code-container").not(this).removeClass("bord");
				    		$(".code-container").not(this).children(".code-title").removeClass("selected");
				    	});
		      		}
		      	}else{
		      		alert(data.msg);
		      	}
		      	
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
		//点击提交
   		$("#sub span").on("click",function(){
   			var id = $(this).attr("id"),
   			//获取当前选中的信息
			selectednode = $(".code-container").not(".no"),
			selectedid;
			for(var j=0;j<selectednode.length;j++){
				if(selectednode[j].className==="code-container bord"){
					//先清空显示内容
					selectedid = alldata[selectednode[j].id].id;
					codeid = selectednode[j].id;
				}
			}
			if(selectedid){
				
			}else{
				//说明没有选中操作项
				alert("请选择要操作的项");
				return false;
			}
   			if(id==="del"){
   				//说明是删除
   				  var r=confirm("确定删除选中的二维码")
				  if (r==true)
				    {
				    	$.ajax({
							url:"delQRCode",
					      	type:"post",
					      	crossDomain:true,
					      	async:true,
					      	dataType:"json",
					      	data: {"id":selectedid},
					      	beforeSend: function () {
							},
					      	success:function(data){
						      	if(data.status==="SUCCESS"){
						      		alert("删除成功");
						      		location.reload();
						       	}else{
						       		alert(data.msg);
						       	}
						    
					      	},
					      	error:function(){
					      	   $("#loadingToast").hide();
					           alert("操作失败");
					      	}
				    	});
				    }
				  else
				    {
				    
				    }
   			}else{
   				
   				sessionStorage.setItem("alldata",JSON.stringify(alldata));
   				sessionStorage.setItem("codeid",codeid);
   				//跳转到二维码详情
   				window.location.href = "toORCodeDetil";
   			}
   			
   		});
    	
    });