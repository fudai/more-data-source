/**
 * Copyright (c) 2009-2021 WACAI,Inc.All Rights Reserved.
 *
 * @fileName: DataSourceContextHolder
 * @package: com.fudai.context
 * @date: 2021-04-12 17:50
 * @version: V1.0
 */
package com.fudai.context;

/**
 * @className: DataSourceContextHolder
 * @description: 数据源环境载体
 * @author: fudai
 * @date: 2021-04-12 17:50
 */
public class DataSourceContextHolder {

    public static final ThreadLocal<String> context = new ThreadLocal<String>();

    public static void set(String datasourceType){
        context.set(datasourceType);
    }

    public static String get(){
        return context.get();
    }

    public static void clear(){
        context.remove();
    }


}
