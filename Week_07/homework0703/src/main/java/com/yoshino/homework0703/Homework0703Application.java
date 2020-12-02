package com.yoshino.homework0703;

import com.yoshino.homework0703.v2.DataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@SpringBootApplication
public class Homework0703Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Homework0703Application.class, args);
    }

    @Override
    public void run(String[] args) throws Exception {
        log.info("\n\n\n");
        File yamlFile = getFile("/META-INF/replica-query.yaml");
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(yamlFile);
        DataSourceHolder.setDataSource(dataSource);
        log.info("dataSource:" + dataSource);
        // 验证从库 轮询读
        select();
        // 验证读写
        write();
    }

    private static File getFile(final String fileName) {
        return new File(Homework0703Application.class.getResource(fileName).getFile());
    }

    /**
     * 从库：只读操作
     */
    public void select() {
        log.info("select");
        Map<Integer, AtomicInteger> countMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            List<Integer> ids = selectIds();
            countMap.computeIfAbsent(ids.size(), key -> new AtomicInteger()).incrementAndGet();
        }

        countMap.forEach((k, v) -> log.info("key:{} count:{}", k, v));
    }

    private List<Integer> selectIds() {
        String sql = "select id from t1 where id > 0";
        List<Integer> idList = new ArrayList<>();
        try {
            try (Connection conn = DataSourceHolder.get().getConnection()) {
                try (Statement stmt = conn.createStatement()) {
                    try (ResultSet rs = stmt.executeQuery(sql)) {
                        while (rs.next()) {
                            idList.add(rs.getInt(1));
                        }
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idList;
    }

    /**
     * 主库：进行查询更新然后查询的操作
     */
    @Transactional(rollbackFor = Throwable.class)
    public void write() {
        log.info("DemoService.write");
        // 开启强制走主库
        HintManager.getInstance().setPrimaryRouteOnly();

        List<Integer> before = selectIds();
        log.info(String.valueOf(before.size()));
        Integer maxId = before.stream().mapToInt(Integer::intValue).max().orElse(0);

        insertInner(maxId + 1);

        List<Integer> after = selectIds();
        log.info(String.valueOf(after.size()));
        int maxId2 = after.stream().mapToInt(Integer::intValue).max().orElse(0);
        Assert.isTrue(maxId.equals(maxId2 - 1), "max id not equal");
        // 清理强制走主库
        HintManager.clear();
    }

    private void insertInner(Integer id) {
        try (Connection conn = DataSourceHolder.get().getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO t1 (id) VALUES (?)")) {
                ps.setObject(1, id);
                int n = ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
