package com.example;

import com.example.model.dto.ApiResponse;
import com.example.service.IssPlacesService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/api")
public class ISSPagesResource {

    @Inject
    IssPlacesService issPlacesService;

    @Path("iss/places")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<? extends ApiResponse> getPlaces() {
        return issPlacesService.getPlacesDefault();
    }
}
