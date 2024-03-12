package com.example.service.client;

import com.example.model.response.geosearch.GeoSearchResponseRoot;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "geosearch-api")
public interface GeoSearchClient {
    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        if (response.getStatus() >= 500) {
            return new RuntimeException("GeoSearch API returned with exception. ");
        } else if (response.getStatus() >= 400) {
            return new RuntimeException("Bad Request to GeoSearch API. ");
        }
        return null;
    }

    @GET
    GeoSearchResponseRoot getPlaces(@QueryParam("action") String action,
                                    @QueryParam("format") String format,
                                    @QueryParam("list") String list,
                                    @QueryParam("gscoord") String gscoord,
                                    @QueryParam("gsradius") int gsradius,
                                    @QueryParam("gslimit") int gslimit,
                                    @QueryParam("gsprop") String gsprop);
}
