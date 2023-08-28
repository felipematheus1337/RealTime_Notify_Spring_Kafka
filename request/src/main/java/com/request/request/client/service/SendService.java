package com.request.request.client.service;


import com.request.request.client.SendClient;
import com.request.request.client.response.SendResponse;
import com.request.request.exception.FeignClientRestException;
import com.request.request.model.CompanyMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendService {


    private final SendClient sendClient;

    public SendResponse sendWelcomeMessage(CompanyMessage companyMessage) {
        log.info("Trying to send the json. {}",companyMessage);
        try {
            return this.sendClient.sendWelcome(companyMessage);
        } catch(FeignClientRestException e) {
            log.info("FAILED SENDING THE JSON, {}",e.getMessage());
            e.printStackTrace();
             throw new FeignClientRestException("Failed to send the JSON to welcome endpoint in Send Microsservice");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
