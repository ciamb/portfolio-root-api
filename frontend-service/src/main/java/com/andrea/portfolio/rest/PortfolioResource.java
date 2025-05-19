package com.andrea.portfolio.rest;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/portfolio")
public class PortfolioResource {
    private final Template layout;

    public PortfolioResource(
            @Location("layout.qute.html") Template layout) {
        this.layout = layout;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance portfolio() {
        return layout.data("content", null);
    }

    @GET
    @Path("/ui")
    @Produces(MediaType.TEXT_HTML)
    public String ui() {
        return "<p>Benvenuto nel portfolio!</p>";
    }
}
