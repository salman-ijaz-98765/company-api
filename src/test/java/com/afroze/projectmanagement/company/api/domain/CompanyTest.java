package com.afroze.projectmanagement.company.api.domain;

import com.afroze.projectmanagement.company.api.util.GetterSetterVerifier;
import org.junit.jupiter.api.Test;

class CompanyTest {

    @Test
    void verifyGettersSetters() {
        GetterSetterVerifier.forClass(Company.class).verify();
    }
}