package com.yoshino.homework0702.v1.aop;

import com.yoshino.homework0702.v1.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class HandleDatasourceAspect {

    @Pointcut("@annotation(com.yoshino.homework0702.v1.aop.DataSource)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforeExecute(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        if (null == annotation) {
            annotation = joinPoint.getTarget().getClass().getAnnotation(DataSource.class);
        }
        if (null != annotation) {
            // 切换数据源
            DataSourceContextHolder.switchDataSource(annotation.readOnly());
        }
    }

    @After("pointcut()")
    public void afterExecute() {
        DataSourceContextHolder.clear();
    }
}
