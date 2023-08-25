package com.register.service;

import com.register.model.dtos.CompanyRequest;
import com.register.model.dtos.CompanyResponse;

import java.util.List;
import java.util.Optional;

public interface CompanyService {


    CompanyResponse create(CompanyRequest companyRequest);

    CompanyResponse getOneById(Long id);

    List<CompanyResponse> getAll();


}
