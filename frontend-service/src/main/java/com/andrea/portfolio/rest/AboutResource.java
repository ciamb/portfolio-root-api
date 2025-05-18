package com.andrea.portfolio.rest;

import java.util.UUID;

import com.andrea.portfolio.service.AboutEventService;
import com.andrea.portfolio.service.AboutRespondedListener;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/about")
public class AboutResource {

    private final AboutEventService service;
    private final AboutRespondedListener listener;
    private final Template about;

    @Inject
    public AboutResource(
            AboutEventService service,
            AboutRespondedListener listener,
            @Location("about.qute.html") Template about) {
        this.service = service;
        this.listener = listener;
        this.about = about;
    }

    @GET
    @Path("/trigger")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String triggerEvent() {
        service.sendAboutRequested(UUID.randomUUID().toString());
        return "[i] event AboutRequested sent to kafka!";
    }

}
