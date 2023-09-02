package com.sender.resources;


import com.sender.dto.CompanyMessage;
import com.sender.dto.SendResponse;
import com.sender.exception.BusinessException;
import com.sender.service.SendEmailService;
import com.sender.utils.KafkaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sender")
@RestController
@RequiredArgsConstructor
public class SenderResource {


    private final SendEmailService sendEmailService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping(value = "/welcome")
    SendResponse sendWelcome(@RequestBody CompanyMessage companyMessage) {
        try {
            this.sendEmailService.sendWelcomeEmailTo(companyMessage, companyMessage.getPreference());
            this.kafkaTemplate.send(KafkaUtils.STORAGE_TOPIC, companyMessage);

        } catch(Exception e) {
            throw new BusinessException("Failed to execute the Welcome mailer");
        }

        return new SendResponse(HttpStatus.CREATED, 200);
    }

}
