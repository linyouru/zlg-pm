package com.zlg.zlgpm.helper;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class EmailHelper {

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

    public void sendSimpleMailMessage(SimpleMailMessage message) {
        mailSender.send(message);
    }


}
