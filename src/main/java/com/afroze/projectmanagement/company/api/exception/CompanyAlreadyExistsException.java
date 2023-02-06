package com.afroze.projectmanagement.company.api.exception;

import com.afroze.projectmanagement.company.api.domain.Company;

public class CompanyAlreadyExistsException extends Exception {
    public CompanyAlreadyExistsException(Company company) {
        super("Company with the name '" + company.getName() + "' already exists");
    }
}
