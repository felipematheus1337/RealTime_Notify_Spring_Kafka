package com.request.request.client;


import com.request.request.client.response.SendResponse;
import com.request.request.model.CompanyMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "SendClient", url = "http://localhost:8082/sender")
public interface SendClient {

    @PostMapping(value = "/welcome")
    SendResponse sendWelcome(@RequestBody CompanyMessage companyMessage);
}
