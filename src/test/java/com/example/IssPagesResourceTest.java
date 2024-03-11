package com.example;

import com.example.model.dto.ResultsDTO;
import com.example.model.response.geosearch.GeoSearch;
import com.example.model.response.geosearch.GeoSearchResponseRoot;
import com.example.model.response.geosearch.Query;
import com.example.model.response.iss.IssPosition;
import com.example.model.response.iss.IssResponseRoot;
import com.example.service.client.GeoSearchService;
import com.example.service.client.IssService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.MockitoConfig;
import io.restassured.response.ResponseOptions;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.HttpURLConnection;
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
    IssService issMock;

    @InjectMock
    @MockitoConfig(convertScopes = true)
    @RestClient
    GeoSearchService geoSearchMock;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void whenValidRequestThenOk() {
        IssPosition pos = new IssPosition("1", "1");
        IssResponseRoot issRoot = new IssResponseRoot(1L, pos, "test");
        when(issMock.getIssLocation()).thenReturn( issRoot );
        List<GeoSearch> places = new ArrayList<>();
        places.add(new GeoSearch("test place", 1.1, 1.1,  "Philippines"));
        Query query = new Query(places);
        GeoSearchResponseRoot gsRoot = new GeoSearchResponseRoot(true, query);
        when(geoSearchMock.getPlaces(anyString(), anyString(), anyString(), anyString(), anyInt(),
                anyInt(), anyString())).thenReturn( gsRoot );
        given().when().get("/api/iss/places").then().statusCode(200);
    }

    @Test
    void whenIssErrorThenThrowException() {
        when(issMock.getIssLocation()).thenThrow(new RuntimeException("ISS Exception."));

        given().when().get("/api/iss/places").then().statusCode(500);
    }

    @Test
    void whenGeoSearchErrorThenThrowException() {
        IssPosition pos = new IssPosition("1", "1");
        IssResponseRoot issRoot = new IssResponseRoot(1L, pos, "test");
        when(issMock.getIssLocation()).thenReturn( issRoot );
        when(geoSearchMock.getPlaces(anyString(), anyString(), anyString(), anyString(), anyInt(),
                anyInt(), anyString()) ).thenThrow(new RuntimeException("GeoSearch Exception."));

        given().when().get("/api/iss/places").then().statusCode(500);
    }

}
