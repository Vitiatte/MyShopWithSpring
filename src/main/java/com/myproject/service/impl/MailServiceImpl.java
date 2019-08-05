package com.myproject.service.impl;

import com.myproject.entity.Order;
import com.myproject.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);

    @Override
    public void sendCheckCode(Order order) {
        final String username = "tt5881862@gmail.com";
        final String password = "RCZ7TzCySXVBj7P";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tt5881862@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(order.getEmail())
            );
            message.setSubject("Check code for order " + order.getId());
            message.setText("Code: " + order.getCheckCode());
            Transport.send(message);

        } catch (MessagingException e) {
            logger.warn("Exception when mail was being sent", e);
        }
    }
}
