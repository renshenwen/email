package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by swren on 2017-11-9.
 */
@RestController
@RequestMapping("/a")
public class A {
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    String from;
    @RequestMapping("/send")
    public void send(){

        SimpleMailMessage min = new SimpleMailMessage();
        min.setFrom(from);
        min.setTo("3343068522@qq.com");
        min.setSubject("test");

        min.setText(" xxxxxxxxxxxxxxxxxxxxxxValue");
        javaMailSender.send(min);
    }
}
