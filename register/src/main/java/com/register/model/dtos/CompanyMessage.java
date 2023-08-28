package com.register.model.dtos;


import com.register.model.NewsletterPreference;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CompanyMessage {

    private Long companyId;

    private String email;

    private String message;

    private NewsletterPreference preference;


}
