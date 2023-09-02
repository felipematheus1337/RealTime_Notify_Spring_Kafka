package com.request.request.service.Impl;

import com.request.request.exception.BusinessAMQPException;
import com.request.request.exception.BusinessException;
import com.request.request.model.CompanyMessage;
import com.request.request.model.enums.NewsletterPreference;
import com.request.request.repository.CompanyMessageRepository;
import com.request.request.service.CompanyMessageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyMessageServiceImpl implements CompanyMessageService {

    private final CompanyMessageRepository repository;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String CUSTOM_LETTER_TOPIC_NAME = "customLetter";

    @Override
    public CompanyMessage create(CompanyMessage companyMessage) {
        return this.repository.save(companyMessage);
    }

    @Transactional
    @Override
    public CompanyMessage update(CompanyMessage companyMessage) {
        var companyExists = this.get(companyMessage.getCompanyId());

        if (companyExists.isEmpty()) {
            return this.create(companyMessage);
        }
        var companyFounded = companyExists.get();
        companyFounded.setMessage(companyMessage.getMessage());
        companyFounded.setEmail(companyMessage.getEmail());

        return this.repository.save(companyFounded);

    }

    @Override
    public Optional<CompanyMessage> get(Long id) {
        return this.repository.findById(id);
    }


    @Override
    public Optional<CompanyMessage> getByCompanyId(Long id) {
        return this.repository.findByCompanyId(id);
    }

    @Override
    public List<CompanyMessage> findByPreference(NewsletterPreference preference) {
        return this.repository.findByPreference(preference);
    }

    @Override
    @Async
    public void sendCustomMessage(Long companyId, CompanyMessage companyMessage) {
        log.info("Searching Company by the id...");
        var optCompany = this.getByCompanyId(companyId);

        if (optCompany.isEmpty()) {
            throw new BusinessException("Company don't exist");
        }

        log.info("Company founded successfully.");

        var companyFounded = optCompany.get();

        var future = this.kafkaTemplate.send(CUSTOM_LETTER_TOPIC_NAME, companyFounded);

        log.info("Sending the message..");

        future.whenComplete((f,err) -> {
            if (err != null) {
                log.info("Failed to send the message with the object: {}",companyFounded);
                throw new BusinessAMQPException("Failed sending the message." + err.getMessage());
            } else {
                RecordMetadata recordMetadata = f.getRecordMetadata();
                ProducerRecord<?, ?> producerRecord = f.getProducerRecord();
                log.info("Message succesfully send to the topic: " + CUSTOM_LETTER_TOPIC_NAME);
                log.info("Topic: " + recordMetadata.topic());
                log.info("Partition: " + recordMetadata.partition());
                log.info("Offset: " + recordMetadata.offset());
                log.info("Key: " + producerRecord.key());
                log.info("Value: " + producerRecord.value());
            }
        });


    }
}
