package com.example;

import com.example.model.dto.ApiResponse;
import com.example.model.response.geosearch.GeoSearchResponseRoot;
import com.example.model.response.iss.IssResponseRoot;
import com.example.service.IssPlacesService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/test")
public class ExampleResource {

    @Inject
    IssPlacesService issPlacesService;

    @Path("hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }


    @Path("iss")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IssResponseRoot getIssLocation() {
        IssResponseRoot root = issPlacesService.getIssLocation();
        return issPlacesService.getIssLocation();
    }


    @Path("places")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GeoSearchResponseRoot getTestPlaces(@QueryParam("props") String props,
                                               @QueryParam("limit") int limit,
                                               @QueryParam("radius") int radius,
                                               @QueryParam("latitude") double latitude,
                                               @QueryParam("longitude") double longitude) {
        return issPlacesService.getTestPlaces(props, limit, radius, latitude, longitude);

    }


    @Path("sample")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<? extends ApiResponse> getSamplePlaces() {
        return issPlacesService.getSamplePlaces();
    }
}
