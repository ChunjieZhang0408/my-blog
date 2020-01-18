package com.example.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Desc OperationAspect
 * @Author ZhangChunjie
 * @Date 2020/1/17 15:23
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class OperationAspect {


    @Pointcut("@annotation(com.example.config.annotation.RecordOperationLog)")
    public void log(){

    }

    @Around("log()")
    public Object aspect(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Enumeration<String> parameterNames = request.getParameterNames();
        log.info("parameterNames==>{}",JSONObject.toJSONString(parameterNames));
        Object[] args = joinPoint.getArgs();
        log.info("args==>{}", JSONObject.toJSONString(args));
        String requestURL = request.getRequestURL().toString();
        log.info("requestURL==>{}",requestURL);
        Object result = joinPoint.proceed();
        log.info("result==>{}",result);
        return result;
    }
}
