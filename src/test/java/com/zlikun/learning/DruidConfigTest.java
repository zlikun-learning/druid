package com.zlikun.learning;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Druid配置测试
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/9/12 11:27
 */
public class DruidConfigTest {

    private DruidDataSource dataSource ;
    private JdbcTemplate jdbcTemplate ;

    @Before
    public void init() throws SQLException {
        dataSource = new DruidDataSource() ;

        // 配置驱动及连接，其中driver配置可以省略
        // dataSource.setDriver(new org.h2.Driver());
        dataSource.setUrl("jdbc:h2:mem:default;MODE=MySQL");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        // 配置连接池大小
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(5);
        dataSource.setInitialSize(5);

        // 配置超时参数
        dataSource.setMaxWait(60000);

        // 连接有效性检查及回收策略配置
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 'X'");
        dataSource.setValidationQueryTimeout(3);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);

        // 配置过滤器(后面一种配置方式更灵活，可以针对过滤器本身进行一些设置)
        dataSource.setFilters("wall,slf4j,stat");

        StatFilter statFilter = new StatFilter() ;
        statFilter.setSlowSqlMillis(3000);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);

        WallFilter wallFilter = new WallFilter() ;
        wallFilter.setConfig(new WallConfig());

        dataSource.setProxyFilters(Arrays.asList(
                statFilter ,
                wallFilter ,
                new Slf4jLogFilter()
        ));

        // 其它设置
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800);
        dataSource.setLogAbandoned(false);

        dataSource.init();

        // 初始化脚本，对应：<jdbc:initialize-database> 标签
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator() ;
        populator.addScript(new ClassPathResource("scripts/schema.sql"));
        populator.addScript(new ClassPathResource("scripts/data.sql"));
        populator.execute(dataSource);

        jdbcTemplate = new JdbcTemplate(dataSource) ;
    }

    @Test
    public void test() {
        jdbcTemplate.query("SELECT * FROM TBL_USER" ,resultSet -> {
            // 1	zlikun	2017-09-12 12:04:37.243
            // 2	kevin	2017-09-12 12:04:37.247
            // 3	jackson	2017-09-12 12:04:37.247
            do {
                System.out.println(resultSet.getLong(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getTimestamp(3));
            } while (resultSet.next()) ;
        }) ;
    }

    @After
    public void destroy() {
        dataSource.close();
    }

}
