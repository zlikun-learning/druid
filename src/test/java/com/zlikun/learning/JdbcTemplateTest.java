package com.zlikun.learning;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 使用spring配置的druid连接池测试
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/9/12 12:41
 */
public class JdbcTemplateTest {

    private JdbcTemplate jdbcTemplate ;

    @Before
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml") ;
        jdbcTemplate = context.getBean(JdbcTemplate.class) ;
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

}
