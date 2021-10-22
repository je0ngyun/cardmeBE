package com.jy.cardme.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@Aspect
public class ControllerLogAspect {

    @Around("within(com.jy.cardme.controller.*))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        long startAt = System.currentTimeMillis();
        String method = request.getMethod();
        String path = request.getRequestURI();

        ResponseEntity result = (ResponseEntity) pjp.proceed();

        long endAt = System.currentTimeMillis();
        log.info("{} {} {} {}ms",method, path, result.getStatusCode(), endAt - startAt);

        return result;
    }
}
