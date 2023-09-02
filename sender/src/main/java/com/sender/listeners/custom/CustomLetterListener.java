package com.sender.listeners.custom;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sender.amqp.KafkaListenerCompletionEvent;
import com.sender.dto.CompanyMessage;

import com.sender.exception.BusinessException;
import com.sender.service.SendEmailService;
import com.sender.utils.KafkaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class CustomLetterListener {

    private final ObjectMapper objectMapper;

    private final SendEmailService sendEmailService;


    private final ApplicationEventPublisher eventPublisher;

    @KafkaListener(topics = KafkaUtils.CUSTOM_TOPIC)
    public void customLetterListener(@Payload String message) throws JsonProcessingException {
        log.info("Trying to deserialize the message...");
        try {
            var companyMessage = objectMapper.readValue(message, CompanyMessage.class);
            log.info("Sending the email..");
            this.sendEmailService.sendEmailTo(companyMessage, companyMessage.getPreference());
            log.info("E-mail Successfully send");
        } catch(Exception e) {
            throw new BusinessException("Failed to deserialize the object.");
        } finally {
            eventPublisher.publishEvent(new KafkaListenerCompletionEvent(this,
                    objectMapper.readValue(message, CompanyMessage.class)));
        }

    }

}
