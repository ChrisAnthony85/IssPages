package com.example.model.response.geosearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoSearch(String title, double lat, double lon, String country) {
}
