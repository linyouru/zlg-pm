package com.zlg.zlgpm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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


    @Resource
    private UserMapper userMapper;

    @Test
    void updateUser() {
        User user = new User();
        user.setId(15L);
        user.setUserName("zlg123");
        user.setPassword("123456");
        user.setEmail("zlg@qq.com");
        userMapper.updateById(user);
    }

    @Test
    void selectUserList(){
        int currentPage = 1;
        int pageSize = 2;
        String userName = "zlg123";
//        Page<User> userPage = userMapper.selectPage(new Page<>(currentPage,pageSize), null);
        Page<User> userPage = userMapper.selectPage(new Page<User>().setCurrent(currentPage).setSize(pageSize), new QueryWrapper<User>().eq("userName", userName));

        System.out.println(1);
    }




}
