package com.request.request.resource;


import com.request.request.model.enums.NewsletterPreference;
import com.request.request.service.CompanyMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledNewsLetterEmails {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final CompanyMessageService companyMessageService;

    private static final String SPORTS_TOPIC_NAME = "sportsletter";
    private static final String CRYPTO_TOPIC_NAME = "cryptoletter";
    private static final String FINANCIAL_TOPIC_NAME = "financialletter";
    private static final String WORLD_TOPIC_NAME = "worldletter";

    @Scheduled(cron = "0 0 6 * * ?")
    public void sendSportsNewsLetterEmails() {

        var companies = this.companyMessageService.findByPreference(NewsletterPreference.SPORTS);

        this.kafkaTemplate.send(SPORTS_TOPIC_NAME, companies);

    }

    @Scheduled(cron = "0 0 7 * * ?")
    public void sendCryptoNewsLetterEmails() {

        var companies = this.companyMessageService.findByPreference(NewsletterPreference.CRYPTO);

        this.kafkaTemplate.send(CRYPTO_TOPIC_NAME, companies);
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void sendFinanceNewsLetterEmails() {

        var companies = this.companyMessageService.findByPreference(NewsletterPreference.FINANCE);

        this.kafkaTemplate.send(FINANCIAL_TOPIC_NAME, companies);
    }

    @Scheduled(cron = "0 30 8 * * ?")
    public void sendWorldNewsLetterEmails() {
        var companies = this.companyMessageService.findByPreference(NewsletterPreference.WORLD_NEWS);

        this.kafkaTemplate.send(WORLD_TOPIC_NAME, companies);

    }

}
