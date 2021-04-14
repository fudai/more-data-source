/**
 * Copyright (c) 2009-2021 WACAI,Inc.All Rights Reserved.
 *
 * @fileName: DataSourceRouter
 * @package: com.fudai.route
 * @date: 2021-04-12 17:49
 * @version: V1.0
 */
package com.fudai.route;

import com.fudai.context.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @className: DataSourceRouter
 * @description: 数据源路由
 * @author: fudai
 * @date: 2021-04-12 17:49
 */
public class DataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }
}
