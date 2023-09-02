package com.request.request.api;


import com.request.request.model.CompanyMessage;
import com.request.request.service.CompanyMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custom")
@RequiredArgsConstructor
public class CustomLetterController {

    private final CompanyMessageService service;

    @PostMapping("/{companyId}")
    public ResponseEntity<Void> requestAnCustomLetter(@PathVariable(value = "companyId") Long companyId,
                                                      @RequestBody CompanyMessage companyMessage) {

        this.service.sendCustomMessage(companyId, companyMessage);

        return ResponseEntity.noContent().build();
    }
}
