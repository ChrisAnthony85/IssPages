package com.example.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ApplicationScoped
public class CountryService {

    private Map<String, String> countryNamesMap;

    @PostConstruct
    void init() {
        countryNamesMap = new HashMap<>();
        String[] countryCodes = Locale.getISOCountries();
        for (String isoCode : countryCodes) {
            countryNamesMap.put(isoCode, new Locale("", isoCode).getDisplayCountry());
        }
    }

    public Map<String, String> getCountryMap() {
        return countryNamesMap;
    }

    public String getCountryName(String isoCode) {
        return countryNamesMap.get(isoCode);
    }
}
