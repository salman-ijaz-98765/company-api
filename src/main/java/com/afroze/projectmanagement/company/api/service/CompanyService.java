package com.afroze.projectmanagement.company.api.service;

import com.afroze.projectmanagement.company.api.dto.CompanyDto;
import com.afroze.projectmanagement.company.api.exception.CompanyAlreadyExistsException;
import com.afroze.projectmanagement.company.api.exception.CompanyNotFoundException;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAll();
    CompanyDto getById(long companyId) throws CompanyNotFoundException;
    CompanyDto create(CompanyDto companyDto) throws CompanyAlreadyExistsException;
    CompanyDto update(long companyId, CompanyDto companyDto) throws CompanyNotFoundException;
    void delete(long companyId);
}
