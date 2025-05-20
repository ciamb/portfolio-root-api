package com.andrea.skill;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SkillLoader {

    private final Logger log = Logger.getLogger(getClass().getSimpleName());
    private List<String> skills;

    private final SkillConfig skillConfig;

    @Inject
    public SkillLoader(SkillConfig skillConfig) {
        this.skillConfig = skillConfig;
    }

    @PostConstruct
    public void load() {
        if (skillConfig != null) {
            log.log(Level.FINEST, () -> "loading skills from yaml configuration: %s"
                    .formatted(skillConfig.values()));
            this.skills = skillConfig.values();
        } else {
            throw new IllegalStateException("skillConfig is null");
        }
    }

    public List<String> getSkills() {
        return skills;
    }
}
