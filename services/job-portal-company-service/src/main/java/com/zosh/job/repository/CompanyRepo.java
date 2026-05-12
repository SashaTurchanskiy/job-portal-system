package com.zosh.job.repository;

import com.zosh.job.domain.CompanyStatus;
import com.zosh.job.domain.CompanyType;
import com.zosh.job.domain.IndustryType;
import com.zosh.job.modal.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company, Long> {

    Optional<Company> findByOwnerId(Long ownerId);
    boolean existsByOwnerId(Long ownerId);
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    boolean existsByRegistrationNumber(String registrationNumber);

    @Query(
            "select c from Company c where " +
            "(:companyType IS null or c.companyType=:companyType) and " +
            "(:industryType Is Null or c.industryType=:industryType) And " +
            "(:status Is Null Or c.status=:status)")
    List<Company> findByFilters(
            @Param("companyType") CompanyType companyType,
            @Param("industryType")IndustryType industryType,
            @Param("status")CompanyStatus status
    );
}
