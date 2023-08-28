package com.register.config.mapper;


import com.register.model.Company;
import com.register.model.dtos.CompanyMessage;
import com.register.model.dtos.CompanyRequest;
import com.register.model.dtos.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class CompanyMapper {

    private final ModelMapper mapper;

    public Company toModel(CompanyResponse response) {
        return mapper.map(response, Company.class);
    }

    public Company requestToModel(CompanyRequest request) {
        return mapper.map(request, Company.class);
    }

    public CompanyResponse toResponse(Company company) {
        return mapper.map(company, CompanyResponse.class);
    }

    public CompanyMessage toMessage(Company company) {
        return CompanyMessage.builder()
                .companyId(company.getId())
                .message("")
                .preference(company.getPreference())
                .email(company.getEmail())
                .build();
    }

    public List<CompanyResponse> toResponseList(List<Company> list) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<CompanyMessage> toMessageList(List<Company> list) {
        return list.stream()
                .map(this::toMessage)
                .collect(Collectors.toList());
    }
}
