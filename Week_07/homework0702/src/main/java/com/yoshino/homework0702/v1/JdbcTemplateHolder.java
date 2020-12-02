package com.yoshino.homework0702.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class JdbcTemplateHolder implements InitializingBean {

    private final static Map<String, List<JdbcTemplate>> JDBC_TEMPLATE_MAP = new HashMap<>();

    @Autowired
    @Qualifier("masterJdbcTemplate")
    protected JdbcTemplate masterJdbcTemplate;

    @Autowired
    @Qualifier("slave1JdbcTemplate")
    protected JdbcTemplate slave1JdbcTemplate;

    @Autowired
    @Qualifier("slave2JdbcTemplate")
    protected JdbcTemplate slave2JdbcTemplate;

    public static JdbcTemplate get() {
        String dataSource = DataSourceContextHolder.getDataSource();
        log.info(dataSource);
        List<JdbcTemplate> jdbcTemplates = JDBC_TEMPLATE_MAP.get(dataSource);
        if (jdbcTemplates.isEmpty()) {
            return null;
        } else if (jdbcTemplates.size() == 1) {
            return jdbcTemplates.get(0);
        } else {
            int index = ThreadLocalRandom.current().nextInt(jdbcTemplates.size());
            log.info("get salve" + (1 + index));
            return jdbcTemplates.get(index);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JDBC_TEMPLATE_MAP.put("master", Collections.singletonList(masterJdbcTemplate));
        JDBC_TEMPLATE_MAP.put("slave", Arrays.asList(slave1JdbcTemplate, slave2JdbcTemplate));
    }
}
