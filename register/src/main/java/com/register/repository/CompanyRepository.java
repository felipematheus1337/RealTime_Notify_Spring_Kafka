package com.register.repository;

import com.register.model.Company;
import com.register.model.NewsletterPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {


    List<Company> findByPreference(NewsletterPreference preference);
}
