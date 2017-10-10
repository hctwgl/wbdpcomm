<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="zh-CN">

<head>
	<title>任性刷管理</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="${basePath }assets/vendor/font-awesome/css/font-awesome.min.css">
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

	//上传征信
	function upload(id,money){
		  $.ajax({
			url:"",
	      	type:"post",
	      	crossDomain:true,
	      	async:true,
	      	dataType:"json",
	      	data: {beeid:id,money:money},
	      	success:function(data){
	      	if(data.data===1){
	      	alert("上传成功");
	      		window.location.href="beeList?pageNum=1";
	      	}else{
	      	alert("拉黑失败，请仔细检查您提交的资料")
	      	}	
	      	},
	      	error:function(){
	           alert("访问失败，请仔细检查您提交的资料!!!");
	           return false;
	      	}
	    }); 
		
	}
</script>
</head>

<body>
					<h2 class="" style="margin-top:20px ; display: block; float: left;">任性刷管理</h2>
				<form class="navbar-form navbar-right">
					<div class="input-group" style="float: right;margin-right: 100px;">
						<!-- <input type="text" value="" class="form-control" placeholder="输入查询条件">
						<span class="input-group-btn"><button type="button" class="btn btn-primary" >查询</button></span> -->
						<a href="brushListThisMouth?pageNum=1" class="btn btn-primary" >本月</a>&nbsp
						<a href="brushListLastMouth?pageNum=1" class="btn btn-primary" >上月</a>
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
												<th>编号</th>
												<th>客户</th>
												<th>期数（月）</th>
												<th>每月金额（元）</th>
												<th>总金额</th>
												<th>手机型号</th>
												<th>推荐码</th>
												<th>状态</th>
												<th>时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										  <c:forEach var="item" items="${data}" begin="0" end="${fn:length(data)}" varStatus="status">
										  <tr>
											<td>${status.index+1}</td>
												<c:choose>
													<c:when test="${item.BeeName==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.BeeName}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.PacPeriods==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.PacPeriods}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.PacMonthlyPrice==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.PacMonthlyPrice}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.PacMonthlyPrice*item.PacPeriods==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.PacMonthlyPrice*item.PacPeriods}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.PhoneModel==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.PhoneModel}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.RecomCode==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.RecomCode}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.FaceStatus==null}">
														<td>暂无数据</td>
													</c:when>
													<c:when test="${item.FaceStatus==0}">
														<td>未通过</td>
													</c:when>
													<c:when test="${item.FaceStatus==1}">
														<td>已通过</td>
													</c:when>
													<c:when test="${item.FaceStatus==2}">
														<td>等待后台审核</td>
													</c:when>
													<c:when test="${item.FaceStatus==3}">
														<td>等待用户确认</td>
													</c:when>
													<c:otherwise>
														<td>${item.RecomCode}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.BrushTime==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.BrushTime}</td>
													</c:otherwise>
												</c:choose>
												<%-- <c:choose>
													<c:when test="${item.bee.phone==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.bee.phone}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.orderDate==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.orderDate}</td>
													</c:otherwise>
												</c:choose>
												
												<td>
													${item.goods.goodsname }
												</td>
												<c:choose>
													<c:when test="${item.money==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.money}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.periods==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.periods}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.salesManCode==null}">
														<td>暂无数据</td>
													</c:when>
													<c:otherwise>
														<td>${item.salesManCode}</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${item.orderState==null}">
														<td>暂无数据</td>
													</c:when>
													<c:when test="${item.orderState==0}">
														<td>未审核</td>
													</c:when>
													<c:when test="${item.orderState==1}">
														<td>已通过</td>
													</c:when>
													<c:when test="${item.orderState==2}">
														<td>审核未通过</td>
													</c:when>
												</c:choose>--%>
												<c:choose>
												<c:when test="${sessionScope.userType==2&&item.FaceStatus==2}">
													<%-- <c:when test="${item.FaceStatus==2}"> --%>
														<td><a href="brushReview?brushid=${item.brushID}&beeWX=${item.BeeWX}" type="button" class="btn btn-primary btn-xs" style="background-color:#449d44; border:1px solid #398439">审核</a></td>
													<%-- </c:when> --%>
												</c:when>
												<c:otherwise>
													<td></td>
												</c:otherwise>
												</c:choose> 
												</tr>
										  </c:forEach> 
										</tbody>
									</table>
								</div>
							</div>
							<!-- END BASIC TABLE -->
						</div>
					</div>
					<div class="row">
						<div>
							<!-- BASIC TABLE -->
							<div style="text-align: center;margin: 0 auto;">
					<!--分页组件  -->
					<!-- <div class="M-box"></div> -->
					<!--分页组件-->
					
						<nav aria-label="Page navigation" style="text-align: center;margin: 0 auto;">
						  <ul class="pagination" style="text-align: center;margin-right: 10%">
						<c:choose>
								<c:when test="${sessionScope.pageNowThisMouth==null&&sessionScope.pageNowLastMouth==null}">
								    <c:choose>
								    	<c:when test="${sessionScope.pageNow==1}">
								    		
								    	</c:when>
								    	<c:when test="${sessionScope.pageNow!=1}">
								    		<li>
									    		<a href="brushList?pageNum=${sessionScope.pageNow-1}" aria-label="Previous">
									        		<span aria-hidden="true">&laquo;</span>
									      		</a>
								      		</li>
								    	</c:when>
								    </c:choose>
						    	</c:when>
						    	<c:when test="${sessionScope.pageNowThisMouth==1}">
						    		
						    	</c:when>
						    	<c:when test="${sessionScope.pageNowThisMouth!=1&&sessionScope.pageNowThisMouth!=null}">
						    				<li>
									    		<a href="brushListThisMouth?pageNum=${sessionScope.pageNowThisMouth-1}" aria-label="Previous">
									        		<span aria-hidden="true">&laquo;</span>
									      		</a>
								      		</li>
						    	</c:when>
						    	<c:when test="${sessionScope.pageNowLastMouth==1}">
						    	
						    	</c:when>
						    	<c:when test="${sessionScope.pageNowLastMouth!=1&&sessionScope.pageNowLastMouth!=null}">
						    				<li>
									    		<a href="brushListLastMouth?pageNum=${sessionScope.pageNowLastMouth-1}" aria-label="Previous">
									        		<span aria-hidden="true">&laquo;</span>
									      		</a>
								      		</li>
						    	</c:when>
						 </c:choose>
						 <c:choose>
						 	<c:when test="${sessionScope.pagesThisMouth==null&&sessionScope.pagesLastMouth==null}">
						 		<c:forEach var="item" begin="1" end="${sessionScope.pages}">
						    		<li><a href="brushList?pageNum=${item}">${item}</a></li>
						    	</c:forEach>
						 	</c:when>
						 	<c:when test="${sessionScope.pageNowThisMouth!=null}">
						 		<c:forEach var="item" begin="1" end="${sessionScope.pagesThisMouth}">
						    		<li><a href="brushListThisMouth?pageNum=${item}">${item}</a></li>
						    	</c:forEach>
						 	</c:when>
						 	<c:when test="${sessionScope.pageNowLastMouth!=null}">
						 		<c:forEach var="item" begin="1" end="${sessionScope.pagesLastMouth}">
						    		<li><a href="brushListLastMouth?pageNum=${item}">${item}</a></li>
						    	</c:forEach>
						 	</c:when>
						 </c:choose>
						    
						    <c:choose>
						    	<c:when test="${sessionScope.pageNowThisMouth==null&&sessionScope.pageNowLastMouth==null}">
								    <c:choose>
								    	<c:when test="${sessionScope.pageNow==sessionScope.pages}">
								    		
								    	</c:when>
								    	<c:when test="${sessionScope.pageNow<=sessionScope.pages}">
								    		<li>
									    		<a href="brushList?pageNum=${sessionScope.pageNow+1}" aria-label="Previous">
									        		<span aria-hidden="true">&raquo;</span>
									      		</a>
								      		</li>
								    	</c:when>
								     </c:choose>
						    	</c:when>
						    	<c:when test="${sessionScope.pageNowThisMouth!=null}">
							    	<c:choose>
							    		<c:when test="${sessionScope.pageNowThisMouth==sessionScope.pagesThisMouth}">
									    		
									    	</c:when> 
									    	<c:when test="${sessionScope.pageNowThisMouth<sessionScope.pagesThisMouth}">
									    		<li>
										    		<a href="brushListThisMouth?pageNum=${sessionScope.pageNowThisMouth+1}" aria-label="Previous">
										        		<span aria-hidden="true">&raquo;</span>
										      		</a>
									      		</li>
									    	</c:when> 
							    	</c:choose>
						    			  
						    	</c:when>
						    	<c:when test="${sessionScope.pageNowLastMouth!=null}">
						    	<c:choose>
						    		<c:when test="${sessionScope.pageNowLastMouth==sessionScope.pagesLastMouth}">
								    		
								    	</c:when>
								    	<c:when test="${sessionScope.pageNowLastMouth<=sessionScope.pagesLastMouth}">
								    		<li>
									    		<a href="brushListLastMouth?pageNum=${sessionScope.pageNowLastMouth+1}" aria-label="Previous">
									        		<span aria-hidden="true">&raquo;</span>
									      		</a>
								      		</li>
								    	</c:when>
						    	</c:choose>
						    			
						    	</c:when>
						    </c:choose>
						    
						    
						     <c:choose>
						    	<c:when test="${sessionScope.pageNowThisMouth==null&&sessionScope.pageNowLastMouth==null}">
						    		<li><span>第${sessionScope.pageNow}页</span></li>
						    	</c:when>
						    	<c:when test="${sessionScope.pageNowThisMouth!=null }">
						    		<li><span>第${sessionScope.pageNowThisMouth}页</span></li>
						    	</c:when>
						    	<c:when test="${sessionScope.pageNowLastMouth!=null }">
						    		<li><span>第${sessionScope.pageNowLastMouth}页</span></li>
						    	</c:when>
						    </c:choose> 
						  </ul>
						</nav>
				
							</div>
						</div>
					</div>
					
</body>

</html>
