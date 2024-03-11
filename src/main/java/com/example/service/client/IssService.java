package com.example.service.client;

import com.example.model.response.iss.IssResponseRoot;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestResponse;

@RegisterRestClient(configKey = "iss-api")
public interface IssService {

    @GET
    IssResponseRoot getIssLocation();

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        if (response.getStatus() >= 500) {
            return new RuntimeException("ISS API returned with exception. ");
        }
        return null;
    }
}
