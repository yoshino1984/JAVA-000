package com.yoshino.homework0702.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DataSourceContextHolder {

    private static ThreadLocal<String> datasourceContext = new ThreadLocal<>();

    public static void switchDataSource(boolean readOnly) {
        String datasource = readOnly ? "slave" : "master";
        log.debug("switchDataSource: {}", datasource);
        datasourceContext.set(datasource);
    }

    public static String getDataSource() {
        return datasourceContext.get();
    }

    public static void clear() {
        datasourceContext.remove();
    }
}
