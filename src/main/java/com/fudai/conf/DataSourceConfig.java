/**
 * Copyright (c) 2009-2021 WACAI,Inc.All Rights Reserved.
 *
 * @fileName: DataSourceConfig
 * @package: com.fudai.conf
 * @date: 2021-04-12 17:36
 * @version: V1.0
 */
package com.fudai.conf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.fudai.enums.DataSourceEnum;
import com.fudai.route.DataSourceRouter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * @className: DataSourceConfig
 * @description: 数据源配置
 * @author: fudai
 * @date: 2021-04-12 17:36
 */
@Configuration
@MapperScan(basePackages = "com.fudai.dal.mapper", sqlSessionTemplateRef = "sqlTemplate")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slaver")
    public DataSource slaver() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public DataSourceRouter dynamicDB(@Qualifier("master") DataSource masterDataSource, @Autowired(required = false) @Qualifier("slaver") DataSource slaverDataSource) {
        DataSourceRouter dataSourceRouter = new DataSourceRouter();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DataSourceEnum.MASTER.getCode(), masterDataSource);
        if (Objects.nonNull(slaverDataSource)) {
            targetDataSources.put(DataSourceEnum.SLAVER.getCode(), slaverDataSource);
        }
        dataSourceRouter.setTargetDataSources(targetDataSources);
        dataSourceRouter.setDefaultTargetDataSource(masterDataSource);
        return dataSourceRouter;
    }

    @Bean
    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDB") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml"));
        bean.setDataSource(dynamicDataSource);
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "dataSourceTx")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dynamicDB") DataSource dynamicDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dynamicDataSource);
        return dataSourceTransactionManager;
    }
}
