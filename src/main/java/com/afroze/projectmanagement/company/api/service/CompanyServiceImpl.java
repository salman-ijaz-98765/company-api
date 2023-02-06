package com.afroze.projectmanagement.company.api.service;

import com.afroze.projectmanagement.company.api.domain.Company;
import com.afroze.projectmanagement.company.api.dto.CompanyDto;
import com.afroze.projectmanagement.company.api.exception.CompanyAlreadyExistsException;
import com.afroze.projectmanagement.company.api.exception.CompanyNotFoundException;
import com.afroze.projectmanagement.company.api.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final ModelMapper mapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper mapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public List<CompanyDto> getAll() {
        List<Company> companies = companyRepository.findAll();
        return mapper.map(companies, new TypeToken<List<CompanyDto>>(){}.getType());
    }

    @Override
    public CompanyDto getById(long companyId) throws CompanyNotFoundException {
        Company company = companyRepository.findById(companyId).orElse(null);

        if(company == null) {
            throw new CompanyNotFoundException(companyId);
        }
        return mapper.map(company, CompanyDto.class);
    }

    @Override
    public CompanyDto create(CompanyDto companyDto) throws CompanyAlreadyExistsException {
        Company companyWithSameName = companyRepository
                .findByName(companyDto.getName()).orElse(null);

        if(companyWithSameName != null) {
            throw new CompanyAlreadyExistsException(companyWithSameName);
        }

        Company company = mapper.map(companyDto, Company.class);
        Company createdCompany = companyRepository.save(company);

        return mapper.map(createdCompany, CompanyDto.class);
    }

    @Override
    public CompanyDto update(long companyId, CompanyDto companyDto) throws CompanyNotFoundException {
        Company companyToUpdate = companyRepository.findById(companyId).orElse(null);
        if(companyToUpdate == null) {
            throw new CompanyNotFoundException(companyDto.getId());
        }

        companyToUpdate.setName(companyDto.getName());
        companyToUpdate.setTags(companyDto.getTags());

        companyRepository.save(companyToUpdate);

        return mapper.map(companyToUpdate, CompanyDto.class);
    }

    @Override
    public void delete(long companyId) {
        Optional<Company> companyToDelete = companyRepository.findById(companyId);
        companyToDelete.ifPresent(companyRepository::delete);
    }
}
