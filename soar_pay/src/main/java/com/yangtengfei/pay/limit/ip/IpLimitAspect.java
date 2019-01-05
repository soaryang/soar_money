package com.yangtengfei.pay.limit.ip;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class IpLimitAspect {
    @Before("execution(public * com.yangtengfei.pay.controller..*.*(..)) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint , IpLimit limit) throws IpLimitException {


        log.info("limit:"+JSON.toJSONString(limit));
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String requestId = attributes.getRequest().getRemoteAddr();
        if(!requestId.equals(limit.ip())){
            throw new IpLimitException();
        }

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }
}
