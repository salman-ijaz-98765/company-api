package com.afroze.projectmanagement.company.api.data;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SpringSecurityAuditorAwareTest {
    @Test
    void getCurrentAuditor_returnsDefault() {
        SpringSecurityAuditorAware sut = new SpringSecurityAuditorAware();

        Optional<String> result = sut.getCurrentAuditor();

        assertTrue(result.isPresent());
        assertEquals("system", result.get());
    }
}