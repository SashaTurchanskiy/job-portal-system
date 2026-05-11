package com.zosh.job.service;

import com.zosh.job.domain.CompanyStatus;
import com.zosh.job.domain.CompanyType;
import com.zosh.job.domain.IndustryType;
import com.zosh.job.dto.request.CompanyRequest;
import com.zosh.job.dto.response.CompanyResponse;
import com.zosh.job.modal.Company;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(Long ownerId, CompanyRequest request);
    CompanyResponse getCompanyById(Long id);
    CompanyResponse getMyCompany(Long ownerId);
    List<CompanyResponse> getAllCompanies(CompanyType companyType,
                                          IndustryType industryType,
                                          CompanyStatus companyStatus);
    CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest request);
    CompanyResponse verifyCompany(Long companyId);
    void deleteCompany(Long companyId);
    CompanyResponse deactivateCompany(Long companyId);

    Company getCompanyEntityById(Long id);
}
