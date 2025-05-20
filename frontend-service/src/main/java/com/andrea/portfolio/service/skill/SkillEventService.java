package com.andrea.portfolio.service.skill;

import java.time.Duration;
import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.andrea.events.skill.SkillResponded;
import com.andrea.portfolio.service.broadcaster.EventLogBroadcaster;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SkillEventService {
    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    private final Emitter<String> emitter;
    private final EventLogBroadcaster broadcaster;

    private final SkillTemplateService skillTemplateService;

    @Inject
    public SkillEventService(
            @Channel("skill-requested") Emitter<String> emitter,
            EventLogBroadcaster broadcaster,
            SkillTemplateService skillTemplateService) {
        this.emitter = emitter;
        this.broadcaster = broadcaster;
        this.skillTemplateService = skillTemplateService;
    }

    public Uni<SkillResponded> sendAndAwait(String requestId) {
        sendSkillRequested(requestId);

        return Uni.createFrom()
                .<SkillResponded>emitter(e -> skillTemplateService
                        .onResponde(requestId, e::complete))
                .ifNoItem()
                .after(Duration.ofSeconds(5))
                .fail();
    }

    public void sendSkillRequested(String requestId) {
        log.info(() -> "[!] sending request for skill with requestId: %s".formatted(requestId));
        broadcaster.broadcast(requestId, "skill.requested");
        emitter.send(requestId);
    }


}
