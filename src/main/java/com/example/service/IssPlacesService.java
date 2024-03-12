package com.example.service;

import com.example.model.dto.ApiResponse;
import com.example.model.dto.Error;
import com.example.model.dto.ResultsDTO;
import com.example.model.mapping.PlaceMapper;
import com.example.model.response.geosearch.GeoSearchResponseRoot;
import com.example.model.response.iss.IssResponseRoot;
import com.example.service.client.GeoSearchClient;
import com.example.service.client.IssClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;

@ApplicationScoped
public class IssPlacesService {

    private static final String FORMAT = "json";
    private static final String LIST = "geosearch";
    private static final String ACTION = "query";
    @RestClient
    GeoSearchClient geoSearchClient;
    @RestClient
    IssClient issClient;
    @Inject
    PlaceMapper placeMapper;

    public RestResponse<? extends ApiResponse> getPlacesDefault() {
        int gsRadius = 10000;
        int gsLimit = 10;
        String gsProp = "country";

        try {
            IssResponseRoot issRoot = issClient.getIssLocation();
            String latitude = issRoot.iss_position().latitude();
            String longitude = issRoot.iss_position().longitude();

            GeoSearchResponseRoot root = getGeosearch(FORMAT, LIST,
                    ACTION, gsRadius, gsLimit, gsProp, latitude, longitude);

            ResultsDTO resultsDTO = new ResultsDTO();
            if (root.query() != null) {
                resultsDTO.setResults(placeMapper.toPlaceDtoList(root.query().geosearch()));
            }
            return RestResponse.ResponseBuilder.ok(resultsDTO).build();
        } catch (RuntimeException exception) {
            Error error = new Error("ERROR", 504, exception.getMessage());
            return RestResponse.ResponseBuilder.ok(error).status(504).build();
        }
    }

    public IssResponseRoot getIssLocation() {
        return issClient.getIssLocation();
    }

    private GeoSearchResponseRoot getGeosearch(String format, String list, String action, int gsRadius, int gsLimit, String gsProp, String latitude, String longitude) {
        return geoSearchClient.getPlaces(action, format, list,
                latitude + "|" + longitude,
                gsRadius, gsLimit, gsProp);
    }

    public RestResponse<? extends ApiResponse> getSamplePlaces() {

        int gsRadius = 10000;
        int gsLimit = 10;
        String gsProp = "country";

        String latitude = "16.593372";
        String longitude = "120.316351";
        try {
            GeoSearchResponseRoot root = getGeosearch(FORMAT, LIST,
                    ACTION, gsRadius, gsLimit, gsProp, latitude, longitude);

            ResultsDTO resultsDTO = new ResultsDTO();
            if (root.query() != null) {
                resultsDTO.setResults(placeMapper.toPlaceDtoList(root.query().geosearch()));
            }
            return RestResponse.ResponseBuilder.ok(resultsDTO).build();
        } catch (RuntimeException exception) {
            Error error = new Error("ERROR", 504, exception.getMessage());
            return RestResponse.ResponseBuilder.ok(error).status(504).build();
        }

    }


    public GeoSearchResponseRoot getTestPlaces(String props, int limit, int radius, double latitude, double longitude) {
        return geoSearchClient.getPlaces(ACTION, FORMAT, LIST,
                latitude + "|" + longitude,
                radius, limit, props);
    }
}
