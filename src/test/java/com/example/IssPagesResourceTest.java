package com.example;

import com.example.model.response.geosearch.GeoSearch;
import com.example.model.response.geosearch.GeoSearchResponseRoot;
import com.example.model.response.geosearch.Query;
import com.example.model.response.iss.IssPosition;
import com.example.model.response.iss.IssResponseRoot;
import com.example.service.client.GeoSearchClient;
import com.example.service.client.IssClient;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.MockitoConfig;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@QuarkusTest
public class IssPagesResourceTest {

    @InjectMock
    @MockitoConfig(convertScopes = true)
    @RestClient
    IssClient issMock;

    @InjectMock
    @MockitoConfig(convertScopes = true)
    @RestClient
    GeoSearchClient geoSearchMock;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void whenValidRequestThenOk() {
        IssPosition pos = new IssPosition("1", "1");
        IssResponseRoot issRoot = new IssResponseRoot(1L, pos, "test");
        when(issMock.getIssLocation()).thenReturn( issRoot );
        List<GeoSearch> places = new ArrayList<>();
        places.add(new GeoSearch("test place", 1.1, 1.1,  "PH"));
        Query query = new Query(places);
        GeoSearchResponseRoot gsRoot = new GeoSearchResponseRoot(true, query);
        when(geoSearchMock.getPlaces(anyString(), anyString(), anyString(), anyString(), anyInt(),
                anyInt(), anyString())).thenReturn( gsRoot );
        given().when().get("/api/iss/places").then().statusCode(200);
    }

    @Test
    void whenIssErrorThenThrowException() {
        when(issMock.getIssLocation()).thenThrow(new RuntimeException("ISS Exception."));

        given().when().get("/api/iss/places").then().statusCode(504);
    }

    @Test
    void whenGeoSearchErrorThenThrowException() {
        IssPosition pos = new IssPosition("1", "1");
        IssResponseRoot issRoot = new IssResponseRoot(1L, pos, "test");
        when(issMock.getIssLocation()).thenReturn( issRoot );
        when(geoSearchMock.getPlaces(anyString(), anyString(), anyString(), anyString(), anyInt(),
                anyInt(), anyString()) ).thenThrow(new RuntimeException("GeoSearch Exception."));

        given().when().get("/api/iss/places").then().statusCode(504);
    }

}
