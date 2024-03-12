package com.example.service;

import com.example.model.dto.PlaceDTO;
import com.example.model.dto.ResultsDTO;
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
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
public class IssPlacesServiceTest {

    @Inject
    IssPlacesService service;

    @InjectMock
    @MockitoConfig(convertScopes = true)
    @RestClient
    IssClient issMock;

    @InjectMock
    @MockitoConfig(convertScopes = true)
    @RestClient
    GeoSearchClient geoSearchMock;

    @Test
    void getPlacesDefault() {
        IssPosition pos = new IssPosition("1", "1");
        IssResponseRoot issRoot = new IssResponseRoot(1L, pos, "test");
        when(issMock.getIssLocation()).thenReturn( issRoot );

        List<GeoSearch> places = new ArrayList<>();
        places.add(new GeoSearch("test place", 1.1, 2.1,  "PH"));
        Query query = new Query(places);
        GeoSearchResponseRoot gsRoot = new GeoSearchResponseRoot(true, query);
        when(geoSearchMock.getPlaces(anyString(), anyString(), anyString(), anyString(), anyInt(),
                anyInt(), anyString())).thenReturn( gsRoot );

        ResultsDTO results = (ResultsDTO) service.getPlacesDefault().getEntity();
        assertNotNull(results.getResults());
        assertEquals(1, results.getResults().size());

        PlaceDTO resultPlace = results.getResults().get(0);
        assertEquals("test place", resultPlace.getTitle());
        assertEquals(1.1, resultPlace.getLatitude());
        assertEquals(2.1, resultPlace.getLongitude());
        assertEquals("Philippines", resultPlace.getCountry());

    }

    @Test
    void getIssLocation() {
        IssPosition pos = new IssPosition("10", "10");
        IssResponseRoot issRoot = new IssResponseRoot(100L, pos, "test");
        when(issMock.getIssLocation()).thenReturn( issRoot );
        IssResponseRoot serviceResponse = service.getIssLocation();
        assertEquals(100L, serviceResponse.timestamp());
        assertNotNull(serviceResponse.iss_position());
        assertEquals("10", serviceResponse.iss_position().latitude());
        assertEquals("10", serviceResponse.iss_position().longitude());
        assertEquals("test", serviceResponse.message());
    }
}