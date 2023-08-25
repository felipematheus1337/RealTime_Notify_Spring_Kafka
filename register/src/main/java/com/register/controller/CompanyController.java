package com.register.controller;


import com.register.model.dtos.CompanyRequest;
import com.register.model.dtos.CompanyResponse;
import com.register.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;

    @PostMapping()
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest companyRequest) {
        return new ResponseEntity<>(this.service.create(companyRequest), HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.getOneById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
}
