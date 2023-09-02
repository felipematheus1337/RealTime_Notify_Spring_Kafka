package com.sender.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanyMessage {


    private Long id;

    private Long companyId;

    private String email;

    private String message;

    private NewsletterPreference preference;

    private Integer messagesReceived;
}
