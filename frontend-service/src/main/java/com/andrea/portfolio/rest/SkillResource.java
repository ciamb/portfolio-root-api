package com.andrea.portfolio.rest;

import java.util.UUID;
import java.util.logging.Logger;

import com.andrea.portfolio.service.skill.SkillEventService;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/skill")
public class SkillResource {

    private final Logger log = Logger.getLogger(getClass().getSimpleName());
    private final SkillEventService service;
    private final Template skill;

    @Inject
    public SkillResource(
        SkillEventService service, 
        @Location("skill.qute.html") Template skill) {
        this.service = service;
        this.skill = skill;
    }

    @GET
    @Path("/ui")
    @Produces(value = MediaType.TEXT_HTML)
    public Uni<String> ui() {
        return service.sendAndAwait(UUID.randomUUID().toString())
                .onItem()
                .ifNotNull()
                .transform(response -> {
                    log.info(() -> "[!] transforming SkillResponded in partial UI");
                    return skill.data("skill", response.skills()).render();
                });
    }
}
