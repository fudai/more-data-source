/**
 * Copyright (c) 2009-2021 WACAI,Inc.All Rights Reserved.
 *
 * @fileName: DataSourceEnum
 * @package: com.fudai.enums
 * @date: 2021-04-12 18:05
 * @version: V1.0
 */
package com.fudai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className: DataSourceEnum
 * @description: 数据源枚举
 * @author: fudai
 * @date: 2021-04-12 18:05
 */
@AllArgsConstructor
@Getter
public enum DataSourceEnum {
    MASTER("master", "主库"),
    SLAVER("slaver", "从库"),
    ;
    private String code;
    private String msg;

}
