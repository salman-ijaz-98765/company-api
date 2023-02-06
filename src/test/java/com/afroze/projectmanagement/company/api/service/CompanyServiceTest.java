package com.afroze.projectmanagement.company.api.service;

import com.afroze.projectmanagement.company.api.domain.Company;
import com.afroze.projectmanagement.company.api.dto.CompanyDto;
import com.afroze.projectmanagement.company.api.exception.CompanyAlreadyExistsException;
import com.afroze.projectmanagement.company.api.exception.CompanyNotFoundException;
import com.afroze.projectmanagement.company.api.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Spy
    ModelMapper mapper = new ModelMapper();

    @InjectMocks
    private CompanyServiceImpl companyService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    private Company getCompany() {
        Company c1 = new Company();
        c1.setId(1L);
        c1.setName("company a");
        c1.setTags("tag1,tag2");
        return c1;
    }

    private CompanyDto getCompanyDto() {
        CompanyDto c1 = new CompanyDto();
        c1.setId(1L);
        c1.setName("company a");
        c1.setTags("tag1,tag2");
        return c1;
    }

    @Test
    void getAll_returnsCompanies() {
        List<Company> companies = new ArrayList<>();
        companies.add(getCompany());
        Mockito.when(companyRepository.findAll()).thenReturn(companies);

        List<CompanyDto> result = companyService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void getById_returnsCompany() throws CompanyNotFoundException {
        Mockito.when(companyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCompany()));
        CompanyDto result = companyService.getById(1L);
        assertEquals("company a", result.getName());
        assertEquals("tag1,tag2", result.getTags());
    }

    @Test
    void getById_throws() {
        Mockito.when(companyRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(CompanyNotFoundException.class, () -> companyService.getById(2L));
    }

    @Test
    void create_returnsSuccess() throws CompanyAlreadyExistsException {
        Mockito.when(companyRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(companyRepository.save(Mockito.any(Company.class))).thenReturn(getCompany());

        CompanyDto result = companyService.create(getCompanyDto());
        assertNotNull(result);
        assertEquals("company a", result.getName());
    }

    @Test
    void create_throws(){
        Mockito.when(companyRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(getCompany()));
        assertThrows(CompanyAlreadyExistsException.class, () -> companyService.create(getCompanyDto()));
    }

    @Test
    void update_returnsSuccess() throws CompanyNotFoundException {
        Mockito.when(companyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCompany()));
        Mockito.when(companyRepository.save(Mockito.any(Company.class))).thenReturn(getCompany());

        CompanyDto result = companyService.update(1L, getCompanyDto());
        assertNotNull(result);
        assertEquals("company a", result.getName());
    }

    @Test
    void update_throws() {
        Mockito.when(companyRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(CompanyNotFoundException.class, () -> companyService.update(1L, getCompanyDto()));
    }

    @Test
    void delete_runs() {
        Mockito.when(companyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCompany()));
        assertDoesNotThrow(() ->companyService.delete(1L));
    }
}