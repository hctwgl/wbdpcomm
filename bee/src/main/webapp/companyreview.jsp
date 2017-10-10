<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>公司信用审核</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
		<script src="js/jquery.min.js"></script>
	<link rel="apple-touch-icon" sizes="76x76" href="img/login_logo_top.png">
	<link rel="icon" type="image/png" sizes="96x96" href="img/web_icon.png">
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	<script src="myjs/customer.js"></script>
		<!--引入我的js与css文件-->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/common.css">
	<link rel="stylesheet" href="css/normalize.css">
	<link rel="stylesheet" href="css/pagination.css">
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.pagination.min.js"></script>
	<script type="text/javascript">
	//订单通过审核
	function approved(id,checkStatus){
		var msg = confirm("确认审核？")
		if(msg==true){
		var reason;
		var aliScore = $("#aliScore").html();
		if(aliScore==="暂无数据"){
			aliScore=0;
		}
		if(checkStatus==4){
			reason = prompt("请输入未通过理由");
			if(reason==null){
				return false;
			}
		}
		var grade = $("#grade").html();
			$.ajax({
				url:"companyYseOrNo",
		      	type:"post",
		      	crossDomain:true,
		      	async:true,
		      	dataType:"json",
		      	data: {id:id,checkStatus:checkStatus,grade:grade,checkReason:reason,aliScore:aliScore},
		      	success:function(data){
		      	   if(data===1){
		      	    alert("审核成功！");
		      	    location.href="listBeeCompany?pageNum=1";
		      	   }
		      	},
		      	error:function(){
		           alert("审核失败！！！");
		           return false;
		      	}
		    }); 
		}else{
			return false;
		}
		
	}
</script>
<script>
     	//图片旋转
        window.onload = function(){
         
            var current1 = 0;
            var current2 = 0;
          	var current3 = 0;
            document.getElementById('target1').onclick = function(){
             
                current1 = (current1+90)%360;
             
                this.style.transform = 'rotate('+current1+'deg)';
            }
            document.getElementById('target2').onclick = function(){
             
                current2 = (current2+90)%360;
             
                this.style.transform = 'rotate('+current2+'deg)';
            }
            document.getElementById('target3').onclick = function(){
             
                current3 = (current3+90)%360;
             
                this.style.transform = 'rotate('+current3+'deg)';
            }
        };
    </script>
</head>
<body>
					<h2 class="" style="margin-top:20px ; display: block; float: left;">公司信用审核</h2>
				<form class="navbar-form navbar-right">
					<div class="input-group" style="float: right;margin-right: 100px;">
						<!-- <input type="text" value="" class="form-control" placeholder="输入查询条件">
						<span class="input-group-btn"><button type="button" class="btn btn-primary" >查询</button></span> -->
					</div>
				</form>
					<div class="row">
							<div class="col-md-6" style="width: 100%;">
							<!-- BASIC TABLE -->
							<div class="panel">
								<!--<div class="panel-heading">
									<h3 class="panel-title">Basic Table</h3>
								</div>-->
								<div class="panel-body" style="margin-top: 20px;">
									<table class="table">
										<thead>
											<tr>
												<th>公司名称</th>
												<th>统一社会信用代码</th>
												<th>地址</th>
												<th>信用评分</th>
												<th>默认等级（点击可更改）</th>
											</tr>
										</thead>
										<tbody>
										   <td>${data.company}</td>
										   <td>${data.companyCode}</td>
										   <c:choose>
										   	<c:when test="${data.address==null}">
										   		<td>暂无数据</td>
										   	</c:when>
										   	<c:otherwise>
										   		 <td>${data.address}</td>
										   	</c:otherwise>
										   </c:choose>
										   <c:choose>
										   	<c:when test="${data.aliScore==null}">
										   		<td id="aliScore">暂无数据</td>
										   	</c:when>
										   	<c:otherwise>
										   		 <td id="aliScore">${data.aliScore}</td>
										   	</c:otherwise>
										   </c:choose>
										   <c:choose>
										   	<c:when test="${data.grade==null}">
										   		<td id="grade" contentEditable="true">暂无数据</td>
										   	</c:when>
										   	<c:otherwise>
										   		 <td id="grade" contentEditable="true">${data.grade}</td>
										   	</c:otherwise>
										   </c:choose>
										</tbody>
									</table>
									<img id ="target1" style="width: 600px;height: 600px;margin-left:10%" alt="" src="${data.licenseImg}">
									<img id ="target2" style="width: 600px;height: 600px;margin-left: 20px;margin-right: 20px" alt="" src="${data.contract}">
									    <div style="margin-left: 28%">
									<button onclick="approved('${data.id}',3)"  type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439;width: 300px;margin-top: 1%">通过审核</button>
									<button  onclick="approved('${data.id}',4)" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439;width: 300px;margin-top: 1%">未通过审核</button>
							</div>
							<!-- END BASIC TABLE -->
						</div>
					</div>
				</div>
			</div>
</body>

</html>
