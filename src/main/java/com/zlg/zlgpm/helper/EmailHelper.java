package com.zlg.zlgpm.helper;

import com.zlg.zlgpm.commom.Utils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class EmailHelper {

    public static final String EMAIL_FORM = "noreply_developer@zlg.cn";

    @Resource
    private JavaMailSender mailSender;


    public SimpleMailMessage getSimpleMailMessage(String from, String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setSentDate(new Date());
        return simpleMailMessage;
    }

    @Async
    public void sendSimpleMailMessage(SimpleMailMessage message) {
        mailSender.send(message);
    }

    /**
     * 拼接邮件信息
     *
     * @param projectName     项目名称
     * @param projectVersion  项目版本
     * @param projectUserName 项目负责人
     * @param taskUsername    任务负责人
     * @param task            任务标题
     * @return 邮件文本
     */
    public String assembleEmailMessage(String projectName, String projectVersion, String projectUserName, String taskUsername, String task) {
        return "时间：" + Utils.convertTimestamp2Date(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n" +
                "项目名称： " + projectName + "\n" +
                "项目版本号： " + projectVersion + "\n" +
                "项目负责人： " + projectUserName + "\n" +
                "开发负责人： " + taskUsername + "\n" +
                "任务标题： " + task;
    }

    public String assembleEmailMessage(String projectName, String projectVersion, String task) {
        return "时间：" + Utils.convertTimestamp2Date(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n" +
                "项目名称： " + projectName + "\n" +
                "项目版本号： " + projectVersion + "\n" +
                "任务标题： " + task;
    }

}
