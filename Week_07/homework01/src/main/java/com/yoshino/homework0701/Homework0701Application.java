package com.yoshino.homework0701;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class Homework0701Application implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Homework0701Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        log.info(String.valueOf(conn));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("addBatch");
        int size = 1000000;
        int thread = 1000;
        int subSize = size / thread;
        CountDownLatch countDownLatch = new CountDownLatch(thread);
        addBatchThread(thread, subSize, countDownLatch);
//        addBatch(conn, size);
//        add(jdbcTemplate, size);
//        addByMultipleValues(jdbcTemplate, size);

        countDownLatch.await();
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }

    private void addBatchThread(int threadSize, int subSize, CountDownLatch countDownLatch) {
        AtomicInteger start = new AtomicInteger();
        Runnable runnable = () -> {
            try {
                Connection connection = jdbcTemplate.getDataSource().getConnection();
                addBatch(connection, start.getAndAdd(subSize), subSize);
                log.info(String.valueOf(start.get()));
                countDownLatch.countDown();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(8);
        IntStream.rangeClosed(1, threadSize).forEach(i -> executor.execute(runnable));
    }

    private void addByMultipleValues(JdbcTemplate jdbcTemplate, int size) {
        String sql = "INSERT INTO `mytest`.`my_order` (`order_no`, `shop_id`, `user_id`, `country_id`, " +
            "`province_id`, `city_id`, `district_id`, `street_id`, `address`, `phone`, " +
            "`money`, `remark`, `status`, `is_deleted`) " +
            "VALUES ";
        int count = 0;
        String subSql = "(%s, 1, 1, 1, 1, 1, 1, 1, '地址', '18868831988', '1.0', '1', '1', '1'),";
        StringBuilder currentSql = null;
        for (int i = 0; i < size; i++) {
            count++;
            if (currentSql == null) {
                currentSql = new StringBuilder(sql);
            }
            currentSql.append(String.format(subSql, i));
            if (count == 1000) {
                jdbcTemplate.execute(currentSql.substring(0, currentSql.length() - 1));
                currentSql = null;
                count = 0;
            }
        }
        if (count != 0) {
            jdbcTemplate.execute(currentSql.substring(0, currentSql.length() - 1));
        }
    }

    private void add(JdbcTemplate jdbcTemplate, int size) {
        String sql = "INSERT INTO `mytest`.`my_order` (`order_no`, `shop_id`, `user_id`, `country_id`, " +
            "`province_id`, `city_id`, `district_id`, `street_id`, `address`, `phone`, " +
            "`money`, `remark`, `status`, `is_deleted`) " +
            "VALUES (%s, 1, 1, 1, 1, 1, 1, 1, '地址', " +
            "'18868831988', '1.0', '1', '1', '1')";
        for (int i = 0; i < size; i++) {
            jdbcTemplate.execute(String.format(sql, (i + 1)));
        }
    }


    private static void addBatch(Connection conn, int start, int size) throws SQLException {
        String sql = "INSERT INTO `mytest`.`my_order` (`order_no`, `shop_id`, `user_id`, `country_id`, `province_id`, " +
            "`city_id`, `district_id`, `street_id`, `address`, `phone`, `money`, `remark`, `status`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement pstmt;
        try {
            // 事务，关闭自动提交
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < size; i++) {
                pstmt.setString(1, String.valueOf(start + i));
                pstmt.setLong(2, i / 10000);
                pstmt.setLong(3, i / 10000);
                pstmt.setInt(4, i / 10);
                pstmt.setInt(5, i / 10);
                pstmt.setInt(6, i / 10);
                pstmt.setInt(7, i / 10);
                pstmt.setInt(8, i / 10);
                pstmt.setString(9, "地址" + i);
                pstmt.setString(10,  String.valueOf(18868800000L + i));
                pstmt.setBigDecimal(11, BigDecimal.valueOf(i / 100));
                pstmt.setString(12, "备注" + i);
                pstmt.setInt(13, i % 10 );
                pstmt.addBatch();
//                log.info(String.valueOf(i));
            }
            int[] ret = pstmt.executeBatch();
//            System.out.println(Arrays.toString(ret));
            pstmt.close();
            conn.commit();
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    private void info(Connection conn) {
        System.out.println(conn);
    }
}
