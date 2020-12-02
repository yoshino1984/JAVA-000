package com.yoshino.homework0702.v1.service;

import com.yoshino.homework0702.v1.DataSourceContextHolder;
import com.yoshino.homework0702.v1.JdbcTemplateHolder;
import com.yoshino.homework0702.v1.aop.DataSource;
import com.yoshino.homework0702.v1.bean.Table1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Slf4j
public class DemoService {

    private JdbcTemplate getJdbcTemplate() {
        return JdbcTemplateHolder.get();
    }

    /**
     * 从库：只读操作
     */
    @DataSource(readOnly = true)
    public void select() {
        log.info("DemoService.select");
        for (int i = 0; i < 5; i++) {
            List<Integer> list = getJdbcTemplate().queryForList("select id from t1", Integer.class);
            log.info(String.valueOf(list.size()));
        }
    }

    /**
     * 主库：进行查询更新然后查询的操作
     */
    @DataSource()
    public void write() {
        log.info("DemoService.write");
        List<Integer> before = getJdbcTemplate().queryForList("select id from t1", Integer.class);
        log.info(String.valueOf(before.size()));
        Integer maxId = before.stream().mapToInt(Integer::intValue).max().orElse(0);

        getJdbcTemplate().update("insert into t1(id) values (?)", maxId + 1);

        List<Integer> after = getJdbcTemplate().queryForList("select id from t1", Integer.class);
        log.info(String.valueOf(after.size()));
        int maxId2 = after.stream().mapToInt(Integer::intValue).max().orElse(0);
        Assert.isTrue(maxId.equals(maxId2 - 1), "");
    }

}
