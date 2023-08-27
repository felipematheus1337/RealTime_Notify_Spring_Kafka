package com.request.request.service;

import com.request.request.model.CompanyMessage;

import java.util.Optional;

public interface CompanyMessageService {

    CompanyMessage create(CompanyMessage companyMessage);

    CompanyMessage update(CompanyMessage companyMessage);

    Optional<CompanyMessage> get(Long id);

    Optional<CompanyMessage> getByCompanyId(Long id);
}
