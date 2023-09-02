package com.sender.email.factory;

import com.sender.dto.NewsletterPreference;
import com.sender.email.templates.*;
import org.springframework.stereotype.Component;

@Component
public class EmailTemplateFactory {

    public static EmailTemplate createEmailTemplate(NewsletterPreference emailType) {
        switch (emailType) {
            case CRYPTO -> {
                return new CryptoEmailTemplate();
            }
            case SPORTS -> {
                return new SportsEmailTemplate();
            }
            case FINANCE -> {
                return new FinanceEmailTemplate();
            }
            case WORLD_NEWS -> {
                return new WorldEmailTemplate();
            }
            default -> {
                return new CustomEmailTemplate();
            }
        }
    }
}
