package com.request.request.amqp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.request.request.exception.ApiError;
import com.request.request.exception.BusinessAMQPException;
import com.request.request.model.CompanyMessage;
import com.request.request.service.CompanyMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaListenersClass {

    private final ObjectMapper objectMapper;

    private final CompanyMessageService service;

    private final static String WELCOME_TOPIC = "kafkatopics-welcomeTopic";

    @KafkaListener(topics = WELCOME_TOPIC)
    public void welcomeListener(@Payload String msg) throws JsonProcessingException {
        log.info("Received the message, and now trying to deserialize it. {}",msg);
        var company = objectMapper.readValue(msg, CompanyMessage.class);
        try {

        } catch(Exception e) {
          log.info("Exception trying to deserialize the message. {}",e.getMessage());
            ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, msg, e.getMessage());
            log.info("ERROR ::: {}", apiError);
        }

        if (this.service.getByCompanyId(company.getCompanyId()).isPresent()) {
              throw new BusinessAMQPException("Company already created!");
        }

        this.service.create(company);


    }

}
