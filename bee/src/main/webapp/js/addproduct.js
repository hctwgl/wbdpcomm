$(function(){
    //获取session里面的值
    var data = JSON.parse(sessionStorage.getItem("productdata"));
    var id = sessionStorage.getItem("productid");
    var type;
    var productid;
    var imgsrc;
    //移除本地存储的值
    sessionStorage.removeItem("productdata");
    sessionStorage.removeItem("productid");
    //根据session判断是修改还是增加
    if(data===undefined||data===null){
    	//增加
    	$(".content-top-right").html("新增产品");
    	type = 1;
    	productid = "";
    }else{
    	$(".content-top-right").html("修改产品");
    	type = 2;
    	//修改跟表单赋值
    	productid = data.resultList[id].id;
    	$("#productmodel").val(data.resultList[id].productmodel);
		$("#productname").val(data.resultList[id].productname);
		$("#producttype").val(data.resultList[id].producttype);
		$("#supplier").val(data.resultList[id].supplier);
		$("#protocolcode").val(data.resultList[id].protocolcode);
		$("#productdesc").val(data.resultList[id].productdesc);
		$("#filecontainer").attr("src",data.resultList[id].productpic);
		
		
    }
    //点击上传图片并显示出来
    $("#upload").on("click",function(){
    	if(data===null&&$("#file").val()===""){
    		alert("请先选择文件")
    	}else{
    		//先提交表单上传图片并显示出来
	    	$("#imgform").ajaxSubmit({
	    		url:"picUpload",
		      	type:"post",
		      	success:function(dat){
		      		$("#filecontainer").attr("src",dat);
		      		imgsrc = dat;
		      	},
		      	error:function(){
		           alert("获取数据失败");
		      	}
	    		
	    	});

    	}
    	    	
    });
	//点击确定
	$("#sure").on("click",function(){
		//获取用户输入的值
		var productmodel = $("#productmodel").val();
		var productname= $("#productname").val();
		var producttype = $("#producttype").val();
		var supplier = $("#supplier").val();
		var protocolcode = $("#protocolcode").val();
		var productdesc = $("#productdesc").val();
		
		//调取新增车辆接口
		$.ajax({
			url:"insertProduct",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {"id":productid,"type":type,"productmodel":productmodel,"productname":productname,"producttype":producttype,"supplier":supplier,"protocolcode":protocolcode,"productdesc":productdesc,"productpic":imgsrc},
	      	beforeSend: function () {
			//防止重复提交弹出loading窗口
	        				
	    	},
	      	success:function(data){
	      		alert("提交成功");
	      		$("#content").load("product");
	      	},
	      	error:function(){
	           alert("获取数据失败");
	      	}
    	});
	});
  
});