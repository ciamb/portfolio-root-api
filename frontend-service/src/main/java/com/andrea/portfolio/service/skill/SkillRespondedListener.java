package com.andrea.portfolio.service.skill;

import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.andrea.events.skill.SkillResponded;
import com.andrea.portfolio.service.broadcaster.EventLogBroadcaster;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SkillRespondedListener {

    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    private final SkillTemplateService service;
    private final EventLogBroadcaster broadcaster;
    
    @Inject
    public SkillRespondedListener(SkillTemplateService service,EventLogBroadcaster broadcaster) {
        this.service = service;
        this.broadcaster = broadcaster;
    }

    @Incoming("skill-responded")
    public void consumeSkillResponse(SkillResponded response) {
        log.info(() -> "[!] received SkillResponded: %s".formatted(response));
        broadcaster.broadcast(response, "skill.responded");
        service.setSkillResponded(response);
    }



}
