package com.ms.email.service;


import com.ms.email.Repository.EmailsRepository;
import com.ms.email.emails.EmailsModels;
import com.ms.email.enums.StatusEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    final EmailsRepository emailsRepository;
    final JavaMailSender javaMailSender;

    public EmailService(EmailsRepository emailsRepository, JavaMailSender javaMailSender) {
        this.emailsRepository = emailsRepository;
        this.javaMailSender = javaMailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailForm;

    @Transactional
    public EmailsModels sendEmail(EmailsModels emailModel) {
        try{
            emailModel.setSendDateTime(LocalDateTime.now());
            emailModel.setEmailFrom(emailForm);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            javaMailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.ENVIADO);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailsRepository.save(emailModel);
        }
    }
}
