package com.andrea.portfolio.rest;

import com.andrea.portfolio.service.AboutService;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/about")
public class AboutResource {

    private final AboutService aboutService;
    private final Template about;

    @Inject
    public AboutResource(
            AboutService aboutService,
            @Location("about.qute.html") Template about) {
        this.aboutService = aboutService;
        this.about = about;
    }

    @GET
    @Produces(value = { MediaType.TEXT_PLAIN, MediaType.TEXT_HTML })
    public TemplateInstance aboutPage() {
        var info = aboutService.getAbout();
        return about.data("about", info);
    }

}
