package com.andrea.portfolio.rest;

import java.util.UUID;

import com.andrea.portfolio.service.AboutEventService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/about")
public class AboutResource {

    private final AboutEventService service;

    @Inject
    public AboutResource(
            AboutEventService service) {
        this.service = service;
    }

    @GET
    @Path("/trigger")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String triggerEvent() {
        service.sendAboutRequested(UUID.randomUUID().toString());
        return "[i] event AboutRequested sent to kafka!";
    }

}
