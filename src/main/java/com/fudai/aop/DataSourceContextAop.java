/**
 * Copyright (c) 2009-2021 WACAI,Inc.All Rights Reserved.
 *
 * @fileName: DataSourceContextAop
 * @package: com.fudai.aop
 * @date: 2021-04-12 18:58
 * @version: V1.0
 */
package com.fudai.aop;

import com.fudai.context.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @className: DataSourceContextAop
 * @description: 数据源切面
 * @author: fudai
 * @date: 2021-04-12 18:58
 */
@Slf4j
@Aspect
@Component
@Order(value = 0)
public class DataSourceContextAop {

    @Around("@annotation(com.fudai.aop.DataSourceSwitcher)")
    public Object setDynamicDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean clear = true;
        try {
            Method method = this.getMethod(joinPoint);
            DataSourceSwitcher dataSourceSwitcher = method.getAnnotation(DataSourceSwitcher.class);
            clear = dataSourceSwitcher.clear();
            DataSourceContextHolder.set(dataSourceSwitcher.value().getCode());
            log.info("数据源切换至：{}", dataSourceSwitcher.value().getCode());
            return joinPoint.proceed();
        } finally {
            if (clear) {
                DataSourceContextHolder.clear();
            }
        }
    }

    private Method getMethod(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        return signature.getMethod();
    }
}
