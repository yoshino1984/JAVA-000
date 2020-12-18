package com.yoshino.account.config;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author wangxin
 * 2020/12/9 上午12:47
 * @since
 **/
@Configuration
public class DataSourceConfig {
    private static final String SHARDING_YML_PATH = "/META-INF/dataSource.yaml";


    @Bean(name = "dataSource")
    public static DataSource dataSource() throws SQLException, IOException {
        return YamlShardingSphereDataSourceFactory.createDataSource(getFile());
    }

    private static File getFile() throws IOException {
        return new ClassPathResource(SHARDING_YML_PATH).getFile();
    }
}
