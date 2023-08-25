package com.register.model.dtos;

import jakarta.persistence.Transient;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyResponse {


    private Long id;

    private String name;

    private String cnpj;

    private String email;

    private String contactEmail;

    private String phone;

}
