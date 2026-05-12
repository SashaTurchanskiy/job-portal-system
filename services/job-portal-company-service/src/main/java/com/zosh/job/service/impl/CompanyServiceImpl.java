package com.zosh.job.service.impl;

import com.zosh.job.domain.CompanyStatus;
import com.zosh.job.domain.CompanyType;
import com.zosh.job.domain.IndustryType;
import com.zosh.job.dto.request.CompanyRequest;
import com.zosh.job.dto.response.CompanyResponse;
import com.zosh.job.modal.Company;
import com.zosh.job.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {



    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest request) {
        return null;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        return null;
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) {
        return null;
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
        return List.of();
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest request) {
        return null;
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) {
        return null;
    }

    @Override
    public void deleteCompany(Long companyId) {

    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) {
        return null;
    }

    @Override
    public Company getCompanyEntityById(Long id) {
        return null;
    }
}
