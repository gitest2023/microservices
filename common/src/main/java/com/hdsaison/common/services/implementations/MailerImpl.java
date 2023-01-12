package com.hdsaison.common.services.implementations;

import com.hdsaison.common.services.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailerImpl implements Mailer {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(String to, String subject, String body) {
        String from = "gitworktest@gmail.com";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
