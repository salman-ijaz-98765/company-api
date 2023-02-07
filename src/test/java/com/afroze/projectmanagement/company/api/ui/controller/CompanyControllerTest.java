package com.afroze.projectmanagement.company.api.ui.controller;

import com.afroze.projectmanagement.company.api.domain.Company;
import com.afroze.projectmanagement.company.api.dto.CompanyDto;
import com.afroze.projectmanagement.company.api.exception.CompanyAlreadyExistsException;
import com.afroze.projectmanagement.company.api.exception.CompanyNotFoundException;
import com.afroze.projectmanagement.company.api.service.CompanyService;
import com.afroze.projectmanagement.company.api.ui.model.CompanyRequestModel;
import com.afroze.projectmanagement.company.api.ui.model.CompanyResponseModel;
import com.afroze.projectmanagement.company.api.ui.model.CompanySummaryResponseModel;
import com.afroze.projectmanagement.company.api.ui.model.HttpResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    @Spy
    ModelMapper mapper = new ModelMapper();


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_returnsListOfCompanies() {
        List<CompanyDto> dtos = new ArrayList<>();
        CompanyDto dto = new CompanyDto();
        dto.setName("company 1");
        dto.setTags("tag1,tag2");
        dtos.add(dto);

        Mockito.when(companyService.getAll()).thenReturn(dtos);

        var sut = new CompanyController(companyService, mapper);

        ResponseEntity<HttpResponseModel<List<CompanySummaryResponseModel>>> result = sut.getAll();

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertFalse(result.getBody().isError());
        assertEquals(1, result.getBody().getData().size());
        assertEquals("", result.getBody().getErrorMessage());
    }

    @Test
    void getAll_returns404() {
        List<CompanyDto> dtos = new ArrayList<>();

        Mockito.when(companyService.getAll()).thenReturn(dtos);

        var sut = new CompanyController(companyService, mapper);

        ResponseEntity<HttpResponseModel<List<CompanySummaryResponseModel>>> result = sut.getAll();

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void getById_returnsCompany() throws CompanyNotFoundException {
        CompanyDto dto = new CompanyDto();
        dto.setId(1L);
        dto.setName("company 1");
        dto.setTags("tag1,tag2");

        Mockito.when(companyService.getById(Mockito.anyLong())).thenReturn(dto);

        var sut = new CompanyController(companyService, mapper);

        ResponseEntity<HttpResponseModel<CompanyResponseModel>> result = sut.getById(1);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertFalse(result.getBody().isError());
    }

    @Test
    void getById_returns404() throws CompanyNotFoundException {
        Mockito.when(companyService.getById(Mockito.anyLong())).thenThrow(new CompanyNotFoundException(1));

        var sut = new CompanyController(companyService, mapper);

        ResponseEntity<HttpResponseModel<CompanyResponseModel>> result = sut.getById(1);

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void create_returnsSuccess() throws CompanyAlreadyExistsException {
        CompanyRequestModel model = new CompanyRequestModel();
        model.setName("company 1");
        model.setTags("tag1,tag2");

        CompanyDto createdCompany = new CompanyDto();
        createdCompany.setId(1L);
        createdCompany.setName("company 1");
        createdCompany.setTags("tag1,tag2");

        Mockito.when(companyService.create(Mockito.any(CompanyDto.class))).thenReturn(createdCompany);

        CompanyController sut = new CompanyController(companyService, mapper);
        ResponseEntity<HttpResponseModel<CompanyResponseModel>> result = sut.create(model);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertFalse(result.getBody().isError());
    }

    @Test
    void create_returns400() throws CompanyAlreadyExistsException {
        CompanyRequestModel model = new CompanyRequestModel();
        model.setName("company 1");
        model.setTags("tag1,tag2");

        Company existingCompany = new Company();
        existingCompany.setName("company 1");
        existingCompany.setTags("tag1,tag2");

        Mockito.when(companyService.create(Mockito.any(CompanyDto.class))).thenThrow(new CompanyAlreadyExistsException(existingCompany));

        CompanyController sut = new CompanyController(companyService, mapper);
        ResponseEntity<HttpResponseModel<CompanyResponseModel>> result = sut.create(model);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertTrue(result.getBody().isError());
    }

    @Test
    void update_returnsSuccess() throws CompanyNotFoundException {
        CompanyRequestModel model = new CompanyRequestModel();
        model.setName("company 1");
        model.setTags("tag1,tag2");

        CompanyDto updatedCompany = new CompanyDto();
        updatedCompany.setId(1L);
        updatedCompany.setName("company 1");
        updatedCompany.setTags("tag1,tag2");

        Mockito.when(companyService.update(Mockito.anyLong(), Mockito.any(CompanyDto.class))).thenReturn(updatedCompany);

        CompanyController sut = new CompanyController(companyService, mapper);
        ResponseEntity<HttpResponseModel<CompanyResponseModel>> result = sut.update(1L, model);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertFalse(result.getBody().isError());
    }

    @Test
    void update_returns404() throws CompanyNotFoundException {
        CompanyRequestModel model = new CompanyRequestModel();
        model.setName("company 1");
        model.setTags("tag1,tag2");

        Mockito.when(companyService.update(Mockito.anyLong(), Mockito.any(CompanyDto.class))).thenThrow(new CompanyNotFoundException(1L));

        CompanyController sut = new CompanyController(companyService, mapper);
        ResponseEntity<HttpResponseModel<CompanyResponseModel>> result = sut.update(1L, model);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertTrue(result.getBody().isError());
    }

    @Test
    void delete_returns204() {
        CompanyController sut = new CompanyController(companyService, mapper);
        ResponseEntity<HttpResponseModel<CompanyResponseModel>> result = sut.delete(1);

        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}