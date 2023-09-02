package com.request.request.repository;

import com.request.request.model.CompanyMessage;
import com.request.request.model.enums.NewsletterPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyMessageRepository extends JpaRepository<CompanyMessage, Long> {

    Optional<CompanyMessage> findByCompanyId(Long id);

    List<CompanyMessage> findByPreference(NewsletterPreference preference);
}
