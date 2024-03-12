package com.example.service.client;

import com.example.model.response.iss.IssResponseRoot;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "iss-api")
public interface IssClient {

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        if (response.getStatus() >= 500) {
            return new RuntimeException("ISS API returned with exception. ");
        }
        return null;
    }

    @GET
    IssResponseRoot getIssLocation();
}
