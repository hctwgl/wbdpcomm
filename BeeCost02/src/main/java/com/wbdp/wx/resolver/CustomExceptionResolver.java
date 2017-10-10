package com.wbdp.wx.resolver;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
public class CustomExceptionResolver implements HandlerExceptionResolver{

    /**日志log*/
    private static Logger log = LoggerFactory.getLogger(CustomExceptionResolver.class);
    //系统抛出的异常  
    @Override  
    public ModelAndView resolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {  
        //handler就是处理器适配器要执行的Handler对象(只有method)  
        //解析出异常类型。  
        /*  使用response返回    */
        response.setStatus(HttpStatus.OK.value()); //设置状态码  
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
        response.setCharacterEncoding("UTF-8"); //避免乱码  
        response.setHeader("Cache-Control", "no-cache, must-revalidate");  
        //如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示。  
        CustomException customException=null;
        try {
	        if(ex instanceof CustomException){  
	            customException = (CustomException)ex;  
	            //错误信息  
	            log.info("结果："+ ResultUtils.error(customException.getCode(),ex.getMessage()).toString());
				response.getWriter().write(ResultUtils.error(customException.getCode(),ex.getMessage()).toString());
	        }else{
	        	response.getWriter().write(ResultUtils.error(-1,ex.getMessage()).toString());
	        }
    	} catch (IOException e) {
            log.error("与客户端通讯异常:"+ e.getMessage(), e);  
			e.printStackTrace();
		}
        ModelAndView modelAndView=new ModelAndView();  
        
//
//        //将错误信息传到页面  
//        modelAndView.addObject("message",error);  
//          
//        //指向到错误界面  
//        modelAndView.setViewName("error");  
          
        return modelAndView;  
    }  
       
} 