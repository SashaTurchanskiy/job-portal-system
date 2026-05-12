package com.zosh.job.service.impl;

import com.zosh.job.domain.CompanyStatus;
import com.zosh.job.domain.CompanyType;
import com.zosh.job.domain.IndustryType;
import com.zosh.job.dto.request.CompanyRequest;
import com.zosh.job.dto.response.CompanyResponse;
import com.zosh.job.dto.response.SocialLinkResponse;
import com.zosh.job.mapper.CompanyMapper;
import com.zosh.job.modal.Company;
import com.zosh.job.modal.SocialLink;
import com.zosh.job.repository.CompanyRepo;
import com.zosh.job.service.CompanyService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepo companyRepo;

    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception {
        if (companyRepo.existsByOwnerId(ownerId)){
            throw new Exception("You already have company registered. Only one company per acc is allowed");
        }
        if (companyRepo.existsByName(request.getName())){
            throw new Exception("Company already exists. Please choose a different name");
        }
        if (request.getRegistrationNumber() != null &&
        companyRepo.existsByRegistrationNumber(request.getRegistrationNumber())){
            throw new Exception("Company already exists. Please choose different registration number");
        }

        String slug = generateUniqueSlug(request.getName());

        Company company = Company.builder()
                .name(request.getName())
                .slug(slug)
                .tagline(request.getTagline())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .coverImageUrl(request.getCoveImageUrl())
                .website(request.getWebsite())
                .email(request.getEmail())
                .phone(request.getPhone())
                .foundedYear(request.getFoundedYear())
                .companySize(request.getCompanySize())
                .companyType(request.getCompanyType())
                .industryType(request.getIndustryType())
                .registrationNumber(request.getRegistrationNumber())
                .ownerId(ownerId)
                .socialLinks(mapSocialLinks(request.getSocialLinks()))
                .build();

               Company savedCompany = companyRepo.save(company);

        return CompanyMapper.toResponse(savedCompany);
    }

    private List<SocialLink> mapSocialLinks(List<SocialLinkResponse> socialLinks) {
        if (socialLinks == null && socialLinks.isEmpty()){
            return new ArrayList<SocialLink>();
        }
        return socialLinks.stream().map(e -> SocialLink.builder()
                .platform(e.getPlatform())
                .url(e.getUrl())
                .build())
                .collect(Collectors.toList()
        );

    }

    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replace("[^a-z0-9\\s-]", "")
                .trim().replaceAll("[\\s-]", "-");

        if (!companyRepo.existsBySlug(base)){
            return base;
        }
        int counter = 1;
        while (companyRepo.existsBySlug(base + "-" + counter)){
            counter++;
        }
        return base + "-" + counter;

    }

    @Override
    public CompanyResponse getCompanyById(Long id) throws Exception {
        Company company = companyRepo.findById(id)
                .orElseThrow(()-> new Exception("Company not found"));
        return CompanyMapper.toResponse(company);
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) throws Exception {
        Company company = companyRepo.findByOwnerId(ownerId)
                .orElseThrow(()-> new Exception("Company exists for owner " + ownerId));
        return CompanyMapper.toResponse(company);
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
        return companyRepo.findByFilters(
                companyType,
                industryType,
                companyStatus
                ).stream()
                .map(CompanyMapper::toResponse)
                .toList();
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest request) throws Exception {
        Company company = getCompanyEntityById(companyId);

        if (!company.getName().equals(request.getName())){
            companyRepo.existsByName(request.getName());
        }
        if (request.getRegistrationNumber() != null
            && !request.getRegistrationNumber().equals(request.getRegistrationNumber())
                && companyRepo.existsByRegistrationNumber(request.getRegistrationNumber())){
            throw new Exception("Company already exists. Please choose a different registration number");
        }
        company.setName(request.getName());
        company.setTagline(request.getTagline());
        company.setDescription(request.getDescription());
        company.setLogoUrl(request.getLogoUrl());
        company.setCoverImageUrl(request.getCoveImageUrl());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setFoundedYear(request.getFoundedYear());
        company.setCompanySize(request.getCompanySize());
        company.setCompanyType(request.getCompanyType());
        company.setIndustryType(request.getIndustryType());
        company.setRegistrationNumber(request.getRegistrationNumber());
        company.setSocialLinks(mapSocialLinks(request.getSocialLinks()));

        return CompanyMapper.toResponse(companyRepo.save(company));
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        company.setStatus(CompanyStatus.ACTIVE);
        company.setVerified(true);
        return CompanyMapper.toResponse(companyRepo.save(company));
    }

    @Override
    public void deleteCompany(Long companyId, Long ownerId) throws Exception {
        Company company = getCompanyEntityById(ownerId);
        assertOwner(company, ownerId);
        companyRepo.delete(company);

    }

    private void assertOwner(Company company, Long ownerId) throws Exception {
        if (!company.getOwnerId().equals(ownerId)) {
            throw new Exception("You are not the owner of this company");
        }
    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        company.setStatus(CompanyStatus.SUSPEND);
        company.setVerified(false);
        return CompanyMapper.toResponse(companyRepo.save(company));
    }

    @Override
    public Company getCompanyEntityById(Long id) throws Exception {
        return companyRepo.findById(id).orElseThrow(()-> new Exception("Company not found with id"));
    }
}
