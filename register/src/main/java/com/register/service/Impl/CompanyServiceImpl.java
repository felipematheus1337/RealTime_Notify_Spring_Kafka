package com.register.service.Impl;

import com.register.exception.ObjectNotFoundException;
import com.register.model.Company;
import com.register.model.dtos.CompanyRequest;
import com.register.model.dtos.CompanyResponse;
import com.register.repository.CompanyRepository;
import com.register.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;
    private final ModelMapper mapper;

    @Override
    public CompanyResponse create(CompanyRequest companyRequest) {
        Company company = mapper.map(companyRequest, Company.class);
        company.hasContactForEmail();
        CompanyResponse response = mapper.map(repository.save(company),CompanyResponse.class);
        return response;
    }

    @Override
    public CompanyResponse getOneById(Long id) {
        return repository.findById(id).map(c -> mapper.map(c, CompanyResponse.class))
                .orElseThrow(() -> new ObjectNotFoundException("Company not found with that id"));
    }

    @Override
    public List<CompanyResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(c -> mapper.map(c, CompanyResponse.class))
                .collect(Collectors.toList());
    }
}
