package com.storage.model.dto;

import com.storage.model.Company;
import com.storage.model.enums.NewsletterPreference;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class CompanyMessage {


    private Long id;

    private Long companyId;

    private String email;

    private String message;

    private NewsletterPreference preference;



}
