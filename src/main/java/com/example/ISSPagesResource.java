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

    @Path("iss/places")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultsDTO getPlaces() {
        return issPlacesService.getPlacesDefault();
    }
}
