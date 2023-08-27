package com.request.request.service.Impl;

import com.request.request.model.CompanyMessage;
import com.request.request.repository.CompanyMessageRepository;
import com.request.request.service.CompanyMessageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyMessageServiceImpl implements CompanyMessageService {

    private final CompanyMessageRepository repository;

    @Override
    public CompanyMessage create(CompanyMessage companyMessage) {
        return this.repository.save(companyMessage);
    }

    @Transactional
    @Override
    public CompanyMessage update(CompanyMessage companyMessage) {
        var companyExists = this.get(companyMessage.getCompanyId());

        if (companyExists.isEmpty()) {
            return this.create(companyMessage);
        }
        var companyFounded = companyExists.get();
        companyFounded.setMessage(companyMessage.getMessage());
        companyFounded.setEmail(companyMessage.getEmail());

        return this.repository.save(companyFounded);

    }

    @Override
    public Optional<CompanyMessage> get(Long id) {
        return this.repository.findById(id);
    }


    @Override
    public Optional<CompanyMessage> getByCompanyId(Long id) {
        return this.repository.findByCompanyId(id);
    }
}
