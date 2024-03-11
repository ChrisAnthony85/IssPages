package com.example.service;

import com.example.model.dto.ResultsDTO;
import com.example.model.mapping.PlaceMapper;
import com.example.model.response.geosearch.GeoSearchResponseRoot;
import com.example.model.response.iss.IssResponseRoot;
import com.example.service.client.GeoSearchService;
import com.example.service.client.IssService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class IssPlacesService {

    @RestClient
    GeoSearchService geoSearchService;

    @RestClient
    IssService issService;

    @Inject
    PlaceMapper placeMapper;

    private static final String FORMAT = "json";
    private static final String LIST = "geosearch";
    private static final String ACTION = "query";

    public ResultsDTO getPlacesDefault() {
        int gsRadius = 10000;
        int gsLimit = 10;
        String gsProp = "country";

        IssResponseRoot issRoot = issService.getIssLocation();
        String latitude = issRoot.iss_position().latitude();
        String longitude = issRoot.iss_position().longitude();

        GeoSearchResponseRoot root = getGeosearch(FORMAT, LIST, ACTION, gsRadius, gsLimit, gsProp, latitude, longitude);

        ResultsDTO resultsDTO = new ResultsDTO();
        if(root.query() != null) {
            resultsDTO.setResults(placeMapper.toPlaceDtoList(root.query().geosearch()));
        }
        return resultsDTO;
    }

    private GeoSearchResponseRoot getGeosearch(String format, String list, String action, int gsRadius, int gsLimit, String gsProp, String latitude, String longitude) {
        return geoSearchService.getPlaces(action, format, list,
                latitude + "|" + longitude,
                gsRadius, gsLimit, gsProp);
    }

    public ResultsDTO getSamplePlaces() {

        int gsRadius = 10000;
        int gsLimit = 10;
        String gsProp = "country";

        String latitude = "16.593372";
        String longitude = "120.316351";

        GeoSearchResponseRoot root = getGeosearch(FORMAT, LIST, ACTION, gsRadius, gsLimit, gsProp, latitude, longitude);

        ResultsDTO resultsDTO = new ResultsDTO();
        if(root.query() != null) {
            resultsDTO.setResults(placeMapper.toPlaceDtoList(root.query().geosearch()));
        }
        return resultsDTO;
    }

    public IssResponseRoot getIssLocation() {
        return issService.getIssLocation();
    }

    public GeoSearchResponseRoot getTestPlaces(String props, int limit, int radius, double latitude, double longitude) {
        return geoSearchService.getPlaces(ACTION, FORMAT, LIST,
                latitude + "|" + longitude,
                radius, limit, props);
    }
}
