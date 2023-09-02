package com.sender.amqp;


import com.sender.dto.CompanyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;



public class KafkaListenerCompletionEvent  extends ApplicationEvent {

    private final CompanyMessage message;

    public KafkaListenerCompletionEvent(Object source, CompanyMessage message) {
        super(source);
        this.message = message;
    }

    public CompanyMessage getCompanyMessage() {
        return message;
    }
}
