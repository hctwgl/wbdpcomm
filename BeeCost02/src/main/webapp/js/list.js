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
		
		$(".img").find("img").hide();
		$(".first").find("img").show();
		$(".first").attr("id","selected");
	    $(".img").click(function (e) {
			$(this).find("img").show();
			$(".img").not(this).find("img").hide();
			//给被选择的对像添加一个id
			$(this).attr("id","selected");
		});
		//显示当前用户的购物车
		$.ajax({
			url:"getMineOrders",
	      	type:"get",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {},
	      	beforeSend: function () {
	      		
				
			},
	      	success:function(data){
		      	if(data.code===1){
		      		if(data.data.length === 0){
	      						$("#nogoods").show();
	      				}else{
	      					//购物车显示
				      		var divstr = '';
			      			for(var i = 0;i<data.data.length;i++){
			      				
			      				if(i===0){
				      				divstr+='<div class="popup-one" id="'+data.data[i].oID+'">';
									divstr+='<div class="image"><img src="'+data.data[i].goodImg+'" /></div><div class="detail">'
									divstr+='<div class="describe">'+data.data[i].goodName+'</div><div class="describe">'+data.data[i].color+'</div>';
									divstr+='<div class="gap1 param"><span>'+data.data[i].storage+'</span><span class="gap">'+data.data[i].color+'</span><span class="gap">'+data.data[i].supplier+'</span><span class="gap">'+data.data[i].wpackage+'</span></div>';
									divstr+='<div class="pay"><div></div><div><span class="cash">总价:￥'+data.data[i].goodPrice+'</span><span class="num out"></span></div></div></div></div>';
			      				}else{
			      					divstr+='<div class="popup-one gap1" id="'+data.data[i].oID+'">';
									divstr+='<div class="image"><img src="'+data.data[i].goodImg+'" /></div><div class="detail">'
									divstr+='<div class="describe">'+data.data[i].goodName+'</div><div class="describe">'+data.data[i].color+'</div>';
									divstr+='<div class="gap1 param"><span>'+data.data[i].storage+'</span><span class="gap">'+data.data[i].color+'</span><span class="gap">'+data.data[i].supplier+'</span><span class="gap">'+data.data[i].wpackage+'</span></div>';
									divstr+='<div class="pay"><div></div><div><span class="cash">总价:￥'+data.data[i].goodPrice+'</span><span class="num out"></span></div></div></div></div>';
			      				}
			      
			      			}
				      		$("#popup").html(divstr);
				      		$(".popup-one").on("click",function(){
				      			var id = $(this).attr("id");
				      			sessionStorage.setItem("oid",id);
				      			window.location.href = "toOrderListDetail";
				      		})
	      				}
		      		
		       	}else{
		       		
		       	}
		      
	      	},
	      	error:function(){
	      	   $("#loadingToast").hide();
	           alert("操作失败");
	      	}
    	});
    });