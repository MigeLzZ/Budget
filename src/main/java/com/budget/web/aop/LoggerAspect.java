package com.budget.web.aop;

import com.budget.utilits.LogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggerAspect {
    @Around("execution(* com.budget.web.controller.*.* (..)) ")
    public Object logAroundAdvice(ProceedingJoinPoint jp) throws Throwable {
        Object value = null;
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();

        try {
            LogUtils.debug("Aspect calls method: " + method.getName());
            value = jp.proceed();
            LogUtils.debug("Aspect method completed: " + method.getName());
        } catch (Throwable e) {
            LogUtils.error("logger advice: " + e);
            throw e;
        }
        return value;
    }
}
