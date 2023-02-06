package com.afroze.projectmanagement.company.api.ui.model;

import com.afroze.projectmanagement.company.api.util.GetterSetterVerifier;
import org.junit.jupiter.api.Test;

class CompanyResponseModelTest {
    @Test
    void verifyGettersSetters() {
        GetterSetterVerifier.forClass(CompanyResponseModel.class).verify();
    }
}