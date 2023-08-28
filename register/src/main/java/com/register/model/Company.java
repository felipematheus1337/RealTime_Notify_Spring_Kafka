package com.register.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Data
@Table(name = "company")
public class Company implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "email")
    private String email;

    @Column(name = "contactEmail")
    private String contactEmail;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    private NewsletterPreference preference;

    @Transient
    private boolean hasContactEmail;

    public void hasContactForEmail() {
        if (!hasContactEmail) {
            contactEmail = email;
        }
    }


}
