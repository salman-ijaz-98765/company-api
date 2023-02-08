package com.afroze.projectmanagement.company.api.ui.controller;

import com.afroze.projectmanagement.company.api.dto.CompanyDto;
import com.afroze.projectmanagement.company.api.exception.CompanyAlreadyExistsException;
import com.afroze.projectmanagement.company.api.exception.CompanyNotFoundException;
import com.afroze.projectmanagement.company.api.security.Permissions;
import com.afroze.projectmanagement.company.api.service.CompanyService;
import com.afroze.projectmanagement.company.api.ui.model.CompanyRequestModel;
import com.afroze.projectmanagement.company.api.ui.model.CompanyResponseModel;
import com.afroze.projectmanagement.company.api.ui.model.CompanySummaryResponseModel;
import com.afroze.projectmanagement.company.api.ui.model.HttpResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    private final ModelMapper mapper;

    private final Logger logger;

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("EI_EXPOSE_REP2")
    @Autowired
    public CompanyController(CompanyService companyService, ModelMapper mapper) {
        this.companyService = companyService;
        this.mapper = mapper;
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        this.logger = LoggerFactory.getLogger(CompanyController.class);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('" + Permissions.READ_COMPANY + "')")
    @Operation(summary = "Get Companies", security = @SecurityRequirement(name="bearerAuth"))
    public ResponseEntity<HttpResponseModel<List<CompanySummaryResponseModel>>> getAll() {
        logger.info("Getting all companies");

        List<CompanyDto> companies = companyService.getAll();
        if(companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CompanySummaryResponseModel> response = mapper.map(companies, new TypeToken<List<CompanySummaryResponseModel>>(){}.getType());
        return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.success(response));
    }

    @GetMapping("/{companyId}/")
    @PreAuthorize("hasAuthority('" + Permissions.READ_COMPANY + "')")
    public ResponseEntity<HttpResponseModel<CompanyResponseModel>> getById(@PathVariable("companyId") int companyId) {
        try {
            CompanyDto company = companyService.getById(companyId);
            CompanyResponseModel response = mapper.map(company, CompanyResponseModel.class);
            return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.success(response));
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpResponseModel.failure(null, e.getLocalizedMessage()));
        }
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('" + Permissions.WRITE_COMPANY + "')")
    public ResponseEntity<HttpResponseModel<CompanyResponseModel>> create(@RequestBody @Valid CompanyRequestModel company) {
        CompanyDto dto = mapper.map(company, CompanyDto.class);
        try {
            CompanyDto createdCompany = companyService.create(dto);
            CompanyResponseModel response = mapper.map(createdCompany, CompanyResponseModel.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(HttpResponseModel.success(response));
        } catch (CompanyAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpResponseModel.failure(null, e.getLocalizedMessage()));
        }
    }

    @PutMapping("/{companyId}/")
    @PreAuthorize("hasAuthority('" + Permissions.UPDATE_COMPANY + "')")
    public ResponseEntity<HttpResponseModel<CompanyResponseModel>> update(
            @PathVariable("companyId") long companyId,
            @RequestBody @Valid CompanyRequestModel company) {
        CompanyDto dto = mapper.map(company, CompanyDto.class);
        try {
            CompanyDto updatedCompany = companyService.update(companyId, dto);
            CompanyResponseModel response = mapper.map(updatedCompany, CompanyResponseModel.class);
            return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.success(response));
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpResponseModel.failure(null, e.getLocalizedMessage()));
        }
    }

    @DeleteMapping("/{companyId}/")
    @PreAuthorize("hasAuthority('" + Permissions.DELETE_COMPANY + "')")
    public ResponseEntity<HttpResponseModel<CompanyResponseModel>> delete(@PathVariable("companyId") int companyId) {
        companyService.delete(companyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
