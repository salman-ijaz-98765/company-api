package com.afroze.projectmanagement.company.api.dto;

import com.afroze.projectmanagement.company.api.util.GetterSetterVerifier;
import org.junit.jupiter.api.Test;

class CompanyDtoTest {
    @Test
    void verifyGettersSetters() {
        GetterSetterVerifier.forClass(CompanyDto.class).verify();
    }
}