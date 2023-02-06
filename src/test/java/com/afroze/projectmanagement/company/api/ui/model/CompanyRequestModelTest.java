package com.afroze.projectmanagement.company.api.ui.model;

import com.afroze.projectmanagement.company.api.util.GetterSetterVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyRequestModelTest {
    @Test
    void verifyGettersSetters() {
        GetterSetterVerifier.forClass(CompanyRequestModel.class).verify();
    }
}