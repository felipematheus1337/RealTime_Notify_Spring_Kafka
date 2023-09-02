package com.sender.amqp;


import com.sender.dto.CompanyMessage;
import com.sender.utils.KafkaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerCompletionEventListener implements ApplicationListener<KafkaListenerCompletionEvent> {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Override
    public void onApplicationEvent(KafkaListenerCompletionEvent event) {
            CompanyMessage message = event.getCompanyMessage();
            this.kafkaTemplate.send(KafkaUtils.STORAGE_TOPIC, message);
    }
}
