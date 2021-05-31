package com.webcoder.vaccine.api.service.impl;

import com.webcoder.vaccine.api.commons.EncoderDecoderUtil;
import com.webcoder.vaccine.api.service.AlertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.pwd}")
    String encodedPassword;

    @Value("${service.provider.offer.report.to.mail.address}")
    String to;

    @Autowired
    EncoderDecoderUtil encoderDecoderUtil;

    @Override
    public boolean mailAlert(String subject, String body) {

        String decodedPassword = encoderDecoderUtil.base64Decode(encodedPassword);
        log.info("ACTION= mailAlert | MAIL PASSWORD: {}", decodedPassword);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        try {
            javaMailSender.setPassword(decodedPassword);
            javaMailSender.send(simpleMailMessage);
            log.info("MAIL SENT TO: {}", to);
            return true;
        } catch (MailException e) {
            log.info("ACTION= mailAlert | EXCEPTION OCCURRED WHILE SENDING OFFER REPORT MAIL | CAUSE: {} | MESSAGE: {}", e.getCause(), e.getMessage());
            return false;
        }
    }
}
