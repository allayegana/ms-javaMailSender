package com.ms.email.consumers;


import com.ms.email.dtos.EmailRecordsDtos;
import com.ms.email.emails.EmailsModels;
import com.ms.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordsDtos emailRecordsDtos) {

        var emailsModels = new EmailsModels();
        BeanUtils.copyProperties(emailRecordsDtos,emailsModels);
        emailService.sendEmail(emailsModels);
    //System.out.println(emailRecordsDtos.emailTo());
    }

}
