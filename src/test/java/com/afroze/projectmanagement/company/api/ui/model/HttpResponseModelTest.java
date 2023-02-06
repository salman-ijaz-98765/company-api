package com.afroze.projectmanagement.company.api.ui.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseModelTest {
    @Test
    void success_returnsStatusAndData() {
        HttpResponseModel<String> sut = HttpResponseModel.success("data");

        assertEquals("data", sut.getData());
        assertFalse(sut.isError());
        assertEquals("", sut.getErrorMessage());
    }

    @Test
    void failure_returnsStatusAndError() {
        HttpResponseModel<String> sut = HttpResponseModel.failure("failure", "error");

        assertEquals("failure", sut.getData());
        assertTrue(sut.isError());
        assertEquals("error", sut.getErrorMessage());
    }
}