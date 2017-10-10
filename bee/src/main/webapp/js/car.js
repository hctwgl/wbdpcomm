$(function(){
    //获取页面数据
    var pagesize = "";//当前页面总数
    var alldata;
    //获取车主数据
    $.ajax({
		url:"serchVehiceList",
      	type:"post",
      	crossDomain:true,
      	async:true,
      	dataType:"json",
      	data: {},
      	success:function(data){
      		alldata = data;
      		//将新生成的数据绑定到表格中
      		pagesize = data.pageSize;
      		$("tbody").html("");
      		for(var i= 0;i<data.resultList.length;i++){
      			var x = add0(parseInt(i)+1);
      			var tablestr = '<tr><td>'+x+'</td><td>'+data.resultList[i].platenumber+'</td><td>'+data.resultList[i].vin+'</td><td>'+data.resultList[i].enginenumber+'</td>';
      			if(data.resultList[i].gpsstate===0){
      				tablestr+='<td>未授权</td>';
      			}else{
      				tablestr+='<td>已授权</td>';
      			}
      			tablestr+='<td id="'+i+'" class="update">修改资料</td></tr>';
      			$("tbody").append(tablestr);
      			//修改资料绑定事件
				$("#"+i+"").on("click",function(){
					//将当前获取的数据放入session中
					var data = JSON.stringify(alldata);
					sessionStorage.setItem("cardata",data);
					//获取当前点击的id并将其放入session中
					var customid = $(this).attr("id");
					sessionStorage.setItem("customid",customid);
					$("#content").load("addcar");
					
				});
      		}
      		initpage(pagesize);
      	
      	},
      	error:function(){
           
      	}
    });
    
    //新增车辆
    $(".content-bottom-left").on("click",function(){
   		$("#content").load("addcar");
   });
   //模糊查询
    $(".sericon").on("click",function(){
   		//获取模糊查询的条件
   		var parmeter =  $("#no").val();//返回当前页数
			$.ajax({
				url:"serchVehiceList",
		      	type:"POST",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"parmeter":parmeter},
		      	success:function(data){
		      		alldata = data;
		      		pagesize = data.pageSize;
		      		//清空table
		      		$("tbody").html("");
		      		//生成当页面数据
		      		for(var i= 0;i<data.resultList.length;i++){
		      			var x = add0(parseInt(i)+1);
      					var x = add0(parseInt(i)+1);
		      			var tablestr = '<tr><td>'+x+'</td><td>'+data.resultList[i].platenumber+'</td><td>'+data.resultList[i].vin+'</td><td>'+data.resultList[i].enginenumber+'</td>';
		      			if(data.resultList[i].gpsstate===0){
		      				tablestr+='<td>未授权</td>';
		      			}else{
		      				tablestr+='<td>已授权</td>';
		      			}
		      			tablestr+='<td id="'+i+'" class="update">修改资料</td></tr>';
      					$("tbody").append(tablestr);
      					//修改资料绑定事件
      					$("#"+i+"").on("click",function(){
      						//将当前获取的数据放入session中
      						var data = JSON.stringify(alldata);
      						sessionStorage.setItem("cardata",data);
      						//获取当前点击的id并将其放入session中
      						var customid = $(this).attr("id");
      						sessionStorage.setItem("customid",customid);
      						$("#content").load("addcar");
      					});
      				}
		      		initpage(pagesize);
		      	},
		      	error:function(){
		         
		      	}
    		});
   });
   
});
function initpage(pagesize){
	var parmeter =  $("#no").val();//返回当前页数
	//初始化分页工具
    $('.M-box3').pagination({
		pageCount:pagesize,//总页数
        jump:true,
        coping:true,
        homePage:'首页',
        endPage:'末页',
        prevContent:'上页',
        nextContent:'下页',
		callback:function(index){//点击回调函数
			//返回当前页数
			$.ajax({
				url:"serchVehiceList",
		      	type:"POST",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {"pageNum":index.getCurrent(),"parmeter":parmeter},
		      	success:function(data){
		      		alldata = data;
		      		//清空table
		      		$("tbody").html("");
		      		//生成当页面数据
		      		for(var i= 0;i<data.resultList.length;i++){
		      			var x = add0(parseInt(i)+1+12*(parseInt(index.getCurrent())-1));
      					var tablestr = '<tr><td>'+x+'</td><td>'+data.resultList[i].platenumber+'</td><td>'+data.resultList[i].vin+'</td><td>'+data.resultList[i].enginenumber+'</td>';
		      			if(data.resultList[i].gpsstate===0){
		      				tablestr+='<td>未授权</td>';
		      			}else{
		      				tablestr+='<td>已授权</td>';
		      			}
		      			tablestr+='<td id="'+i+'" class="update">修改资料</td></tr>';
      					$("tbody").append(tablestr);
      					//修改资料绑定事件
      					$("#"+i+"").on("click",function(){
      						//将当前获取的数据放入session中
      						var data = JSON.stringify(alldata);
      						sessionStorage.setItem("cardata",data);
      						//获取当前点击的id并将其放入session中
      						var customid = $(this).attr("id");
      						sessionStorage.setItem("customid",customid);
      						$("#content").load("addcar");
      					});
      				}
		      	},
		      	error:function(){
		          
		      	}
    		});
		}
    });
}