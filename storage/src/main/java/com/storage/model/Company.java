package com.storage.model;

import com.storage.model.dto.CompanyMessage;
import com.storage.model.enums.NewsletterPreference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Data
public class Company {


    @Id
    private String id;


    private String name;


    private String cnpj;


    private String email;


    private String contactEmail;


    private String phone;

    private NewsletterPreference preference;


    Company toCompanyFromMessage(CompanyMessage message) {
        Company company = new Company();
        company.setPreference(message.getPreference());
        company.setContactEmail(message.getEmail());
        return company;
    }


}
