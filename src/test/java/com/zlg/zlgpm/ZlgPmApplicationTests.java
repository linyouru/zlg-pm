package com.zlg.zlgpm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.dao.ProjectMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import com.zlg.zlgpm.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ZlgPmApplicationTests {


    @Autowired
    private UserService userServiceImpl;


    @Resource
    private UserMapper userMapper;
    @Resource
    private ProjectMapper projectMapper;

    @Test
    void updateUser() {
        UserPo userPo = new UserPo();
        userPo.setId(15L);
        userPo.setUserName("zlg123");
        userPo.setPassword("123456");
        userPo.setEmail("zlg@qq.com");
        userMapper.updateById(userPo);
    }

    @Test
    void selectUserList(){
        int currentPage = 1;
        int pageSize = 2;
        String userName = "zlg123";
//        Page<User> userPage = userMapper.selectPage(new Page<>(currentPage,pageSize), null);
        Page<UserPo> userPage = userMapper.selectPage(new Page<UserPo>().setCurrent(currentPage).setSize(pageSize), new QueryWrapper<UserPo>().eq("userName", userName));

        System.out.println(1);
    }

    @Test
    void selectProjectList(){
        ProjectPo projectPo = new ProjectPo();
        QueryWrapper<ProjectPo> queryWrapper = new QueryWrapper<ProjectPo>().eq("p.name", "awtk");
//        List<Project> projects = projectMapper.selectByName(project,queryWrapper);
        System.out.println();
    }




}
