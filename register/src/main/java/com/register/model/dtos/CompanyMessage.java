package com.register.model.dtos;


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


}
