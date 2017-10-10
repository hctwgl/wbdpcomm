package com.wbdp.wx.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * http拦截器
 */
@Aspect
@Component
public class HttpAspect {
    /**日志log*/
    private static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.wbdp.wx.controller.*(..))")
    public void log(){ }

    @Before("log()")
    public void doBefore(JoinPoint JoinPoint){
        logger.info("方法执行之前就执行的拦截");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}", request.getRequestURL());
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", JoinPoint.getSignature().getDeclaringTypeName());
        //参数
        logger.info("args={}", JoinPoint.getArgs());

    }

    @After("log()")
    public void doafter(){
        logger.info("方法执行之后就执行的拦截");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object);
    }

}