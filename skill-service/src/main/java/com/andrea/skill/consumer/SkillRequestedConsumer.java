package com.andrea.skill.consumer;

import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import com.andrea.events.skill.SkillResponded;
import com.andrea.skill.SkillLoader;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SkillRequestedConsumer {

    private final Logger log = Logger.getLogger(getClass().getSimpleName());
    private final SkillLoader skillLoader;

    @Inject
    public SkillRequestedConsumer(SkillLoader skillLoader) {
        this.skillLoader = skillLoader;
    }

    @Incoming("skill-requested")
    @Outgoing("skill-responded")
    public Uni<SkillResponded> onSkillRequested(String requestId) {
        log.info(() -> "[!] received request for skills list");
        var res = new SkillResponded(requestId, skillLoader.getSkills());
        return Uni.createFrom().item(res); 
    }

}
