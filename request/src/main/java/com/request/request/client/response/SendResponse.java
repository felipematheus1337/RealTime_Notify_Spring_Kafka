package com.request.request.client.response;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SendResponse {

    private HttpStatus httpStatus;

    private int httpCode;
}
