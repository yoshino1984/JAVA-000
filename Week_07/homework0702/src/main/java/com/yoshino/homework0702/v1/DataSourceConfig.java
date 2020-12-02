package com.yoshino.homework0702.v1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    //主数据源配置 master数据源
    @Primary
    @Bean(name = "masterDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    //主数据源 master数据源
    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource(@Qualifier("masterDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    /** 第1个slave数据源配置 */
    @Bean(name = "slave1DataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSourceProperties slave1DataSourceProperties() {
        return new DataSourceProperties();
    }

    /** 第1个slave数据源 */
    @Bean("slave1DataSource")
    public DataSource slave1DataSource(@Qualifier("slave1DataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    /** 第1个slave数据源配置 */
    @Bean(name = "slave2DataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSourceProperties slave2DataSourceProperties() {
        return new DataSourceProperties();
    }

    /** 第1个slave数据源 */
    @Bean("slave2DataSource")
    public DataSource slave2DataSource(@Qualifier("slave2DataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "slave1JdbcTemplate")
    public JdbcTemplate slave1JdbcTemplate(@Qualifier("slave1DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "slave2JdbcTemplate")
    public JdbcTemplate slave2JdbcTemplate(@Qualifier("slave2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
