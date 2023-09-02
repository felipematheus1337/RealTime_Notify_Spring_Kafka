package com.sender.service.Impl;

import com.sender.dto.CompanyMessage;
import com.sender.dto.NewsletterPreference;
import com.sender.email.factory.EmailTemplateFactory;
import com.sender.email.templates.EmailTemplate;
import com.sender.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl  implements SendEmailService {

    private final JavaMailSender emailSender;

    @Override
    public void sendEmailTo(CompanyMessage companyMessage, NewsletterPreference preference) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplay@notifykafkapp.com");
        message.setTo(companyMessage.getEmail());
        message.setSubject("Automatic email by kafka app");
        String txt = selectLetter(companyMessage.getMessage(), preference);
        message.setText(txt);
        emailSender.send(message);
    }

    @Override
    public void sendWelcomeEmailTo(CompanyMessage companyMessage, NewsletterPreference preference) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplay@notifykafkapp.com");
        message.setTo(companyMessage.getEmail());
        message.setSubject("Automatic email by kafka app");
        String txt = selectLetter(companyMessage.getMessage(), preference);
        message.setText(txt);
        emailSender.send(message);
    }

    private String selectLetter(String messageBody, NewsletterPreference preference) {
        EmailTemplate emailTemplate = EmailTemplateFactory.createEmailTemplate(preference);
        return emailTemplate.generateTemplate(messageBody);
    }

}
