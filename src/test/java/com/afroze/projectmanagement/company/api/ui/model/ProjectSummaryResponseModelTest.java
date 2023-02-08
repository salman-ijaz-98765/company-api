package com.afroze.projectmanagement.company.api.ui.model;

import com.afroze.projectmanagement.company.api.util.GetterSetterVerifier;
import org.junit.jupiter.api.Test;

class ProjectSummaryResponseModelTest {
    @Test
    void verifyGettersSetters() {
        GetterSetterVerifier.forClass(ProjectSummaryResponseModel.class).verify();
    }
}