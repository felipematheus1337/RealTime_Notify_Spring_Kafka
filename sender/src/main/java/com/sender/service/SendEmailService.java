package com.sender.service;

import com.sender.dto.CompanyMessage;
import com.sender.dto.NewsletterPreference;
import org.springframework.mail.javamail.JavaMailSender;

public interface SendEmailService {

    void sendEmailTo(CompanyMessage companyMessage, NewsletterPreference preference);

    void sendWelcomeEmailTo(CompanyMessage companyMessage, NewsletterPreference preference);
}
