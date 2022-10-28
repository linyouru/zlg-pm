package com.zlg.zlgpm;

import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.entity.User;
import com.zlg.zlgpm.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ZlgPmApplicationTests {


    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    private UserMapper userMapper;

    @Test
    void test() {
//        Page<User> users = userServiceImpl.queryList();
        //分页total
//        Long total = users.getTotal();
//        System.out.println(total);
//        for (User u : users) {
//            System.out.println(u);
//        }

    }

    @Test
    void test1() {
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(2022, 10, 18);
        System.out.println(date.getTime());
        System.out.println(date1.getTime());
    }

    @Test
    void test2() {
        List<User> users = userMapper.selectList(null);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    void test3() {
        String s = DigestUtils.md5DigestAsHex("zlg2022".getBytes());
        System.out.println(s);
    }


}
