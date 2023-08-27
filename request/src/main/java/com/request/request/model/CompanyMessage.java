package com.request.request.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "company_message")
public class CompanyMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    private String email;

    private String message;

    @Column(nullable = true)
    private Integer messagesReceived;
}
