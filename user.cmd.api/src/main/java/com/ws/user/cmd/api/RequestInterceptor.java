package com.ws.user.cmd.api;


import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@Scope(value = "request")
public class RequestInterceptor {

	private static final Logger log = (Logger) LoggerFactory.getLogger(RequestInterceptor.class);

	@Around("fbAllOperations()")
	public Object validateReqeuest(ProceedingJoinPoint joinPoint) throws Throwable {
		
		final long st = System.currentTimeMillis();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Object obj = joinPoint.proceed();
		final long tt = System.currentTimeMillis() - st;
		log.info(request.getRequestURI()+"|"+joinPoint.getTarget().getClass()+"|"+joinPoint.getSignature().getName()+"|"+tt);
		return obj;
		
	}

	@Pointcut("execution(* com.ws.user.cmd.api.controller.*..*(..))")
	public void fbAllOperations() {
	}
}
