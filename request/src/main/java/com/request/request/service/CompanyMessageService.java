package com.request.request.service;

import com.request.request.model.CompanyMessage;
import com.request.request.model.enums.NewsletterPreference;

import java.util.List;
import java.util.Optional;

public interface CompanyMessageService {

    CompanyMessage create(CompanyMessage companyMessage);

    CompanyMessage update(CompanyMessage companyMessage);

    Optional<CompanyMessage> get(Long id);

    Optional<CompanyMessage> getByCompanyId(Long id);

    List<CompanyMessage> findByPreference(NewsletterPreference preference);

    void sendCustomMessage(Long companyId, CompanyMessage companyMessage);
}
