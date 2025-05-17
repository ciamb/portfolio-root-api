package com.andrea.portfolio.rest;

import java.util.List;

import com.andrea.portfolio.model.Skill;
import com.andrea.portfolio.service.SkillsService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/skills")
public class SkillsResource {

    private final SkillsService skillsService;

    @Inject
    public SkillsResource(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Skill> getSkills() {
        return skillsService.getSkills();
    }
}
