package com.request.request.repository;

import com.request.request.model.CompanyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyMessageRepository extends JpaRepository<CompanyMessage, Long> {

    Optional<CompanyMessage> findByCompanyId(Long id);
}
