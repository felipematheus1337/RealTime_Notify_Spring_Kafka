package com.sender.listeners.scheduled;

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
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledLetterListener {

    private final ObjectMapper objectMapper;

    private  final SendEmailService sendEmailService;

    private final ApplicationEventPublisher eventPublisher;

    @KafkaListener(topics = {
            KafkaUtils.CRYPTO_LETTER,
            KafkaUtils.FINANCIAL_LETTER,
            KafkaUtils.SPORTS_LETTER,
            KafkaUtils.WORLD_LETTER
    })
    public void sendScheduledLetters(@Payload String message,
                                     @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {

        log.info("Received the message from the topic: {}",topic);
        log.info("Trying to deserialize the message...");
        try {
            var companyMessage = objectMapper.readValue(message, CompanyMessage.class);
            log.info("Sending the email..");
            this.sendEmailService.sendEmailTo(companyMessage, companyMessage.getPreference());
            log.info("E-mail Successfully send!");
        } catch(Exception e) {
           throw new BusinessException("Failed to deserialize/send the object/email");
        } finally {
            eventPublisher.publishEvent(new KafkaListenerCompletionEvent(this,
                    objectMapper.readValue(message, CompanyMessage.class)));
        }
    }





}
