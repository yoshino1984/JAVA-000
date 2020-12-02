package com.yoshino.homework0703.v2;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * @author wangxin
 * 2020/12/2 下午5:16
 * @since
 **/
public class DataSourceHolder {
    private static DataSource dataSource = null;

    public static DataSource get() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        if (DataSourceHolder.dataSource == null) {
            DataSourceHolder.dataSource = dataSource;
        }
    }
}
