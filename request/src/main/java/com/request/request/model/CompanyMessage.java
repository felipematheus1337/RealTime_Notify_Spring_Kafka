package com.request.request.model;


import com.request.request.model.enums.NewsletterPreference;
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

    @Enumerated(EnumType.STRING)
    private NewsletterPreference preference;

    @Column(nullable = true)
    private Integer messagesReceived;
}
