package com.iuminov.service;

import com.iuminov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void notify(User user) {
        CompletableFuture.runAsync(() -> javaMailSender.send(this.registrationEmail(user)));
    }

    private SimpleMailMessage registrationEmail(User user) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("aleks.khambir@gmail.com");
        msg.setTo(user.getEmail());
        msg.setSubject("Email verification");
        msg.setText(
                String.format("Please follow link below for finishing your registration: http://localhost:8080/activation/%s",
                user.getToken()));

        return msg;
    }
}
