package com.register.service.Impl;

import com.register.exception.ObjectNotFoundException;
import com.register.model.Company;
import com.register.model.dtos.CompanyMessage;
import com.register.model.dtos.CompanyRequest;
import com.register.model.dtos.CompanyResponse;
import com.register.repository.CompanyRepository;
import com.register.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ModelMapper mapper;

    @Value(value = "${kafkatopics-welcomeTopic}")
    private final String TOPIC;

    @Override
    public CompanyResponse create(CompanyRequest companyRequest) {
        Company company = mapper.map(companyRequest, Company.class);
        company.hasContactForEmail();
        CompanyResponse response = mapper.map(repository.save(company),CompanyResponse.class);
        var companyMessage = CompanyMessage.builder()
                .email(response.getEmail())
                .message("")
                .companyId(response.getId());
        log.info("Sending the message!");
        this.kafkaTemplate.send(TOPIC, companyMessage);
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
