package com.afroze.projectmanagement.company.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.AuditorAware;

import static org.junit.jupiter.api.Assertions.*;

class JpaConfigTest {
    @Test
    void auditorAware_returnsAuditorAware() {
        JpaConfig sut =  new JpaConfig();
        AuditorAware<String> result = sut.auditorAware();
        assertNotNull(result);
    }
}