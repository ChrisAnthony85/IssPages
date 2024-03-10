package com.example;

import com.example.model.dto.ResultsDTO;
import com.example.model.response.geosearch.GeoSearchResponseRoot;
import com.example.model.response.iss.IssResponseRoot;
import com.example.service.IssPlacesService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
public class ISSPagesResource {

    @Inject
    IssPlacesService issPlacesService;

    @Path("test")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @Path("test/iss")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IssResponseRoot getIssLocation() {
        return issPlacesService.getIssLocation();
    }



    @Path("test/places")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GeoSearchResponseRoot getTestPlaces( @QueryParam("props") String props,
                                               @QueryParam("limit") int limit,
                                               @QueryParam("radius") int radius,
                                               @QueryParam("latitude") double latitude,
                                               @QueryParam("longitude") double longitude ) {
        return issPlacesService.getTestPlaces(props, limit, radius, latitude, longitude);

    }



    @Path("iss/places")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultsDTO getPlaces() {
        return issPlacesService.getPlacesDefault();
    }

    @Path("test/sample")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultsDTO getSamplePlaces() {
        return issPlacesService.getSamplePlaces();
    }
}
