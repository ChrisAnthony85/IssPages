package com.example.service.client;

import com.example.model.response.iss.IssResponseRoot;
import jakarta.ws.rs.GET;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "iss-api")
public interface IssService {

    @GET
    IssResponseRoot getIssLocation();
}
