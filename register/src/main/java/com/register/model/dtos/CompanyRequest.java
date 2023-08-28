package com.register.model.dtos;


import com.register.model.NewsletterPreference;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyRequest {


    private String name;

    private String cnpj;

    private String email;

    private String contactEmail;

    private NewsletterPreference preference;

    private String phone;

    @Transient
    private boolean hasContactEmail;


}
