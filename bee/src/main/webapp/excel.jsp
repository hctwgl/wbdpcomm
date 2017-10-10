<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="jquery.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	
/*	$.ajax({
			type:"POST",
			url:"http://jiang.work:8082/HttpServerImpl/getCode",
	      	contentType: "application/json; charset=utf-8",     	
	      	dataType:"json",
	      	data: JSON.stringify({phone:"15527477588"}),
	      	success:function(data){
	      		alert(data);
	      	},
	      	error:function(){
	           alert("获取数据失败");
	      	}
	    })
	    
	    $.ajax({
        type: "POST",
        url: "http://jiang.work:8082/HttpServerImpl/getCode",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({phone:"15527477588"}),
        dataType: "json",
        success: function (message) {
          alert(message);
        },
        error: function (message) {
             alert("error");
        }
    });
    
 */
	
	
            //ajax 方式上传文件操作  
             $(document).ready(function(){  
                $('#btn').click(function(){  
                    if(checkData()){  
                        $('#form1').ajaxSubmit({    
                            url:"excel",  
                            dataType: 'text',  
                            success: resutlMsg,  
                            error: errorMsg  
                        });   
                        function resutlMsg(msg){  
                            alert(msg);     
                            $("#upfile").val("");  
                        }  
                        function errorMsg(){   
                            alert("导入excel出错！");      
                        }  
                    }  
                });  
             });  
                
                
             //JS校验form表单信息  
             function checkData(){  
                var fileDir = $("#upfile").val();  
                var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
                if("" == fileDir){  
                    alert("选择需要导入的Excel文件！");  
                    return false;  
                }  
                if(".xls" != suffix && ".xlsx" != suffix ){  
                    alert("选择Excel格式的文件导入！");  
                    return false;  
                }  
                return true;  
             } 
          </script> 
      
  </head>  
    
  <body>  
  <div>1.通过简单的form表单提交方式，进行文件的上</br> 2.通过jquery.form.js插件提供的form表单一步提交功能 </div></br>  
    <form method="POST"  enctype="multipart/form-data" id="form1" action="excel">  
        <table>  
         <tr>  
            <td>上传文件: </td>  
            <td> <input id="upfile" type="file" name="excel"></td>  
         </tr>  
        <tr>  
            <td><input type="submit" value="提交" onclick="return checkData()"></td>  
            <td><input type="button" value="ajax方式提交" id="btn" name="btn" ></td>  
         </tr>  
        </table>    
    </form>  
  </body>
</html>
