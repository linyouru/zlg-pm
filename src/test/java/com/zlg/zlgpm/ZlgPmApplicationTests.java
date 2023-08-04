package com.zlg.zlgpm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.commom.WorkDayUtils;
import com.zlg.zlgpm.dao.ProjectMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import com.zlg.zlgpm.service.ProjectService;
import com.zlg.zlgpm.service.TaskService;
import com.zlg.zlgpm.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ZlgPmApplicationTests {


    @Autowired
    private UserService userServiceImpl;

    @Resource
    private TaskService taskService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private ProjectService projectService;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private WorkDayUtils workDayUtils;

    @Test
    void test() {
        List<String> list = null;
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("123");
    }

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
    void selectUserList() {
        int currentPage = 1;
        int pageSize = 2;
        String userName = "zlg123";
//        Page<User> userPage = userMapper.selectPage(new Page<>(currentPage,pageSize), null);
        Page<UserPo> userPage = userMapper.selectPage(new Page<UserPo>().setCurrent(currentPage).setSize(pageSize), new QueryWrapper<UserPo>().eq("userName", userName));

        System.out.println(1);
    }

    @Test
    void selectProjectList() {
        ProjectPo projectPo = new ProjectPo();
        QueryWrapper<ProjectPo> queryWrapper = new QueryWrapper<ProjectPo>().eq("p.name", "awtk");
//        List<Project> projects = projectMapper.selectByName(project,queryWrapper);
        System.out.println();
    }

    @Test
    void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply_developer@zlg.cn");
        message.setTo("270809304@qq.com");
        message.setSubject("任务更新");
        message.setText("项目xxx的任务xxx已更新,请查看");
        message.setSentDate(new Date());

        mailSender.send(message);
        System.out.println("发送完成");
    }

    @Test
    void groupByProjectName() {
        projectService.aggregatedProjectName();
    }

    @Test
    void selectProjectStatistics() {
        projectService.selectProjectStatistics(1, 5);
    }

    @Test
    void workDayUtilsTest() {

        //同一周内普通工作日,4天,2023-08-01 ~ 2023-08-04
        double days1 = workDayUtils.getWorkdayTimeInMillisExcWeekendHolidays(1690819200000L, 1691164799000L);
        System.out.println("days1: " + days1);

        //同一周内含节假日工作日,4天,2023-04-03 ~ 2023-04-07
        double days2 = workDayUtils.getWorkdayTimeInMillisExcWeekendHolidays(1680451200000L, 1680883199000L);
        System.out.println("days2: " + days2);

        //同一周内周末,0天,2023-07-29 ~ 2023-07-30
        double days3 = workDayUtils.getWorkdayTimeInMillisExcWeekendHolidays(1690560000000L, 1690732799000L);
        System.out.println("days3: " + days3);

        //跨周普通工作日,7天,2023-07-26 ~ 2023-08-03
        double days4 = workDayUtils.getWorkdayTimeInMillisExcWeekendHolidays(1690300800000L, 1691078399000L);
        System.out.println("days4: " + days4);

        //跨周含节假日工作日,6天,2023-04-25 ~ 2023-05-05
        double days5 = workDayUtils.getWorkdayTimeInMillisExcWeekendHolidays(1682352000000L, 1683302399000L);
        System.out.println("days5: " + days5);

    }

}
