package com.andrea.portfolio.rest;

import java.util.UUID;
import java.util.logging.Logger;

import com.andrea.portfolio.service.about.AboutEventService;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/about")
public class AboutResource {

    private final Logger log = Logger.getLogger(getClass().getSimpleName());
    private final AboutEventService service;
    private final Template about;

    @Inject
    public AboutResource(
            AboutEventService service,
            @Location("about.qute.html") Template about) {
        this.service = service;
        this.about = about;
    }

    @GET
    @Path("/ui")
    @Produces(value = MediaType.TEXT_HTML)
    public Uni<String> ui() {

        return service.sendAndAwait(UUID.randomUUID().toString())
                .onItem()
                .ifNotNull()
                .transform(response -> {
                    log.info(() -> "[!] transforming AboutResponded in partial UI");
                    return about.data("about", response).render();
                });
    }

}
