package com.zlg.zlgpm;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.entity.User;
import com.zlg.zlgpm.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class ZlgPmApplicationTests {


    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    private UserMapper userMapper;

    @Test
    void test() {
        Page<User> users = userServiceImpl.queryList();
        //分页total
        Long total = users.getTotal();
        System.out.println(total);
        for (User u : users) {
            System.out.println(u);
        }

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
        User user = new User();
        Page<User> users = PageHelper.startPage(1, 3).doSelectPage(() -> userMapper.selectList(user));
//        List<User> users = userMapper.selectList(user);
        for (User u :users){
            System.out.println(u);
        }
    }


}
