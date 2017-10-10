package com.wbdp.bee.filter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginHandlerIntercepter implements HandlerInterceptor {

	private Logger logger=Logger.getLogger(LoginHandlerIntercepter.class);
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//获取session
		HttpSession session=request.getSession();
		//获取重定向的URL
		String URL=request.getContextPath()+"/logout";
		//访问的路径
		String url = request.getRequestURL().toString();
	  //判断是否拦截
		   if(session.getAttribute("username")!=null||url.indexOf("userLogin")!=-1){
			     return true; 
		   }else{
      //定义被拦截的提示   
	    response.setContentType("text/html;charset=utf-8");
	         PrintWriter out = response.getWriter();
	                     out.write("<script>alert('登录已过期');</script>");    
	                     out.write("<script>window.parent.frames.location.href =' "+URL+"'</script>");
	                     out.close();
         	 logger.warn("拦截器不通过---客户请求的地址为:"+url+"---客户的IP为:"+request.getRemoteAddr());
       		//	  response.sendRedirect(request.getContextPath()+"/login");
        			  return false;
		   }
		   
	}

}
