package com.example.service.client;

import com.example.model.response.geosearch.GeoSearchResponseRoot;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "geosearch-api")
public interface GeoSearchService {
    @GET
    GeoSearchResponseRoot getPlaces(@QueryParam("action") String action,
                                    @QueryParam("format") String format,
                                    @QueryParam("list") String list,
                                    @QueryParam("gscoord") String gscoord,
                                    @QueryParam("gsradius") int gsradius,
                                    @QueryParam("gslimit") int gslimit,
                                    @QueryParam("gsprop") String gsprop );
}
