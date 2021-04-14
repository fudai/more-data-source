package com.fudai.aop;

import com.fudai.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * @className: DataSourceSwitcher
 * @description: 数据源注解
 * @author: fudai
 * @date: 2021-04-12 18:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSwitcher {

    DataSourceEnum value() default DataSourceEnum.MASTER;

    boolean clear() default true;
}
