package com.example.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class CountryServiceTest {

    @Inject
    CountryService service;

    @Test
    void getCountryMap() {
        assertNotNull(service.getCountryMap());
    }

    @Test
    void getCountryName() {
        assertEquals( "Philippines", service.getCountryName("PH") );
        assertEquals( "Germany", service.getCountryName("DE") );
        assertEquals( "United States", service.getCountryName("US") );
    }
}